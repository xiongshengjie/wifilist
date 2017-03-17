package cn.wifilist.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class shop implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 店铺名称
	 */
	private String shopname;
	/**
	 * 店铺门面照片路径
	 */
	private String imageurl;
	/**
	 * 大厦名称
	 */
	private String buildingname;
	/**
	 * 所在层数
	 */
	private int floor;
	/**
	 * 店铺经度
	 */
	private BigDecimal shoplogitude;
	/**
	 * 店铺纬度
	 */
	private BigDecimal shoplatitude;
	/**
	 * 精度
	 */
	private String accuracy;
	/**
	 * wifi采集人
	 */
	private String username;
	/**
	 * 店铺wifi
	 */
	private String shopwifi;
	/**
	 * 一级
	 */
	private String category1;
	/**
	 * 二级
	 */
	private String category2;
	
	
	public String getCategory1() {
		return category1;
	}
	public void setCategory1(String category1) {
		this.category1 = category1;
	}
	public String getCategory2() {
		return category2;
	}
	public void setCategory2(String category2) {
		this.category2 = category2;
	}
	public String getShopwifi() {
		return shopwifi;
	}
	public void setShopwifi(String shopwifi) {
		this.shopwifi = shopwifi;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public String getBuildingname() {
		return buildingname;
	}
	public void setBuildingname(String buildingname) {
		this.buildingname = buildingname;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public BigDecimal getShoplogitude() {
		return shoplogitude;
	}
	public void setShoplogitude(BigDecimal shoplogitude) {
		this.shoplogitude = shoplogitude;
	}
	public BigDecimal getShoplatitude() {
		return shoplatitude;
	}
	public void setShoplatitude(BigDecimal shoplatitude) {
		this.shoplatitude = shoplatitude;
	}
	public String getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}
}
