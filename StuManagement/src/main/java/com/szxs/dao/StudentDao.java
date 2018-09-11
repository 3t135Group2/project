package com.szxs.dao;

import com.szxs.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> serachAllStu();
    int addStu(Student student);
}
