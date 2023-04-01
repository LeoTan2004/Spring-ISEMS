package com.csb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 建立权限验证的包装类
 * 包装一些用于用户前线验证的数据
 * 不一定全部会用到,要根据验证验证类型来确定
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserParam {
    private String nickname;//昵称
    private String description;//描述
    private String username;//用户名
    private String password;//密码
    private String newPassword;//密码
    private String captcha;//手机验证码
    private String captcha_pic;//图片验证码
    private String type;//验证类型

}
