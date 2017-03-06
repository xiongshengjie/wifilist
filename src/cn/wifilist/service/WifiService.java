package cn.wifilist.service;

import java.sql.SQLException;
import java.util.List;

import cn.wifilist.dao.WifiDao;
import cn.wifilist.domain.shop;
import cn.wifilist.domain.wifilist;

public class WifiService {
	private WifiDao dao = new WifiDao();
	
	//添加wifi数据
	public int addwifi(shop realshop,List<wifilist> realwifilist,String uid) throws SQLException{
		return dao.addWifi(realshop, realwifilist,uid);
	}

}
