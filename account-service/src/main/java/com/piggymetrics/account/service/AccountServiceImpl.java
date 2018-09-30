package com.piggymetrics.account.service;

import com.sunyard.sunfintech.core.Constants;
import com.sunyard.sunfintech.core.dic.AccountType;
import com.sunyard.sunfintech.core.exception.BusinessException;
import com.sunyard.sunfintech.core.exception.BusinessMsg;
import com.sunyard.sunfintech.core.util.SeqUtil;
import com.piggymetrics.account.dao.entity.AccOpenconfig;
import com.piggymetrics.account.dao.entity.AccOpenconfigExample;
import com.piggymetrics.account.dao.entity.AccountSubjectInfo;
import com.piggymetrics.account.dao.entity.AccountSubjectInfoExample;
import com.piggymetrics.account.dao.mapper.AccOpenconfigMapper;
import com.piggymetrics.account.dao.mapper.AccountSubjectInfoMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AccountSubjectInfoMapper accountSubjectInfoMapper;

	@Autowired
	private AccOpenconfigMapper accOpenconfigMapper;
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void createAccount(String platcust) throws BusinessException {

		//创建账户
		AccountSubjectInfo accountSubjectInfo=getAccountSubjectInfo(platcust);
		register(accountSubjectInfo);
		logger.info("new account has been created: " + platcust);

	}

	@Override
	public List<AccountSubjectInfo> queryAccount(String platcust) throws BusinessException {
		AccountSubjectInfoExample accountSubjectInfoExample = new AccountSubjectInfoExample();
		AccountSubjectInfoExample.Criteria criteria = accountSubjectInfoExample.createCriteria();
		criteria.andEnabledEqualTo(Constants.ENABLED);
		criteria.andPlatcustEqualTo(platcust);
		return accountSubjectInfoMapper.selectByExample(accountSubjectInfoExample);
	}

	private AccountSubjectInfo getAccountSubjectInfo(String platcust){
		AccountSubjectInfo accountSubjectInfo=new AccountSubjectInfo();
		accountSubjectInfo.setPlatcust(platcust);
		accountSubjectInfo.setPlat_no("Default Plat");
		accountSubjectInfo.setAccount_type(AccountType.Platcust.getCode());
		return accountSubjectInfo;
	}

	private void register(AccountSubjectInfo accountSubjectInfo) throws BusinessException {
		logger.info("开户信息：" + accountSubjectInfo.toString());
		if (StringUtils.isBlank(accountSubjectInfo.getPlat_no())) {
			logger.error("集团平台编号不能为空");
			throw new BusinessException(BusinessMsg.PARAMETER_ERROR, "集团平台编号不能为空");
		}

		if(StringUtils.isBlank(accountSubjectInfo.getAccount_type())) {
			logger.error("账户类型不能为空");
			throw new BusinessException(BusinessMsg.PARAMETER_ERROR, "账户类型不能为空");
		}

		String platcust = accountSubjectInfo.getPlatcust(); //代表集团客户号

		//验证平台编号是否正确
		AccOpenconfigExample accOpenconfigExample = new AccOpenconfigExample();
		accOpenconfigExample.createCriteria().andPlat_noEqualTo(accountSubjectInfo.getPlat_no());
		List<AccOpenconfig> accOpenconfigList = accOpenconfigMapper.selectByExample(accOpenconfigExample);
		if (null == accOpenconfigList || 0 == accOpenconfigList.size()) {
			logger.error("集团平台编号不存在，或者开户控制表中为空");
			throw new BusinessException(BusinessMsg.PARAMETER_ERROR, "集团平台编号不存在，或者开户控制表中为空");
		}

		//如果不是标的账户，全部平台下的账户都开户。否则是开平台下面的账户
		if(!AccountType.Product.getCode().equals(accountSubjectInfo.getAccount_type())){
			//通过集团号获取所有开户信息配置表
			accOpenconfigExample.clear();
			accOpenconfigExample = new AccOpenconfigExample();
			accOpenconfigExample.createCriteria().andMall_noEqualTo(accOpenconfigList.get(0).getMall_no())
					.andAccount_typeIn(Arrays.asList(accountSubjectInfo.getAccount_type().split(",")));
			accOpenconfigList = accOpenconfigMapper.selectByExample(accOpenconfigExample);
		}else{
			accOpenconfigExample.clear();
			accOpenconfigExample = new AccOpenconfigExample();
			accOpenconfigExample.createCriteria().andPlat_noEqualTo(accountSubjectInfo.getPlat_no())
					.andAccount_typeEqualTo(AccountType.Product.getCode());
			accOpenconfigList = accOpenconfigMapper.selectByExample(accOpenconfigExample);
		}

		for (AccOpenconfig accOpenconfig : accOpenconfigList) {
			AccountSubjectInfo accountSubjectInfoFor = new AccountSubjectInfo();
			//查询是否已经开了虚拟账户
			//如果是电子账户，账户类型是04，那么palt_no填的是集团编号
			AccountSubjectInfoExample accountSubjectInfoExample = new AccountSubjectInfoExample();
			accountSubjectInfoExample.createCriteria().andPlat_noEqualTo(accOpenconfig.getPlat_no())
					.andSubjectEqualTo(accOpenconfig.getSubject()).andSub_subjectEqualTo(accOpenconfig.getSub_subject())
					.andPlatcustEqualTo(platcust).andAccount_typeIn(Arrays.asList(accountSubjectInfo.getAccount_type().split(",")));
			List<AccountSubjectInfo> accountSubjectInfoList = accountSubjectInfoMapper.selectByExample(accountSubjectInfoExample);
			//如果没有开过该虚拟户
			//如果是标的账户
			if (0 == accountSubjectInfoList.size() || "03".equals(accountSubjectInfo.getAccount_type())) {
				//设置账户信息
				accountSubjectInfoFor.setN_balance(BigDecimal.ZERO);
				accountSubjectInfoFor.setT_balance(BigDecimal.ZERO);
				accountSubjectInfoFor.setF_balance(BigDecimal.ZERO);

				accountSubjectInfoFor.setPlat_no(accOpenconfig.getPlat_no());
				accountSubjectInfoFor.setAccount_type(accOpenconfig.getAccount_type());
				accountSubjectInfoFor.setSubject(accOpenconfig.getSubject());
				accountSubjectInfoFor.setSub_subject(accOpenconfig.getSub_subject());
				accountSubjectInfoFor.setPlatcust(platcust); //设置集团客户号
				accountSubjectInfoFor.setMall_platcust(SeqUtil.getSeqNum()); //生成一个平台客户号
				accountSubjectInfoFor.setEnabled(Constants.ENABLED);

				accountSubjectInfoFor.setCreate_time(new Date());
				accountSubjectInfoFor.setCreate_by(SeqUtil.RANDOM_KEY);
				accountSubjectInfoFor.setStatus("1");
				accountSubjectInfoMapper.insert(accountSubjectInfoFor);
			}
		}
		//返回集团客户号
		accountSubjectInfo.setPlatcust(platcust);
	}
}
