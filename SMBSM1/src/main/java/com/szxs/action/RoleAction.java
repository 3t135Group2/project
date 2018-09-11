package com.szxs.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.deploy.net.HttpResponse;
import com.szxs.entity.Role;
import com.szxs.mapper.RoleMapper;
import com.szxs.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class RoleAction extends ActionSupport {

    @RequestMapping("serachRole")
    public String serachAllRole(HttpServletResponse response) throws IOException {

        response.setContentType("text/html; charset=UTF-8");
        SqlSession sqlSession = MybatisUtil.openSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        List<Role> roles = mapper.serachRole();
        String s = JSON.toJSONStringWithDateFormat(roles, "yyyy-MM-dd");
        PrintWriter writer = response.getWriter();
        writer.print(s);
        writer.flush();
        writer.close();
        return null;
    }

}
