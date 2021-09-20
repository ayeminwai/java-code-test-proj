package com.ayeminwai.pc.qr.model;

import java.io.Serializable;
import java.util.HashMap;

import lombok.ToString;

@ToString
public class QRCode implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, Serializable> map = new HashMap();
	
    public QRCode setEVoucherDetailId(Serializable value) {
        this.map.put("1", value);
        return this;
    }
    
    public QRCode setPhoneNo(Serializable value) {
    	this.map.put("2", value);
    	return this;
    }
    
    public QRCode setPromoCode(Serializable value) {
     	this.map.put("3", value);
    	return this;
    }
    
    private Serializable getEVDId() {
        Serializable value = this.map.get("1");
        return value;
    }
    
    private Serializable getPHNO() {
        Serializable value = this.map.get("2");
        return value;
    }
    
    private Serializable getPMCode() {
        Serializable value = this.map.get("3");
        return value;
    }
    
    public String getEVoucherDetailId() {
        Serializable data = this.getEVDId();
        return data == null ? null : (String)data;
    }
    
    public String getPhoneNo() {
        Serializable data = this.getPHNO();
        return data == null ? null : (String)data;
    }
    
    public String getPromoCode() {
        Serializable data = this.getPMCode();
        return data == null ? null : (String)data;
    }
    
    public HashMap<String, Serializable> getValues() {
    	return this.map;
    }
    
    public void setValues(HashMap<String, Serializable> map) {
    	this.map = map;
    }
	
}
