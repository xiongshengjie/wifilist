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
