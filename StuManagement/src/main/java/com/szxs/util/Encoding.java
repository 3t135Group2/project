package com.szxs.util;


import javax.servlet.*;
import java.io.IOException;

public class Encoding implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain Chain) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html; charset=UTF-8");
        Chain.doFilter(req,res);
    }

    @Override
    public void destroy() {

    }


}
