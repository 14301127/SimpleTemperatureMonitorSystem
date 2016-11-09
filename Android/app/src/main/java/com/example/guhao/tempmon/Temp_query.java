package com.example.guhao.tempmon;

import java.io.Serializable;

public class Temp_query implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1142675149845006730L;
	String name;
	String date1;
	String date2;
	public Temp_query(String name, String date1, String date2) {
		super();
		this.name = name;
		this.date1 = date1;
		this.date2 = date2;
	}
	
	public Temp_query() {
		super();
		this.name = null;
		this.date1 = null;
		this.date2 = null;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	public String getDate2() {
		return date2;
	}
	public void setDate2(String date2) {
		this.date2 = date2;
	}
	
	
	@Override
	public String toString() {
		return "Temp_query";
	}
	

}
