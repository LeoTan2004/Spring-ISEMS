package com.csb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csb.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Leo
 * @description 针对表【t_user】的数据库操作Mapper
 * @createDate 2023-03-16 10:10:43
 * @Entity pojo.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User getByUsername(@Param("name") String username);
    User getByUsernameAndPassword(@Param("name") String username,@Param("pwd") String password);

    List<User> getByRole(@Param("rid") Long roleId, @Param("offset") long offset);

    List<User> getByTid(@Param("tid") Long tid, @Param("offset") long offset);
}




