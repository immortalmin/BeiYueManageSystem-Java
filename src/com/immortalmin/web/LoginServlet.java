package com.immortalmin.web;

import com.immortalmin.pojo.User;
import com.immortalmin.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends BaseServlet {
    UserServiceImpl userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=utf-8");
        User user = new User();
        user.setUsername(request.getParameter("username"));
//        user.setUsername(new String(request.getParameter("username").getBytes("UTF-8"),"UTF-8"));//解决中文乱码
        user.setPwd(request.getParameter("pwd"));
        User userLogin = userService.login(user);
        if(userLogin!=null){
            request.getSession().setAttribute("user",userLogin);
//            request.setAttribute("user",userLogin);
            response.sendRedirect("pages/main.jsp");
        }else {
            request.setAttribute("user", user);
            String msg = "用户名或密码错误";
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("pages/user/login.jsp").forward(request,response);
        }
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect("login");
    }

    protected void defaultAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("user")!=null){
            request.setAttribute("user",request.getSession().getAttribute("user"));
            response.sendRedirect("pages/main.jsp");
        }else{
            response.sendRedirect("pages/user/login.jsp");
        }
    }
}
