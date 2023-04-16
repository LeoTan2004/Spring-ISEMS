package com.csb.dto;

import com.csb.module.account.Gender;
import com.csb.module.account.UserInfoDO;
import com.csb.utils.Assert;

import java.sql.Date;

public record UserInfoDTO(String introduction,
                          String nickname,
                          Gender sex,
                          Date birthday,
                          String contact) {

    public UserInfoDO toUserInfo(Long relUid) {
        return new UserInfoDO(relUid,
                introduction, nickname, sex, birthday, contact,
                null, null);

    }
}
