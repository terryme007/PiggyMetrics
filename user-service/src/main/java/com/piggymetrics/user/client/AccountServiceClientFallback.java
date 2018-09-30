package com.piggymetrics.user.client;

import com.piggymetrics.user.model.AccountSubjectInfo;
import com.sunyard.sunfintech.core.base.BaseResponse;
import com.sunyard.sunfintech.core.dic.OrderStatus;
import com.sunyard.sunfintech.core.exception.BusinessMsg;

import java.util.List;

/**
 * Created by terry on 2018/9/14.
 */
public class AccountServiceClientFallback implements AccountServiceClient {
    @Override
    public BaseResponse createAccount(String platcust) {
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setOrder_status(OrderStatus.FAIL.getCode());
        baseResponse.setRecode(BusinessMsg.RUNTIME_EXCEPTION);
        baseResponse.setRemsg(BusinessMsg.getMsg(BusinessMsg.RUNTIME_EXCEPTION));
        return baseResponse;
    }

    @Override
    public List<AccountSubjectInfo> queryAccount(String platcust) {
        return null;
    }
}
