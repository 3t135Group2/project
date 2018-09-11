package com.szxs.controller;

import com.szxs.biz.StudentBiz;
import com.szxs.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class StudentController {

    @Resource
    private StudentBiz studentBiz;

    @RequestMapping("serachAll")
    public String serachAll(Model model){
        List<Student> students = studentBiz.serachAllStu();
        model.addAttribute("students",students);
        return "index";
    }
    @RequestMapping("add")
    public String addStu(Student student,Model model){
        studentBiz.addStu(student);
        return "redirect:serachAll";
    }

}
