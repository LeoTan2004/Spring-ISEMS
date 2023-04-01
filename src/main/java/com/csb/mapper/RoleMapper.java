package com.csb.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.csb.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Leo
 * @description 针对表【t_role】的数据库操作Mapper
 * @createDate 2023-03-16 10:10:43
 * @Entity pojo.Role
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> getByTid(@Param("tid") long id,@Param("offset")long offset);

    Role getByTidAndRoleName(@Param("tid") long id, @Param("role") String roleName);

    List<Role> getByPermission(@Param("permission") String permission,@Param("offset")long offset);

    List<Role> getByUser(@Param("uid") Long userId,@Param("offset")long offset);
}




