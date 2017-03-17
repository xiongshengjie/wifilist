package cn.wifilist.service;

import java.sql.SQLException;
import java.util.List;

import cn.wifilist.exception.FindProductByIdException;
import cn.wifilist.dao.UserDao;
import cn.wifilist.domain.user;
import cn.wifilist.exception.ListProductException;

public class UserService {
	private UserDao dao = new UserDao();

	//验证操作
	public user login(String username,String password) throws SQLException{
		user realuser = dao.findUserByUsernameAndPassword(username, password);
		if(realuser != null)
		{
			return realuser;
		}
		return null;
	}
	
	public boolean addUser(user realuser) throws SQLException{
		return dao.addUser(realuser);
	}
	
	// 查找所有用户信息
	public List<user> listAll() throws ListProductException {
		try {
			return dao.listAll();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ListProductException("查询用户失败");
		}
	}
	
	public void resetpassword(String id) throws SQLException{
		dao.resetpassword(id);
	}
	
	public user findProductById(String id) throws FindProductByIdException {
		try {
			return dao.findProductById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindProductByIdException("根据ID查找用户失败");
		}
	}
	
	public void editUser(user p) {
		try {
			dao.editUser(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(String id) {
		try {
			dao.deleteUser(id);
		} catch (SQLException e) {
			throw new RuntimeException("后台系统根据id删除用户信息失败！");
		}
	}

}
