package com.szxs.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.deploy.net.HttpResponse;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import com.szxs.biz.UserBiz;
import com.szxs.biz.impl.UserBizImpl;
import com.szxs.entity.Role;
import com.szxs.entity.User;
import com.szxs.mapper.RoleMapper;
import com.szxs.mapper.UserMapper;
import com.szxs.pager.Pager;
import com.szxs.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserAction {

    @RequestMapping("/login")
    public String login(User user, HttpSession httpSession,String error) {
        SqlSession sqlSession = MybatisUtil.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = mapper.loginUser(user);
        httpSession.setAttribute("user", user1);
        //ActionContext.getContext().getSession().put("user", user1);
        if (null == user1) {
            error = "用户名或者密码错误!";
            return "/error";
        }
        return "jsp/frame";
    }

    UserBiz biz = new UserBizImpl();

    @RequestMapping("/serachUserPage")
    public String userPage(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "5")Integer pageSize, User user, Model model) {
        SqlSession sqlSession = MybatisUtil.openSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        List<Role> roleList = mapper.serachRole();
        System.out.println(pageNo);
        Pager<User> pager = biz.userPage(pageNo, pageSize, user);
        model.addAttribute("roleList", roleList);
        model.addAttribute("pager", pager);
        return "jsp/userlist";
    }

    @RequestMapping("delUserById")
    public String delUserById(User user,HttpServletResponse response) {

        response.setContentType("text/html; charset=UTF-8");
        SqlSession sqlSession = MybatisUtil.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.delUser(user);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (i > 0) {
            writer.print("{\"delResult\":\"true\"}");
            sqlSession.commit();
        } else {
            writer.print("{\"delResult\":\"false\"}");
        }
        writer.flush();
        writer.close();
        return null;
    }

    @RequestMapping("serachUserByid")
    public String serachUserByid(User user,Model model,String name) throws IOException {
        SqlSession sqlSession = MybatisUtil.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User  user1 = mapper.serachUserByid(user);
        model.addAttribute("user",user1);
        if (name != null && name.equals("user")) {
            return "jsp/usermodify";
        }
        return "jsp/userview";
    }
    @RequestMapping("serachUserByid1")
    public String serachUserByid1(HttpServletResponse response) {

        response.setContentType("text/html; charset=UTF-8");
        SqlSession sqlSession = MybatisUtil.openSession();
        RoleMapper mapper1 = sqlSession.getMapper(RoleMapper.class);
        List<Role> roles = mapper1.serachRole();
        String s = JSON.toJSONStringWithDateFormat(roles, "yyyy-MM-dd");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(s);
        writer.flush();
        writer.close();
        return null;
    }

    @RequestMapping("upUser1")
    public String upUser(@ModelAttribute User user, HttpSession session) {
        SqlSession sqlSession = MybatisUtil.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = (User)session.getAttribute("user");
        user.setModifyBy(user1.getId());
        user.setModifyDate(new Date());
        int i = mapper.updateUser(user);
        if (i > 0) {
            sqlSession.commit();
        }
        return "redirect:serachUserPage";
    }

    @RequestMapping("serachUserByCode")
    public String serachByCode(User user, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        SqlSession sqlSession = MybatisUtil.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = mapper.serachByCode(user);
        PrintWriter writer = response.getWriter();
        if (user1 != null) {
            writer.print("{\"userCode\":\"exist\"}");
        } else {
            writer.print("{\"userCode\":\"exist1\"}");
        }
        writer.flush();
        writer.close();
        return null;
    }
    @RequestMapping("saveUser")
    public String saveUser(User user,HttpSession session) {
        SqlSession sqlSession = MybatisUtil.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = (User) session.getAttribute("user");
        user.setCreatedBy(user1.getId());
        user.setCreationDate(new Date());
        int i = mapper.saveUser(user);
        if (i > 0) {
            sqlSession.commit();
        }
        return "redirect:serachUserPage";
    }

    @RequestMapping("serachUserByidandPwd")
    public String serachUserByidandPwd(User user,HttpServletResponse response,HttpServletRequest request,HttpSession session) throws IOException {

        response.setContentType("text/html; charset=UTF-8");
        SqlSession sqlSession = MybatisUtil.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = (User)session.getAttribute("user");
        PrintWriter writer = response.getWriter();
        if (user1 == null) {
            writer.print("{\"result\":\"sessionerror\"}");
            return null;
        }
        if (user1.getUserPassword().equals(user.getUserPassword())) {
            writer.print("{\"result\":\"true\"}");
        } else {
            writer.print("{\"result\":\"false\"}");
        }
        return null;
    }

    @RequestMapping("udpatePwd")
    public String updatePwd(User user,HttpSession session,HttpServletRequest request) {
        SqlSession sqlSession = MybatisUtil.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = (User) session.getAttribute("user");
        user.setId(user1.getId());
        int i = mapper.updatePwd(user);
        if (i > 0) {
            request.getSession().removeAttribute("user");
            sqlSession.commit();
        }
        return "login";
    }
        @RequestMapping("clearSession")
    public String clearSession(HttpSession session) {

            session.removeAttribute("user");
        User user = (User) session.getAttribute("user");
        if (user == null) {
            System.out.println("-------user以清空");
        }
        return "login";
    }

    /**
     * 日期转换
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        System.out.println("日期转换");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
    }
}
