package com.piggymetrics.user.client;

import com.piggymetrics.user.domain.User;
import com.sunyard.sunfintech.core.base.BaseResponse;
import com.sunyard.sunfintech.core.dic.OrderStatus;
import com.sunyard.sunfintech.core.exception.BusinessMsg;

/**
 * Created by terry on 2018/9/14.
 */
public class AuthServiceClientFallback implements AuthServiceClient {
    @Override
    public BaseResponse createUser(User user) {
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setOrder_status(OrderStatus.FAIL.getCode());
        baseResponse.setRecode(BusinessMsg.RUNTIME_EXCEPTION);
        baseResponse.setRemsg(BusinessMsg.getMsg(BusinessMsg.RUNTIME_EXCEPTION));
        return baseResponse;
    }
}
