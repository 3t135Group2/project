package com.szxs.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.net.httpserver.Authenticator;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@Controller
public class BillAction extends ActionSupport {

    @RequestMapping("serachAll")
    public String serachAll(Provider provider,Bill bill,String name, HttpSession httpSession, Model model){
        SqlSession sqlSession = MybatisUtil.openSession();
        BillMapper mapper = sqlSession.getMapper(BillMapper.class);
        List<Provider>     providerList1 = mapper.serachPro();
        model.addAttribute("providerList1",providerList1);
        if(name!=null&&name.equals("pro")){
            List<Provider> providerList = mapper.serachPro1(provider);
            model.addAttribute("providerList",providerList);
            return "jsp/providerlist";
        }

        List<Bill>  billList= mapper.serachAll(bill);
        model.addAttribute("billList",billList);
        return "jsp/billlist";
    }
    @RequestMapping("saveBill")
    public  String saveBill(Bill bill,HttpSession session){
        SqlSession sqlSession = MybatisUtil.openSession();
        BillMapper mapper = sqlSession.getMapper(BillMapper.class);
        User user =(User) session.getAttribute("user");
        bill.setCreatedBy(user.getId());
        bill.setCreationDate(new Date());
        int i = mapper.saveBill(bill);
        if(i>0){
            sqlSession.commit();
        }
        return "redirect:/serachAll";
    }
    @RequestMapping("del")
    public  String delBill(Bill bill,HttpServletResponse response){
        SqlSession sqlSession = MybatisUtil.openSession();
        BillMapper mapper = sqlSession.getMapper(BillMapper.class);

        int i = mapper.delBill(bill);
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(i>0){
            out.print("{\"delResult\":\"true\"}");
            sqlSession.commit();
        }else{
            out.print("{\"delResult\":\"false\"}");
        }
        out.flush();
        out.close();
        return null;
    }
    @RequestMapping("serachByIdBill")
    public String serachAllById(String name,Bill bill,Model model){
        System.out.println("s---------"+bill.getId());
        SqlSession sqlSession = MybatisUtil.openSession();
        BillMapper mapper = sqlSession.getMapper(BillMapper.class);
        Bill bill1 = mapper.serachById(bill);
        model.addAttribute("bill",bill1);
        if(name!=null&&name.equals("a")){
            return "jsp/billview";
        }

        return "jsp/billmodify";
    }
    @RequestMapping("updateBillById")
    public String updateBillById(Bill bill,HttpSession httpSession){
        SqlSession sqlSession = MybatisUtil.openSession();
        BillMapper mapper = sqlSession.getMapper(BillMapper.class);
        User user =(User) httpSession.getAttribute("user");
        bill.setModifyBy(user.getId());
        bill.setModifyDate(new Date());
        int i = mapper.updateBillById(bill);
        if(i>0){
            sqlSession.commit();
        }
        return "redirect:/serachAll";
    }


}
