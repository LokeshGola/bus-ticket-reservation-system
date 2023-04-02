package com.masaischool.dto;


public class BusDTOimpl implements BusDTO {
	
	private String busId;
	private String busName;
	private String busType;
	private String busNumber;
	private int totalSeats;
	
	public BusDTOimpl(String busId, String busName, String busType, String busNumber, int totalSeats) {
		super();
		this.busId = busId;
		this.busName = busName;
		this.busType = busType;
		this.busNumber = busNumber;
		this.totalSeats = totalSeats;
	}
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public String getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	@Override
	public String toString() {
		return "Bus Id= " + busId + ", bus name= " + busName + ", type= " + busType + ", number= "
				+ busNumber + ", total seats= " + totalSeats ;
	}
	
}
