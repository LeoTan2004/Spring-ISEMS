package com.csb;

import com.csb.dto.RegisterParam;
import com.csb.service.AccountService;
import com.csb.module.account.UserInfoMapper;
import com.csb.module.authority.UserMapper;
import com.csb.service.UserService;
import com.csb.module.monitor.MonitorMapper;
import com.csb.module.role.RoleMapper;
import com.csb.module.team.TeamMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringIsemsApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserInfoMapper mapper;

    @Autowired
    TeamMapper teamMapper;
    @Autowired
    MonitorMapper monitorMapper;

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @Test
    public void testForService() {
        System.out.println(roleMapper.getByTeam(1L, 0L));
    }


}
