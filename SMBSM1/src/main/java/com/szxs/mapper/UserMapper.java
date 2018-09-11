package com.szxs.mapper;

import com.szxs.entity.User;
import com.szxs.pager.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User loginUser(User user);
    List<User> serachUser(@Param("pageNo") int pageNo,@Param("pageSize") int pageSize,@Param("user") User user);
    int serachTotalRows(@Param("user") User  user);
    int delUser(User user);
    User serachUserByid(User user);
    int updateUser(User user);

    int saveUser(User user);

    User serachByCode(User user);

    User serachById(User user);

    int updatePwd(User user);

    void clearSession();

}
