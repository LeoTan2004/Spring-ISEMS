package com.csb.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csb.module.account.UserInfo;
import com.csb.module.account.UserInfoMapper;
import com.csb.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements AccountService {

}
