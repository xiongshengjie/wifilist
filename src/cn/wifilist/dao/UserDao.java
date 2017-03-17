package cn.wifilist.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.wifilist.domain.user;
import cn.wifilist.utils.DataSourceUtils;

public class UserDao {
	//添加用户
	public boolean addUser(user realuser) throws SQLException{
		
		String username = realuser.getUsername();
		String sql = "select * from user where username=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		user result = runner.query(sql, new BeanHandler<user>(user.class),username);
		if(result!=null){
			return false;
		}else{
			sql = "insert into user(username,pwd) values(?,?)";
			int row = runner.update(sql,realuser.getUsername(),realuser.getPassword());
			if(row == 0){
				return false;
			}	
			return true;
		}
	}
	
	//根据用户名与密码查找用户
	public user findUserByUsernameAndPassword(String username, String password) throws SQLException {
		String sql="select * from user where username=? and pwd=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<user>(user.class),username,password);
	}
	
	// 查找所有用户
		public List<user> listAll() throws SQLException {
			String sql = "select * from user";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			return runner.query(sql, new BeanListHandler<user>(user.class));
		}
		
		//后台系统，根据id重置密码
		public void resetpassword(String id) throws SQLException {
			String sql = "update user set pwd='123456' WHERE id = ?";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			runner.update(sql, id);
		}
		
		public user findProductById(String id) throws SQLException {
			String sql = "select * from user where id=?";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			return runner.query(sql, new BeanHandler<user>(user.class), id);
		}
		
		public void editUser(user p) throws SQLException {

			List<Object> obj = new ArrayList<Object>();
			obj.add(p.getPassword());
			String sql  = "update user set pwd=? ";
			sql += " where id=?";
			obj.add(p.getId());
			
			System.out.println(sql);
			
			System.out.println(obj);

			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			runner.update(sql, obj.toArray());

		}
		
		public void deleteUser(String id) throws SQLException {
			String sql = "DELETE FROM user WHERE id = ?";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			runner.update(sql, id);
		}
}
