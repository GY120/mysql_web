package com.hua.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.hua.entity.Student;
import com.hua.entity.select;
import com.hua.utils.PropertiesUtils;


public class StudentDao {
	
	public List<Student> findAllStudnetInfo(int stat,int limitNumber) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from student LIMIT ?,?";
		List<Student> list = queryRunner.query(sql, new BeanListHandler<>(Student.class),stat,limitNumber);
        return list;
        
	}
	
	public int getallstudentcount() throws SQLException{
		QueryRunner queryRunner =new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from student";
		List<Student> list =queryRunner.query(sql,new BeanListHandler<>(Student.class));
		return list.size();
	
	}
	
	public List<select> findAllselectInfo(int stat,int limitNumber) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select student.sno,student.sname,student.ssex,student.sdept,student.sage,course.cno,course.cname,sc.grade from student,sc,course where student.sno=sc.sno and sc.cno=course.cno LIMIT ?,?";
		List<select> list = queryRunner.query(sql, new BeanListHandler<>(select.class),stat,limitNumber);
        return list;
        
	}
	
	public int getallselecttcount() throws SQLException{
		QueryRunner queryRunner =new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select student.sno,student.sname,student.ssex,student.sdept,student.sage,course.cno,course.cname,sc.grade from student,sc,course where student.sno=sc.sno and sc.cno=course.cno";
		List<select> list =queryRunner.query(sql,new BeanListHandler<>(select.class));
		return list.size();
	}
	
	public List<Student> searchAllStudentInfo(int stat, int limitNumber, String key) throws SQLException {
	    QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
	    String sql = "select * from student where sno like ? or sname like ? or ssex like ? or sage like ? or sdept like ? LIMIT ?, ?";
	    List<Student> list = queryRunner.query(sql, new BeanListHandler<>(Student.class), 
	        '%' + key + '%', '%' + key + '%', '%' + key + '%', '%' + key + '%', '%' + key + '%', stat, limitNumber);
	    return list;
	}

	public int searchAllStudentcount(String key) throws SQLException {
	    QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
	    String sql = "select * from student where sno like ? or sname like ? or ssex like ? or sage like ? or sdept like ?";
	    List<Student> list = queryRunner.query(sql, new BeanListHandler<>(Student.class), 
	        '%' + key + '%', '%' + key + '%', '%' + key + '%', '%' + key + '%', '%' + key + '%');
	    return list.size();
	}

	
	
	
	public void add(Student student)throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "insert into student(sno,sname,ssex,sage,sdept) values(?,?,?,?,?)";
		queryRunner.update(sql,student.getSno(),student.getSname(),student.getSsex(),student.getSage(),student.getSdept());
	}
	
	
	
	public int update(Student student) throws SQLException {
	    QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
	    String sql = "update student set sname=?, ssex=?, sage=?, sdept=? where sno=?";
	    
	    //加return!!
	    //add： 通常不需要返回值，因为失败会通过异常体现。
	    //update： where  需要返回受影响的行数，以判断操作是否成功。
	    
	    return queryRunner.update(sql, student.getSname(), student.getSsex(), student.getSage(), student.getSdept(), student.getSno());
	}

	public int delete(Student student) throws SQLException {
	    QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
	    String sql = "DELETE FROM student WHERE sno=?";   
	    return queryRunner.update(sql,student.getSno());
	}
	
	

}
