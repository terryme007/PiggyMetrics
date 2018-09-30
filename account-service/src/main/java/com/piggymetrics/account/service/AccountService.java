package com.piggymetrics.account.service;

import com.sunyard.sunfintech.core.exception.BusinessException;
import com.piggymetrics.account.dao.entity.AccountSubjectInfo;

import java.util.List;

public interface AccountService {

	/**
	 * Checks if account with the same name already exists
	 * Invokes Auth Service user creation
	 * Creates new account with default parameters
	 *
	 * @param platcust
	 * @return created account
	 */
	public void createAccount(String platcust) throws BusinessException;

	public List<AccountSubjectInfo> queryAccount(String platcust)throws BusinessException;
}
