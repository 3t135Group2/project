package com.szxs.biz.impl;

import com.szxs.biz.UserBiz;
import com.szxs.entity.User;
import com.szxs.mapper.UserMapper;
import com.szxs.pager.Pager;
import com.szxs.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class UserBizImpl implements UserBiz {
    @Override
    public Pager<User> userPage(int pageNo, int pageSize, User user) {
        Pager<User> pager = new Pager<User>();
        SqlSession sqlSession = MybatisUtil.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        pager.setPageNo(pageNo);
        pager.setPageSize(pageSize);
        pager.setTotalRows(mapper.serachTotalRows(user));
        pager.setTotalPages((pager.getTotalRows()+pageSize-1)/pageSize);
        pager.setDatas(mapper.serachUser((pageNo-1)*pageSize,pageSize,user));
        return pager;
    }
}
