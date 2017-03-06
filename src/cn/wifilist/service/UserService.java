package cn.wifilist.service;

import java.sql.SQLException;
import java.util.List;

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
}
