package com.piggymetrics.account.dao.mapper;

import com.piggymetrics.account.dao.entity.AccountSubjectInfo;
import com.piggymetrics.account.dao.entity.AccountSubjectInfoExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AccountSubjectInfoMapper {
    /**
     *
     * @mbggenerated 2018-09-12
     */
    int countByExample(AccountSubjectInfoExample example);

    /**
     *
     * @mbggenerated 2018-09-12
     */
    int deleteByExample(AccountSubjectInfoExample example);

    /**
     *
     * @mbggenerated 2018-09-12
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-09-12
     */
    int insert(AccountSubjectInfo record);

    /**
     *
     * @mbggenerated 2018-09-12
     */
    int insertSelective(AccountSubjectInfo record);

    /**
     *
     * @mbggenerated 2018-09-12
     */
    List<AccountSubjectInfo> selectByExample(AccountSubjectInfoExample example);

    /**
     *
     * @mbggenerated 2018-09-12
     */
    AccountSubjectInfo selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-09-12
     */
    int updateByExampleSelective(@Param("record") AccountSubjectInfo record, @Param("example") AccountSubjectInfoExample example);

    /**
     *
     * @mbggenerated 2018-09-12
     */
    int updateByExample(@Param("record") AccountSubjectInfo record, @Param("example") AccountSubjectInfoExample example);

    /**
     *
     * @mbggenerated 2018-09-12
     */
    int updateByPrimaryKeySelective(AccountSubjectInfo record);

    /**
     *
     * @mbggenerated 2018-09-12
     */
    int updateByPrimaryKey(AccountSubjectInfo record);
}