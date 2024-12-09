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
import com.hua.dao.StudentDao;
import com.hua.entity.Student;
import com.hua.entity.select;

/**
 * Servlet implementation class stu
 */
@WebServlet("/student")
public class stu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public stu() {
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
			StudentDao studentDao =new StudentDao();
			int stat = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);
			List<Student> studentinfo;
			try {
				studentinfo = studentDao.findAllStudnetInfo(stat, Integer.valueOf(limit));
				Map<String, Object> data= new HashMap<>();
				data.put("code", 0);
				data.put("msg", "ok");
				data.put("count", studentDao.getallstudentcount());
				data.put("data", studentinfo);
				String s = MAPPER.writeValueAsString(data);
				PrintWriter outPrintWriter = response.getWriter();
				outPrintWriter.append(s);
				
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
		
		if("searchlist".equals(method)) {
			String key = request.getParameter("key");
			if(page == null || limit == null) {
				page ="1";
				limit = "10";
			}
			StudentDao studentDao =new StudentDao();
			int stat = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);
			List<Student> studentinfo;
			try {
				studentinfo = studentDao.searchAllStudentInfo(stat, Integer.valueOf(limit),key);
				Map<String, Object> data= new HashMap<>();
				data.put("code", 0);
				data.put("msg", "ok");
				data.put("count", studentDao.searchAllStudentcount(key));
				data.put("data", studentinfo);
				String s = MAPPER.writeValueAsString(data);
				PrintWriter outPrintWriter = response.getWriter();
				outPrintWriter.append(s);
				
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
		
		
		if("add".equals(method)) {
			Student student =new Student();
			StudentDao studentDao =new StudentDao();
			String sno =request.getParameter("sno");
			String sname =request.getParameter("sname");
			String ssex =request.getParameter("ssex");
			String sage =request.getParameter("sage");
			String sdept =request.getParameter("sdept");
			student.setSno(sno);
			student.setSname(sname);
			student.setSsex(ssex);
			student.setSage(Integer.valueOf(sage));
			student.setSdept(sdept);
			
			try {
				studentDao.add(student);
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
			Student student =new Student();
			StudentDao studentDao =new StudentDao();
			String sno = request.getParameter("sno");
			String sname = request.getParameter("sname");
			String ssex = request.getParameter("ssex");
			String sage = request.getParameter("sage");
			String sdept = request.getParameter("sdept");
			student.setSno(sno);
			student.setSname(sname);
			student.setSsex(ssex);
			student.setSage(Integer.valueOf(sage));
			student.setSdept(sdept);
			try {
				studentDao.update(student);
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
		    Student student = new Student();
		    StudentDao studentDao = new StudentDao();
		    String sno = request.getParameter("sno");//修改stutable 'sno': data.sno
		    student.setSno(sno);

		    try {
		        studentDao.delete(student);
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
		
		
		if("select".equals(method)) {
			if(page == null || limit == null) {
				page ="1";
				limit = "10";
			}
			StudentDao studentDao =new StudentDao();
			int stat = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);
			List<select> selectinfo;
			try {
				selectinfo = studentDao.findAllselectInfo(stat, Integer.valueOf(limit));
				Map<String, Object> data= new HashMap<>();
				data.put("code", 0);
				data.put("msg", "ok");
				data.put("count", studentDao.getallselecttcount());
				data.put("data", selectinfo);
				String s = MAPPER.writeValueAsString(data);
				PrintWriter outPrintWriter = response.getWriter();
				outPrintWriter.append(s);
				
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
		
		
		///
	}

}
