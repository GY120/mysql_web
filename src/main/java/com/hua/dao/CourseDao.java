package com.hua.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.hua.entity.Course;
import com.hua.utils.PropertiesUtils;

public class CourseDao {
	public List<Course> findAllCourseInfo(int stat,int limitNumber) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from course LIMIT ?,?";
		List<Course> list = queryRunner.query(sql, new BeanListHandler<>(Course.class),stat,limitNumber);
        return list;
        
	}
	
	public int getallcoursecount() throws SQLException{
		QueryRunner queryRunner =new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from course";
		List<Course> list =queryRunner.query(sql,new BeanListHandler<>(Course.class));
		return list.size();
	
	}
	
	public void course_add(Course course)throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "insert into course(cno,cname,credit) values(?,?,?)";
		queryRunner.update(sql,course.getCno(),course.getCname(),course.getCredit());
	}
	
	
	
	public int course_update(Course course) throws SQLException {
	    QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
	    String sql = "update course set cname=?, credit=? where cno=?";
	    return queryRunner.update(sql, course.getCname(),course.getCredit(),course.getCno());
	}

	public int course_delete(Course course) throws SQLException {
	    QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
	    String sql = "DELETE FROM course WHERE cno=?";   
	    return queryRunner.update(sql,course.getCno());
	}

}
