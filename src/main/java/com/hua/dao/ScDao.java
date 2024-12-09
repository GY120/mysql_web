package com.hua.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.hua.entity.Sc;
import com.hua.utils.PropertiesUtils;

public class ScDao {

	public List<Sc> findAllScInfo(int stat,int limitNumber) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from sc LIMIT ?,?";
		List<Sc> list = queryRunner.query(sql, new BeanListHandler<>(Sc.class),stat,limitNumber);
        return list;
        
	}
	
	public int getallSccount() throws SQLException{
		QueryRunner queryRunner =new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from sc";
		List<Sc> list =queryRunner.query(sql,new BeanListHandler<>(Sc.class));
		return list.size();
	}
	
	public void sc_add(Sc sc)throws SQLException{
		//外键约束必须 不能给没有sno的 和没有cno添加
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "insert into sc(sno,cno,grade) values(?,?,?)";
		queryRunner.update(sql,sc.getSno(),sc.getCno(),sc.getGrade());
	}
	
	
	
	public int sc_update(Sc sc) throws SQLException {
	    QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
	    //sc表两个主键sno 和 cno 必须指定where sno=? and cno=?"
	    String sql = "update sc set grade=? where sno=? and cno=?";
	    return queryRunner.update(sql, sc.getGrade(),sc.getSno(),sc.getCno());
	}

	public int sc_delete(Sc sc) throws SQLException {
	    QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
	    String sql = "DELETE FROM sc WHERE sno=?";   
	    return queryRunner.update(sql,sc.getSno());
	}
}
