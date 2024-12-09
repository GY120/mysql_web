package com.hua.utils;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
public class PropertiesUtils {

	private static BasicDataSource dataSource =new BasicDataSource();
	static {
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/xsgl");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");
	}
	
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	public static void main(String[] args) {
		try {
			Connection connection=dataSource.getConnection();
			if(connection!=null) {
				System.out.println("连接成功");
			} 
		} catch (SQLException e) {

			System.out.println("连接失败");
			
		}	}
}
