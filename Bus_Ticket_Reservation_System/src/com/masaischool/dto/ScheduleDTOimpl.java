package com.masaischool.dto;

import java.time.LocalDateTime;

public class ScheduleDTOimpl implements ScheduleDTO{
	private String source;
	private String destination;
	private LocalDateTime departureTime;
	private LocalDateTime  arrivalTime;
	private BusDTO bus;
	public ScheduleDTOimpl(String source, String destination, LocalDateTime departureTime, LocalDateTime arrivalTime,
			BusDTO bus) {
		super();
		this.source = source;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.bus = bus;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public LocalDateTime getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}
	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public BusDTO getBus() {
		return bus;
	}
	public void setBus(BusDTO bus) {
		this.bus = bus;
	}
	@Override
	public String toString() {
		return "Schedule: source= " + source + ", destination= " + destination + ", departureTime= " + departureTime
				+ ", arrivalTime= " + arrivalTime + ", bus= " + bus + "";
	}
	
}
