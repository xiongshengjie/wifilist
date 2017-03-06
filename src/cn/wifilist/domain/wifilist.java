package cn.wifilist.domain;

import java.io.Serializable;

public class wifilist implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 采集位置
	 */
	private String place;
	/**
	 * wifi名称
	 */
	private String ssid;
	/**
	 * wifi强度
	 */
	private int wifilevel;
	
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public int getWifilevel() {
		return wifilevel;
	}
	public void setWifilevel(int wifilevel) {
		this.wifilevel = wifilevel;
	}
}
