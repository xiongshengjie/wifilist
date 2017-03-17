package cn.wifilist.service;

import java.sql.SQLException;
import java.util.List;

import cn.wifilist.dao.TypeDao;
import cn.wifilist.domain.floor;
import cn.wifilist.domain.type;
import cn.wifilist.exception.ListTypeException;

public class TypeService {
	private TypeDao dao = new TypeDao();
	
	public List<type> ListAllType() throws ListTypeException{
		try {
			return dao.listAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ListTypeException();
		}
	}
	
	public  floor getfloor() throws SQLException{
		return dao.getfloor();
	}
}
