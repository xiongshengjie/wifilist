package cn.wifilist.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import cn.wifilist.utils.DataSourceUtils;
import cn.wifilist.domain.shop;
import cn.wifilist.domain.wifilist;

public class WifiDao {

	//添加wifi记录
	public int addWifi(shop realshop,List<wifilist> realwifilist,String uid) throws SQLException{
		String sql = "insert into shop(id,shopname,imageurl,buildingname,floor,shoplogitude,shoplatitude,accuracy,shopwifi,category1,category2,username) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int correct = 0;
		int cou = runner.update(sql,uid, realshop.getShopname(), realshop.getImageurl(), realshop.getBuildingname(),
				realshop.getFloor(),realshop.getShoplogitude(),realshop.getShoplatitude(),realshop.getAccuracy(),
				realshop.getShopwifi(),realshop.getCategory1(),realshop.getCategory2(),realshop.getUsername());

		if(cou > 0){
			sql = "insert into wifilist(id,place,ssid,wifilevel) values(?,?,?,?)";
			
			String username = realshop.getUsername();
			for(int i=0;i<realwifilist.size();i++){
				wifilist ok = realwifilist.get(i);
				cou = runner.update(sql,uid,ok.getPlace(),ok.getSsid(),ok.getWifilevel());
				if(cou>0){
					correct++;
				}
			}
			
			sql = "update user set shopcount=shopcount+1 where username=?";
			runner.update(sql,username);
			
			sql = "update user set wificount=wificount+"+correct+" where username=?";
			runner.update(sql,username);
			
			return correct;
		}
		return 0;
	}
	
	//查找所有wifi记录
	
}
