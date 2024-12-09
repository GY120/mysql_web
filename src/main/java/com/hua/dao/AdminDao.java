package com.hua.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import com.hua.entity.Admin;
import com.hua.utils.PropertiesUtils;

public class AdminDao {
    // 实现 admin 表增删改查 sql
    public Admin loginAdmin(Admin admin) {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "select * from admin where username = ? and password = ?";
        try {
            Admin entityAdmin = queryRunner.query(
                sql, 
                new BeanHandler<>(Admin.class), 
                admin.getUsername(), 
                admin.getPassword()
            );
            return entityAdmin;
        } catch (Exception e) {
            e.printStackTrace(); 
            return null;
        }
    }
    
    public boolean addAdmin(Admin admin) throws SQLException {
        String sql = "INSERT INTO admin (username, password) VALUES (?, ?)";
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        int rows = queryRunner.update(sql, admin.getUsername(), admin.getPassword());
        return rows > 0;  // 如果插入成功，返回 true
    }

	public Admin check(Admin admin) {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from admin where username = ?";
		try {
			Admin entityPassword = queryRunner.query(sql, new BeanHandler<>(Admin.class),admin.getUsername());
		    return entityPassword; 
		} catch (Exception e) {
		    e.printStackTrace(); 
		    return null;
		}
	}


    
    
}
