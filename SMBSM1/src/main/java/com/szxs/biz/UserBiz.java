package com.szxs.biz;

import com.szxs.entity.User;
import com.szxs.pager.Pager;

public interface UserBiz {
    Pager<User> userPage(int pageNo, int pageSize, User user);
}
