package com.piggymetrics.user.client;

import com.piggymetrics.user.model.AccountSubjectInfo;
import com.sunyard.sunfintech.core.base.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by terry on 2018/9/14.
 */
@FeignClient(name = "account-service", fallback = AccountServiceClientFallback.class)
public interface AccountServiceClient {

    @RequestMapping(method = RequestMethod.POST, value = "/account/create_account", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse createAccount(String platcust);

    @RequestMapping(method = RequestMethod.POST, value = "/account/query_account", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<AccountSubjectInfo> queryAccount(String platcust);
}
