package com.h.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncodeFilter implements Filter {

    private String encode = "utf-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (filterConfig.getInitParameter("encode") != null) {
            encode = filterConfig.getInitParameter("encode");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding(encode);
        response.setCharacterEncoding(encode);
        Object obj = request.getSession().getAttribute("userId");
        if (obj != null) {
            filterChain.doFilter(request, response);
        } else {
            String servletName = request.getServletPath();
            if ("/loginServlet".equals(servletName)) {
                filterChain.doFilter(request, response);
            } else {
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
