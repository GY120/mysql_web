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
import com.hua.dao.CourseDao;
import com.hua.entity.Course;


/**
 * Servlet implementation class courseservlet
 */
@WebServlet("/course")
public class courseservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public courseservlet() {
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
			CourseDao courseDao =new CourseDao();
			int stat = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);
			List<Course> courseinfo;
			try {
				courseinfo = courseDao.findAllCourseInfo(stat, Integer.valueOf(limit));
				Map<String, Object> data= new HashMap<>();
				data.put("code", 0);
				data.put("msg", "ok");
				data.put("count", courseDao.getallcoursecount());
				data.put("data", courseinfo);
				String s = MAPPER.writeValueAsString(data);
				PrintWriter outPrintWriter = response.getWriter();
				outPrintWriter.append(s);
				
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
		
		
		if("add".equals(method)) {
		    Course course =new Course();
		    CourseDao courseDao = new CourseDao();
		    String cno = request.getParameter("cno");
		    String cname = request.getParameter("cname");
		    String credit = request.getParameter("credit");
			course.setCno(cno);
			course.setCname(cname);
			course.setCredit(credit);
			try {
				courseDao.course_add(course);
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
		    Course course =new Course();
		    CourseDao courseDao = new CourseDao();
		    String cno = request.getParameter("cno");
		    String cname = request.getParameter("cname");
		    String credit = request.getParameter("credit");
			course.setCno(cno);
			course.setCname(cname);
			course.setCredit(credit);
			
			try {
				courseDao.course_update(course);
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
			
		    Course course =new Course();
		    CourseDao courseDao = new CourseDao();
		    String cno =request.getParameter("cno");
		    course.setCno(cno);

		    try {
		        courseDao.course_delete(course);
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
