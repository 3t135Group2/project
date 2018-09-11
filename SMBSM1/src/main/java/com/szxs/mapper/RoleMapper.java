package com.szxs.mapper;

import com.szxs.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int saveRole(Role role);
    int deleteRole(@Param("id") int id);
    List<Role> serachRole();
}
