package com.piggymetrics.account.dao.mapper;

import com.piggymetrics.account.dao.entity.AccOpenconfig;
import com.piggymetrics.account.dao.entity.AccOpenconfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccOpenconfigMapper {
    /**
     *
     * @mbggenerated 2018-09-12
     */
    int countByExample(AccOpenconfigExample example);

    /**
     *
     * @mbggenerated 2018-09-12
     */
    int deleteByExample(AccOpenconfigExample example);

    /**
     *
     * @mbggenerated 2018-09-12
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-09-12
     */
    int insert(AccOpenconfig record);

    /**
     *
     * @mbggenerated 2018-09-12
     */
    int insertSelective(AccOpenconfig record);

    /**
     *
     * @mbggenerated 2018-09-12
     */
    List<AccOpenconfig> selectByExample(AccOpenconfigExample example);

    /**
     *
     * @mbggenerated 2018-09-12
     */
    AccOpenconfig selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-09-12
     */
    int updateByExampleSelective(@Param("record") AccOpenconfig record, @Param("example") AccOpenconfigExample example);

    /**
     *
     * @mbggenerated 2018-09-12
     */
    int updateByExample(@Param("record") AccOpenconfig record, @Param("example") AccOpenconfigExample example);

    /**
     *
     * @mbggenerated 2018-09-12
     */
    int updateByPrimaryKeySelective(AccOpenconfig record);

    /**
     *
     * @mbggenerated 2018-09-12
     */
    int updateByPrimaryKey(AccOpenconfig record);
}