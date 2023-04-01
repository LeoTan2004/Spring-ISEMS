package com.csb.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.csb.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Leo
 * @description 针对表【t_user_role】的数据库操作Mapper
 * @createDate 2023-03-16 10:10:43
 * @Entity pojo.UserRole
 */

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    List<UserRole> getAllByRoleId(@Param("rid") long roleId, @Param("offset")long offset);

    List<UserRole> getAllByUserId(@Param("uid") long userId,@Param("offset")long offset);

    UserRole getByUserIdAndRoleId(@Param("rid") long roleId, @Param("uid") long userId);
    int deleteByRelRoleIdAndRelRoleId(@Param("rid") long roleId,@Param("uid") long userId);
}




