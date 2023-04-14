package com.csb;

import com.csb.module.role.RoleDO;
import com.csb.module.team.TeamDO;
import com.csb.module.team.TeamStatusEnum;
import com.csb.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringIsemsApplicationTests {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @Autowired
    TeamService teamService;

    @Autowired
    MonitorService monitorService;

    @Autowired
    RoleService roleService;

    @Test
    public void testForService() {
        TeamDO teamDO = new TeamDO(1L, TeamStatusEnum.TEAM_STATUS_OPEN, "wuhuqifeiluo", "wuhuqifeihuhuhu");
        teamService.save(teamDO);


    }


}
