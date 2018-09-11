package com.szxs.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SessionClear implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        String path = request.getRequestURI();	//当前请求相对url
        String loginUrl = request.getContextPath()+ "/login.action";

        // 1、登陆页面、初始化页面不过滤
        if(path.equals(loginUrl)){
            chain.doFilter(request, response);
            return;
        }
            if(session.getAttribute("user")==null){
                session.invalidate();
                PrintWriter out = response.getWriter();
                out.println("<script language='javascript' type='text/javascript'>");
                out.println("alert('由于你长时间没有操作,导致Session失效!请你重新登录!');window.location.href='login.jsp'");
                out.println("</script>");
            }else{
                chain.doFilter(request, response);
            }
    }

    @Override
    public void destroy() {

    }
}
