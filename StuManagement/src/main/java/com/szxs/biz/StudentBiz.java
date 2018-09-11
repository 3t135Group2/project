package com.szxs.biz;

import com.szxs.entity.Student;

import java.util.List;

public interface StudentBiz {

    List<Student> serachAllStu();

    int addStu(Student student);
}
