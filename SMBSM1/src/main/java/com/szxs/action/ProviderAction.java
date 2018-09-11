package com.szxs.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.szxs.entity.Bill;
import com.szxs.entity.Provider;
import com.szxs.entity.User;
import com.szxs.mapper.BillMapper;
import com.szxs.mapper.ProviderMapper;
import com.szxs.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.rmi.CORBA.PortableRemoteObjectDelegate;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
@Controller
public class ProviderAction extends ActionSupport {

     @RequestMapping("serachPro")
    public String serachPros(HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        SqlSession sqlSession = MybatisUtil.openSession();
        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
        List<Provider> providers = mapper.serachPro();
        String s = JSON.toJSONStringWithDateFormat(providers, "yyyy-MM-dd");
        PrintWriter out = response.getWriter();
        if(providers.size()>0){
            out.print(s);
            out.flush();
            out.close();
        }
        return null;
    }
    @RequestMapping("serachProById")
    public String serachById(Provider provider,String name, Model model){
        SqlSession sqlSession = MybatisUtil.openSession();
        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
       Provider provider1= mapper.serachById(provider);
        model.addAttribute("provider",provider1);
        if(name!=null&&name.equals("pro")){
            return "jsp/providermodify";
        }
        return "jsp/providerview";
    }
    @RequestMapping("delProById")
    public String delProById(Provider provider,HttpServletResponse response) throws IOException {

        response.setContentType("text/html; charset=UTF-8");
        SqlSession sqlSession = MybatisUtil.openSession();
        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
        int i = mapper.delPro(provider);
        PrintWriter writer = response.getWriter();
        if(i>0){
            writer.print( "{\"delResult\":\"true\"}");
            sqlSession.commit();
        }else{
            writer.print( "{\"delResult\":\"false\"}");
        }
        return null;
    }
        @RequestMapping("updatePro")
    public String updatePro(Provider provider, HttpSession session){
        SqlSession sqlSession = MybatisUtil.openSession();
        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
        User user =(User) session.getAttribute("user");
        provider.setModifyDate(new Date());
        provider.setModifyBy(user.getId());
        int i = mapper.updatePro(provider);
        if(i>0){
            sqlSession.commit();
        }
        return "redirect:serachAll?name=pro";
    }
    @RequestMapping("savePro")
    public  String savePro(Provider provider,HttpSession session){
        SqlSession sqlSession = MybatisUtil.openSession();
        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
        User user =(User) session.getAttribute("user");
        provider.setCreationDate(new Date());
        provider.setCreatedBy(user.getId());
        int i = mapper.savePro(provider);
        if(i>0){
            sqlSession.commit();
        }
        return "redirect:/serachAll?name=pro";
    }
}
