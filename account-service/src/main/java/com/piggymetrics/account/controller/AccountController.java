package com.piggymetrics.account.controller;

import com.piggymetrics.account.service.AccountService;
import com.sunyard.sunfintech.core.base.BaseResponse;
import com.sunyard.sunfintech.core.dic.OrderStatus;
import com.sunyard.sunfintech.core.exception.BusinessException;
import com.sunyard.sunfintech.core.exception.BusinessMsg;
import com.piggymetrics.account.dao.entity.AccountSubjectInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AccountService accountService;

	@RequestMapping(path = "/query_account", method = RequestMethod.POST)
	public List<AccountSubjectInfo> queryAccount(@RequestBody String platcust){
		return accountService.queryAccount(platcust);
	}

	@RequestMapping(path = "/create_account", method = RequestMethod.POST)
	public BaseResponse createNewAccount(@RequestBody String platcust) {
		BaseResponse baseResponse=new BaseResponse();
		try{
			accountService.createAccount(platcust);
			baseResponse.setOrder_status(OrderStatus.SUCCESS.getCode());
			baseResponse.setRecode(BusinessMsg.SUCCESS);
			baseResponse.setRemsg(BusinessMsg.getMsg(BusinessMsg.SUCCESS));
		}catch (Exception e){
			logger.error("【用户注册】创建账户|异常原因：{}",e);
			if(e instanceof BusinessException){
				baseResponse=((BusinessException)e).getBaseResponse();
			}else{
				baseResponse.setOrder_status(OrderStatus.FAIL.getCode());
				baseResponse.setRecode(BusinessMsg.RUNTIME_EXCEPTION);
				baseResponse.setRemsg(e.getMessage());
			}
		}

		return baseResponse;
	}
}
