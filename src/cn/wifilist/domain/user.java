package cn.wifilist.domain;

import java.io.Serializable;

public class user implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 角色
	 */
	private String rool;
	/**
	 * 采集个数
	 */
	private String wificount;
	/**
	 * 店铺个数
	 */
	private String shopcount;
	
	
	public String getShopcount() {
		return shopcount;
	}
	public void setShopcount(String shopcount) {
		this.shopcount = shopcount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getRool() {
		return rool;
	}
	public void setRool(String rool) {
		this.rool = rool;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getWificount() {
		return wificount;
	}
	public void setWificount(String wificount) {
		this.wificount = wificount;
	}
	
}
