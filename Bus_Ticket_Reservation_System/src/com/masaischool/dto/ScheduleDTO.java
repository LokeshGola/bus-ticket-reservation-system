package com.masaischool.dto;

import java.time.LocalDateTime;

public interface ScheduleDTO {
	
	public String getSource()  ;
	public void setSource(String source)  ;
	public String getDestination()  ;
	public void setDestination(String destination)  ;
	public LocalDateTime getDepartureTime()  ;
	public void setDepartureTime(LocalDateTime departureTime)  ;
	public LocalDateTime getArrivalTime()  ;
	public void setArrivalTime(LocalDateTime arrivalTime)  ;
	public BusDTO getBus()  ;
	public void setBus(BusDTO bus)  ;
	
}
