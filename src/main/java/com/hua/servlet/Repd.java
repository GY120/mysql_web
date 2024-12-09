package com.hua.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import com.hua.dao.PdDao;
import com.hua.entity.Password;

/**
 * Servlet implementation class Repd
 */
@WebServlet("/update-password")
public class Repd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Repd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String username = request.getParameter("username");
	    String oldpassword = request.getParameter("oldpassword");
	    String newpassword = request.getParameter("newpassword");
	    
        String salt = "gyro";
        
        String salt_oldpassword = oldpassword + salt;
        
        String final_oldpassword = DigestUtils.md5Hex(salt_oldpassword.getBytes(StandardCharsets.UTF_8));

        String salt_newpassword = newpassword + salt;
        
        String final_newpassword = DigestUtils.md5Hex(salt_newpassword.getBytes(StandardCharsets.UTF_8));
        
        System.out.println("前端传过来的 旧密码："+oldpassword);
       
        System.out.println("后端加密的 旧密码："+final_oldpassword);

        System.out.println("前端传过来的 新密码："+newpassword);
        
        System.out.println("后端加密的 新密码："+final_newpassword);
        
	    response.setContentType("application/json;charset=utf-8");
	    if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(oldpassword) && StringUtils.isNotBlank(newpassword)) {
	        Password password = new Password();
	        password.setUsername(username);
	        password.setOldpassword(final_oldpassword);
	        
	        Password entity = new PdDao().check(password);
	        //check检查用户名密码
	        
	        response.setContentType("text/plain");
	        PrintWriter out = response.getWriter();
	        if (entity != null) {
	            Password npd = new Password();
	            PdDao pdDao = new PdDao();
	            npd.setUsername(username);
	            npd.setNewpassword(final_newpassword);            
	            try {
	            	//更新
	                pdDao.password_update(npd);
	                
	                out.print("200");  
	            } catch (SQLException e) {
	                e.printStackTrace();
	                out.print("500");  
	            }
	        } else {
	            out.print("401");  
	        }
	        out.close();
	    }
	}
}