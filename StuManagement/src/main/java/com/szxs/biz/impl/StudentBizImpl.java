package com.szxs.biz.impl;

import com.szxs.biz.StudentBiz;
import com.szxs.dao.StudentDao;
import com.szxs.entity.Student;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentBizImpl implements StudentBiz {
    @Resource
    private StudentDao studentDao;

    @Override
    public List<Student> serachAllStu() {
        return studentDao.serachAllStu();
    }

    @Override
    public int addStu(Student student) {
        return studentDao.addStu(student);
    }
}
