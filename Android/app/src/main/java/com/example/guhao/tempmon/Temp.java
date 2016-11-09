package com.example.guhao.tempmon;

import java.io.Serializable;



public class Temp implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1612008033166485381L;
	private String Number;
    private String Time;
    private String UserID;
    public String getNumber() {
        return Number;
    }
    public void setNumber(String Number) {
        this.Number = Number;
    }
    public String getTime() {
        return Time;
    }
    public String getUserID() {
		return UserID;
	}
	public void setTime(String Time) {
        this.Time = Time;
    }
    public void setUserID(String UserID) {
        this.UserID = UserID;
    }
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Temp";
	}
    
}