package com.csb.module.authority;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

    @Select({"select uid, username, phone, email, last_login_time, insert_time, update_time " +
            "from monitor_system.user " +
            "where username=#{username} and password=#{password};"})
    UserDO getByUsernameAndPWD(@Param("username") String username, @Param("password") String pwd);


    @Select({"select uid, username, phone, email, last_login_time, insert_time, update_time from user where username=#{username};"})
    UserDO getByUsername(@Param("username") String username);

    @Select({"select uid, username, phone, email, last_login_time, insert_time, update_time from user where phone=#{phone};"})
    UserDO getByPhone(@Param("phone") Long phone);


    @Select({"select uid, username, phone, email, last_login_time, user.insert_time, user.update_time " +
            "from monitor_system.user where user.uid in " +
            "(select rel_uid from team_user where rel_rid in " +
            "(select rid from role where rel_tid = #{tid})) limit #{offset},500;"})
    List<UserDO> listByTeam(@Param("tid") Long tid, @Param("offset") Long offset);


    @Select({"select uid, username, phone, email, last_login_time, user.insert_time, user.update_time " +
            "from monitor_system.user,monitor_system.team_user" +
            " where rel_uid=uid and rel_rid=#{rid} limit #{offset},500;"})
    List<UserDO> listByRole(@Param("rid") Long rid, @Param("offset") Long offset);

    @Insert({"insert into monitor_system.team_user (rel_rid, rel_uid, insert_time) values (#{rid},#{uid},current_timestamp() )"})
    int linkWithRole(@Param("uid") Long uid, @Param("rid") Long rid);

    @Delete({"delete from monitor_system.team_user where rel_rid=#{rid} and rel_uid=#{uid};"})
    int disLinkWithRole(@Param("uid") Long uid, @Param("rid") Long rid);

    @Update("update monitor_system.user set password = #{password} where uid = #{uid};")
    int updatePassword(@Param("uid") Long uid, @Param("password") String password);

    @Select({"select uid, username, phone, email, last_login_time, insert_time, update_time " +
            "from monitor_system.user " +
            "where uid = #{uid} and password=#{password};"})
    UserDO getByUideAndPWD(@Param("uid") Long uid, @Param("password") String pwd);


}
