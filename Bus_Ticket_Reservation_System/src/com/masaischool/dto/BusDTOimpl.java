package com.masaischool.dto;

import java.time.LocalDateTime;

public class BusDTOimpl implements BusDTO {
	
	private String busId;
	private String busName;
	private String busType;
	private String busNumber;
	private int totalSeats;
//	SeatsDTO seats ;
	
//	private String source;
//	private String destination;
//	private LocalDateTime departureTime;
//	private LocalDateTime  arrivalTime;
	
	public BusDTOimpl(String busId, String busName, String busType, String busNumber, int totalSeats) {
		super();
		this.busId = busId;
		this.busName = busName;
		this.busType = busType;
		this.busNumber = busNumber;
		this.totalSeats = totalSeats;
//		this.source = source;
//		this.destination = destination;
//		this.departureTime = departureTime;
//		this.arrivalTime = arrivalTime;
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
//	public String getSource() {
//		return source;
//	}
//	public void setSource(String source) {
//		this.source = source;
//	}
//	public String getDestination() {
//		return destination;
//	}
//	public void setDestination(String destination) {
//		this.destination = destination;
//	}
//	public LocalDateTime getDepartureTime() {
//		return departureTime;
//	}
//	public void setDepartureTime(LocalDateTime departureTime) {
//		this.departureTime = departureTime;
//	}
//	public LocalDateTime getArrivalTime() {
//		return arrivalTime;
//	}
//	public void setArrivalTime(LocalDateTime arrivalTime) {
//		this.arrivalTime = arrivalTime;
//	}
	@Override
	public String toString() {
		return "Bus Id= " + busId + ", bus name= " + busName + ", type= " + busType + ", number= "
				+ busNumber + ", total seats= " + totalSeats ;
	}
	
}
