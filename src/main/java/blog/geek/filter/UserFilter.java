package blog.geek.filter;

import blog.geek.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录控制
 * @author yuanyang
 * @version 1.0
 */
public class UserFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute("user");//判断当前是否有用户登录
        if (user == null){
            response.sendRedirect("index.jsp"); //如果没有用户登录,则重定向
        }
        filterChain.doFilter(request,response);
    }

    public void destroy() {

    }
}
