package com.immortalmin.web;

import com.immortalmin.dao.UserDao;
import com.immortalmin.dao.impl.UserDaoImpl;
import com.immortalmin.pojo.User;
import com.immortalmin.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends BaseServlet {
    UserServiceImpl userService = new UserServiceImpl();
    UserDao userDao = new UserDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int curPage = Integer.parseInt(request.getParameter("curPage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        request.setAttribute("userList",userService.getUserList(curPage,pageSize));
        request.setAttribute("curPage",curPage);
        request.setAttribute("pageSize",pageSize);
        request.setAttribute("totalCount",userDao.getTotalCount());
        request.getRequestDispatcher("pages/user/UserList.jsp").forward(request,response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int uid = Integer.parseInt(request.getParameter("uid"));
        String newPwd = request.getParameter("newPwd");
        userDao.updatePwd(uid,newPwd);
    }
}
