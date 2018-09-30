package com.piggymetrics.user.serviceImpl;

import com.piggymetrics.user.client.AccountServiceClient;
import com.piggymetrics.user.client.AuthServiceClient;
import com.piggymetrics.user.model.AccountSubjectInfo;
import com.piggymetrics.user.repository.UserRepository;
import com.sunyard.sunfintech.core.exception.BusinessMsg;
import com.piggymetrics.user.domain.User;
import com.piggymetrics.user.service.IAccountService;
import com.sunyard.sunfintech.core.base.BaseResponse;
import com.sunyard.sunfintech.core.dic.OrderStatus;
import com.sunyard.sunfintech.core.exception.BusinessException;
import com.sunyard.sunfintech.core.util.SeqUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by terry on 2018/9/13.
 */
@Service
public class AccountServiceImpl implements IAccountService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthServiceClient authClient;

    @Autowired
    private AccountServiceClient accountServiceClient;

    @Autowired
    private UserRepository repository;

    @Override
    public List<AccountSubjectInfo> findByName(String name) {
        logger.info("【用户注册】获取用户信息|用户信息：{}",name);
        //查询user信息
        Optional<User> userOptional = repository.findById(name);
        if(!userOptional.isPresent()){
            throw new BusinessException(BusinessMsg.INVAILD_ACCOUNT,BusinessMsg.getMsg(BusinessMsg.INVALID_ACCOUNT));
        }
        List<AccountSubjectInfo> accountSubjectInfos=accountServiceClient.queryAccount(userOptional.get().getPlatcust());
        if(accountSubjectInfos==null || accountSubjectInfos.size()==0){
            //创建accountSubjectInfo账户数据
            throw new BusinessException(BusinessMsg.INVAILD_ACCOUNT,BusinessMsg.getMsg(BusinessMsg.INVALID_ACCOUNT));
        }
        return accountSubjectInfos;
    }

    @Override
    public String create(User user) {
        logger.info("【用户注册】开始注册|用户信息：{}",user.toString());
        BaseResponse errorBaseResponse;
        //创建user数据
        String platcust= SeqUtil.getSeqNum();
        user.setPlatcust(platcust);
        BaseResponse authBaseResponse=authClient.createUser(user);
        if(OrderStatus.SUCCESS.getCode().equals(authBaseResponse.getOrder_status())){
            //创建accountSubjectInfo账户数据
            BaseResponse accountBaseResponse=accountServiceClient.createAccount(platcust);
            if(OrderStatus.SUCCESS.getCode().equals(accountBaseResponse.getOrder_status())){
                return platcust;
            }else{
                errorBaseResponse=accountBaseResponse;
            }
        }else{
            errorBaseResponse=authBaseResponse;
        }
        throw new BusinessException(errorBaseResponse);
    }
}
