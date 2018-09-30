package com.piggymetrics.auth.controller;

import com.piggymetrics.auth.domain.User;
import com.piggymetrics.auth.service.UserService;
import com.sunyard.sunfintech.core.base.BaseResponse;
import com.sunyard.sunfintech.core.dic.OrderStatus;
import com.sunyard.sunfintech.core.exception.BusinessException;
import com.sunyard.sunfintech.core.exception.BusinessMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public Principal getUser(Principal principal) {
		return principal;
	}

	@PreAuthorize("#oauth2.hasScope('server')")
	@RequestMapping(method = RequestMethod.POST)
	public BaseResponse createUser(@Valid @RequestBody User user) {
		BaseResponse baseResponse=new BaseResponse();
		try{
			userService.create(user);
			baseResponse.setOrder_status(OrderStatus.SUCCESS.getCode());
			baseResponse.setRecode(BusinessMsg.SUCCESS);
			baseResponse.setRemsg(BusinessMsg.getMsg(BusinessMsg.SUCCESS));
		}catch (Exception e){
			logger.error("【用户注册】创建用户|异常原因：{}",e);
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
