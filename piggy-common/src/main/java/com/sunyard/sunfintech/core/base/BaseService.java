package com.sunyard.sunfintech.core.base;

import com.baomidou.mybatisplus.plugins.Page;
import com.sunyard.sunfintech.core.Constants;
import com.sunyard.sunfintech.core.util.BeanUtil;
import com.sunyard.sunfintech.core.util.CacheUtil;
import com.sunyard.sunfintech.core.util.DataUtil;
import com.sunyard.sunfintech.core.util.ExceptionUtil;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 业务逻辑层基类<br/>
 * 继承基类后必须配置CacheConfig(cacheNames=""), 用于缓存记录
 * @author heroy
 * @version 20170401
 */
public abstract class BaseService<T extends BaseEntity> implements ApplicationContextAware {
	protected Logger logger = LogManager.getLogger(getClass());

	@Resource
	protected BaseMapper<T> mapper;

	protected ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/** 分页查询 */
	public static Page<Long> getPage(Map<String, Object> params) {
		Integer current = 1;
		Integer size = 10;
		String orderBy = "id";
		if (DataUtil.isNotEmpty(params.get("pageNum"))) {
			current = Integer.valueOf(params.get("pageNum").toString());
		}
		if (DataUtil.isNotEmpty(params.get("pageSize"))) {
			size = Integer.valueOf(params.get("pageSize").toString());
		}
		if (DataUtil.isNotEmpty(params.get("orderBy"))) {
			orderBy = (String) params.get("orderBy");
			params.remove("orderBy");
		}
		if (size == -1) {
			return new Page<Long>();
		}
		Page<Long> page = new Page<Long>(current, size, orderBy);
		page.setAsc(false);
		return page;
	}

	/** 根据Id查询(默认类型T) */
	public Page<T> getPage(Page<Long> ids) {
		if (ids != null) {
			Page<T> page = new Page<T>(ids.getCurrent(), ids.getSize());
			page.setTotal(ids.getTotal());
			List<T> records = BeanUtil.newArrayList();
			for (Long id : ids.getRecords()) {
				records.add(this.queryById(id));
			}
			page.setRecords(records);
			return page;
		}
		return new Page<T>();
	}

	/** 根据Id查询(默认类型T) */
	public Page<Map<String, Object>> getPageMap(Page<Long> ids) {
		if (ids != null) {
			Page<Map<String, Object>> page = new Page<Map<String, Object>>(ids.getCurrent(), ids.getSize());
			page.setTotal(ids.getTotal());
			List<Map<String, Object>> records = BeanUtil.newArrayList();
			for (Long id : ids.getRecords()) {
				records.add(BeanUtil.transBean2Map(this.queryById(id)));
			}
			page.setRecords(records);
			return page;
		}
		return new Page<Map<String, Object>>();
	}

	/** 根据Id查询(cls返回类型Class) */
	public <K> Page<K> getPage(Page<Long> ids, Class<K> cls) {
		if (ids != null) {
			Page<K> page = new Page<K>(ids.getCurrent(), ids.getSize());
			page.setTotal(ids.getTotal());
			List<K> records = BeanUtil.newArrayList();
			for (Long id : ids.getRecords()) {
				T t = this.queryById(id);
				K k = BeanUtil.to(t, cls);
				records.add(k);
			}
			page.setRecords(records);
			return page;
		}
		return new Page<K>();
	}

	/** 根据Id查询(默认类型T) */
	public List<T> getList(List<Long> ids) {
		List<T> list = BeanUtil.newArrayList();
		if (ids != null) {
			for (Long id : ids) {
				list.add(this.queryById(id));
			}
		}
		return list;
	}

	/** 根据Id查询(cls返回类型Class) */
	public <K> List<K> getList(List<Long> ids, Class<K> cls) {
		List<K> list = BeanUtil.newArrayList();
		if (ids != null) {
			for (Long id : ids) {
				T t = this.queryById(id);
				K k = BeanUtil.to(t, cls);
				list.add(k);
			}
		}
		return list;
	}

	@Transactional
	public void del(Long id, String userId) {
		try {
			T record = this.queryById(id);
			record.setEnabled(Constants.DISABLED);
			record.setUpdate_time(new Date());
			record.setUpdate_by(userId);
			mapper.updateById(record);
			CacheUtil.getCache().set(getCacheKey(id), record);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Transactional
	public void delete(Long id) {
		try {
			mapper.deleteById(id);
			CacheUtil.getCache().del(getCacheKey(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Transactional
	public T update(T record) {
		try {
			record.setUpdate_time(new Date());
			if (record.getId() == null) {
				record.setCreate_time(new Date());
				mapper.insert(record);
			} else {
				T org = queryById(record.getId());
				String lockKey = getLockKey(record.getId());
				if (CacheUtil.getLock(lockKey)) {
					try {
						T update = BeanUtil.getDiff(org, record);
						update.setId(record.getId());
						mapper.updateById(update);
						record = mapper.selectById(record.getId());
						CacheUtil.getCache().set(getCacheKey(record.getId()), record);
					} finally {
						CacheUtil.unlock(lockKey);
					}
				} else {
					sleep(20);
					return update(record);
				}
			}
		} catch (DuplicateKeyException e) {
			String msg = ExceptionUtil.getStackTraceAsString(e);
			logger.error(Constants.EXCEPTION_HEAD + msg, e);
			throw new RuntimeException("已经存在相同的配置.");
		} catch (Exception e) {
			String msg = ExceptionUtil.getStackTraceAsString(e);
			logger.error(Constants.EXCEPTION_HEAD + msg, e);
			throw new RuntimeException(msg);
		}
		return record;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public T queryById(Long id) {
		try {
			String key = getCacheKey(id);
			T record = (T) CacheUtil.getCache().get(key);
			if (record == null) {
				String lockKey = getLockKey(id);
				if (CacheUtil.getLock(lockKey)) {
					record = mapper.selectById(id);
					CacheUtil.getCache().set(key, record);
					CacheUtil.getCache().del(lockKey);
				} else {
					sleep(20);
					return queryById(id);
				}
			}
			return record;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public Page<T> query(Map<String, Object> params) {
		Page<Long> page = getPage(params);
		page.setRecords(mapper.selectIdPage(page, params));
		return getPage(page);
	}

	public Page<Map<String, Object>> queryMap(Map<String, Object> params) {
		Page<Long> page = getPage(params);
		page.setRecords(mapper.selectIdPage(page, params));
		return getPageMap(page);
	}

	public List<T> queryList(Map<String, Object> params) {
		List<Long> ids = mapper.selectIdPage(params);
		List<T> list = getList(ids);
		return list;
	}

	protected <P> Page<P> query(Map<String, Object> params, Class<P> cls) {
		Page<Long> page = getPage(params);
		page.setRecords(mapper.selectIdPage(page, params));
		return getPage(page, cls);
	}

	protected void sleep(int millis) {
		try {
			Thread.sleep(RandomUtils.nextLong(10, millis));
		} catch (InterruptedException e) {
			logger.error("", e);
		}
	}

	/** 获取缓存键值 */
	protected String getCacheKey(Object id) {
		String cacheName = getCacheKey();
		return new StringBuilder(Constants.CACHE_NAMESPACE).append(cacheName).append(":").append(id).toString();
	}

	/** 获取缓存键值 */
	protected String getLockKey(Object id) {
		String cacheName = getCacheKey();
		return new StringBuilder(Constants.CACHE_NAMESPACE).append(cacheName).append(":LOCK:").append(id).toString();
	}

	/**
	 * 获取缓存键
	 * @return 键KEY
	 */
	private String getCacheKey() {
		Class<?> cls = getClass();
		String cacheName = Constants.CACHEKEYMAP.get(cls);
		if (StringUtils.isBlank(cacheName)) {
			CacheConfig cacheConfig = cls.getAnnotation(CacheConfig.class);
			if (cacheConfig == null || cacheConfig.cacheNames() == null || cacheConfig.cacheNames().length < 1) {
				cacheName = getClass().getName();
			} else {
				cacheName = cacheConfig.cacheNames()[0];
			}
			Constants.CACHEKEYMAP.put(cls, cacheName);
		}
		return cacheName;
	}
}
