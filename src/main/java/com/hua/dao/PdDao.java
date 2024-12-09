package com.hua.dao;

import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import com.hua.entity.Password;
import com.hua.utils.PropertiesUtils;

public class PdDao {

	public Password check(Password password) {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from admin where username = ? and password = ?";
		try {
		    Password entityPassword = queryRunner.query(sql, new BeanHandler<>(Password.class), password.getUsername(),password.getOldpassword());
		    return entityPassword; 
		} catch (Exception e) {
		    e.printStackTrace(); 
		    return null;
		}
	}
	
	public int password_update(Password password) throws SQLException {		
	    QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
	    String sql = "update admin set password=? where username=?";
	    return queryRunner.update(sql,password.getNewpassword(),password.getUsername());
	}
}
