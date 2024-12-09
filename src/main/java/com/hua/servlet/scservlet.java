package com.hua.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hua.dao.ScDao;
import com.hua.entity.Sc;

/**
 * Servlet implementation class scservlet
 */
@WebServlet("/sc")
public class scservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public scservlet() {
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
	private static final ObjectMapper MAPPER = new ObjectMapper();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String method =request.getParameter("method");
		String page =request.getParameter("page");
		String limit =request.getParameter("limit");
		
		response.setContentType("application/json;charset=utf-8");
		
		if("listpage".equals(method)) {
			if(page == null || limit == null) {
				page ="1";
				limit = "10";
			}
			ScDao scDao =new ScDao();
			int stat = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);
			List<Sc> scinfo;
			try {
				scinfo = scDao.findAllScInfo(stat, Integer.valueOf(limit));
				Map<String, Object> data= new HashMap<>();
				data.put("code", 0);
				data.put("msg", "ok");
				data.put("count", scDao.getallSccount());
				data.put("data", scinfo);
				String s = MAPPER.writeValueAsString(data);
				PrintWriter outPrintWriter = response.getWriter();
				outPrintWriter.append(s);
				
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
		
		
		if("add".equals(method)) {		
			Sc sc =new Sc();
			ScDao scDao =new ScDao();
			String sno = request.getParameter("sno");
			String cno = request.getParameter("cno");
			String grade = request.getParameter("grade");
			sc.setSno(sno);
			sc.setCno(cno);
			sc.setGrade(grade);
			
			try {
				scDao.sc_add(sc);
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("200");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				PrintWriter out = response.getWriter();
				out.print("404");
			}
		}
		
		if("update".equals(method)) {
			Sc sc =new Sc();
			ScDao scDao =new ScDao();
			String sno = request.getParameter("sno");
			String cno = request.getParameter("cno");
			String grade = request.getParameter("grade");
			sc.setSno(sno);
			sc.setCno(cno);
			sc.setGrade(grade);
			
			try {
				scDao.sc_update(sc);
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("200");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				PrintWriter out = response.getWriter();
				out.print("404");
			}
		}
		
		
		if ("delete".equals(method)) {
			
			Sc sc =new Sc();
			ScDao scDao =new ScDao();
			String sno = request.getParameter("sno");
			sc.setSno(sno);
		    try {
		    	scDao.sc_delete(sc);
		        response.setContentType("text/html;charset=UTF-8");
		        PrintWriter out = response.getWriter();
		        out.print("200");  
		    } catch (SQLException e) {
		        e.printStackTrace();
		        response.setContentType("text/html;charset=UTF-8");
		        PrintWriter out = response.getWriter();
		        out.print("404");  
		    }
		}
		
		///
	}

}
