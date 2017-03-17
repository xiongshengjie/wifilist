package cn.wifilist.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.wifilist.domain.floor;
import cn.wifilist.domain.type;
import cn.wifilist.utils.DataSourceUtils;

public class TypeDao {
	public List<type> listAll() throws SQLException {
		String sql = "select * from type";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<type>(type.class));
	}
	
	public floor getfloor() throws SQLException{
		String sql = "select * from floor";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<floor>(floor.class));
	}
}
