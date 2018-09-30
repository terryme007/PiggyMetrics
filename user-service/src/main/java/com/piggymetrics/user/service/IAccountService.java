package com.piggymetrics.user.service;

import com.piggymetrics.user.domain.User;
import com.piggymetrics.user.model.AccountSubjectInfo;

import java.util.List;

/**
 * Created by terry on 2018/9/13.
 */
public interface IAccountService  {

    public List<AccountSubjectInfo> findByName(String name);

    public String create(User user);
}
