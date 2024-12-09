package com.hua.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hua.dao.AdminDao;
import com.hua.entity.Admin;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Servlet implementation class Loginservlet
 */
@WebServlet("/login")
public class Loginservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Loginservlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        String salt = "gyro";
        String salt_md5pwd = password + salt;
        
        String final_md5pwd = DigestUtils.md5Hex(salt_md5pwd.getBytes(StandardCharsets.UTF_8));

        System.out.println("前端传过来的MD5密码："+password);
        System.out.println("后端加密的 MD5 密码："+final_md5pwd);

        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            Admin admin = new Admin();
            admin.setUsername(username);
            admin.setPassword(final_md5pwd);

            Admin entity = new AdminDao().loginAdmin(admin);
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            if (entity != null) {
                out.print("200");
            } else {
                out.print("401"); 
            }
            out.close();
        } 
    }
}

