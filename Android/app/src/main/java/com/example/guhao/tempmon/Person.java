package com.example.guhao.tempmon;

import java.io.Serializable;

public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2519831905295392947L;
	String name;
	String password;
	String adress;
	String gender;
	public Person(String name, String password, String adress, String gender) {
		super();
		this.name = name;
		this.password = password;
		this.adress = adress;
		this.gender = gender;
	}
	public Person(String name, String password) {
		super();
		this.name = name;
		this.password = password;
		this.adress = null;
		this.gender = null;
	}
	
	public Person() {
		super();
		this.name = null;
		this.password = null;
		this.adress = null;
		this.gender = null;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return adress;
	}
	public void setEmail(String email) {
		this.adress = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	@Override
	public String toString() {
		return "Person";
	}
	

}
