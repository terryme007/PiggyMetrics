package com.piggymetrics.user.controller;

import com.alibaba.fastjson.JSON;
import com.piggymetrics.user.domain.SysPermission;
import com.piggymetrics.user.domain.User;
import com.piggymetrics.user.model.AccountSubjectInfo;
import com.piggymetrics.user.service.IAccountService;
import com.sunyard.sunfintech.core.base.BaseResponse;
import com.sunyard.sunfintech.core.dic.OrderStatus;
import com.sunyard.sunfintech.core.exception.BusinessException;
import com.sunyard.sunfintech.core.exception.BusinessMsg;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AccountController {

	private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IAccountService accountService;

	@RequestMapping(path = "/current", method = RequestMethod.GET)
	public List<AccountSubjectInfo> getCurrentAccount(HttpServletRequest request,Principal principal) {
		logger.info(request.getRequestURI());
		return accountService.findByName(principal.getName());
	}

	@RequestMapping(path = "/register")
	public BaseResponse createNewAccount(@Valid @RequestBody User user) {
		BaseResponse baseResponse=new BaseResponse();
		try{
			String platcust=accountService.create(user);
			baseResponse.setOrder_status(OrderStatus.SUCCESS.getCode());
			baseResponse.setRecode(BusinessMsg.SUCCESS);
			baseResponse.setRemsg(BusinessMsg.getMsg(BusinessMsg.SUCCESS));
		}catch (Exception e){
			logger.error("【用户注册】注册失败|失败原因：{}",e);
			if(e instanceof BusinessException){
				baseResponse=((BusinessException) e).getBaseResponse();
			}else{
				baseResponse.setOrder_status(OrderStatus.FAIL.getCode());
				baseResponse.setRecode(BusinessMsg.FAIL);
				baseResponse.setRemsg("注册失败");
			}
		}

		return baseResponse;
	}

	@RequestMapping(path = "/query_user", method = RequestMethod.POST)
	public User queryUser(String username){
		return null;
	}

	@RequestMapping(path = "/query_permission", method = RequestMethod.POST)
	public List<SysPermission> queryPermission(User user){
		return new ArrayList<>();
	}
}
