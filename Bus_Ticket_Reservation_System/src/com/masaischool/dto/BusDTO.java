package com.masaischool.dto;

import java.time.LocalDateTime;

public interface BusDTO {
	
	public String getBusId()  ;
	public void setBusId(String busId) ; 
	public String getBusName()  ;
	public void setBusName(String busName)  ;
	public String getBusType()  ;
	public void setBusType(String busType)  ;
	public String getBusNumber()  ;
	public void setBusNumber(String busNumber)  ;
	public int getTotalSeats()  ;
	public void setTotalSeats(int totalSeats)  ;
	public String getSource()  ;
	public void setSource(String source)  ;
	public String getDestination()  ;
	public void setDestination(String destination)  ;
	public LocalDateTime getDepartureTime()  ;
	public void setDepartureTime(LocalDateTime departureTime) ; 
	public LocalDateTime getArrivalTime()  ;
	public void setArrivalTime(LocalDateTime arrivalTime)  ;
}
