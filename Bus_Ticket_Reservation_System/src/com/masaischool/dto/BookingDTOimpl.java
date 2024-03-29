package com.masaischool.dto;

import java.time.LocalDateTime;
import java.util.function.Supplier;

public class BookingDTOimpl implements BookingDTO {
	private String busId;
	private String customerId;
	private LocalDateTime booking_date;  // booked for date;
	private LocalDateTime booked_date;  // booked on date;
	private Supplier<LocalDateTime> currentdate;
	private int numberOfTickets;
	
	public BookingDTOimpl(String busId, String customerId, LocalDateTime booking_date, LocalDateTime booked_date, int numberOfTickets) {
		super();
		this.busId = busId;
		this.customerId = customerId;
		this.booking_date = booking_date;
		this.booked_date = booked_date;
		this.currentdate = () -> LocalDateTime.now();
		this.numberOfTickets= numberOfTickets;
	}
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public LocalDateTime getBooking_date() {
		return booking_date;
	}
	public void setBooking_date(LocalDateTime booking_date) {
		this.booking_date = booking_date;
	}
	public LocalDateTime getBooked_date() {
		return booked_date;
	}
	public void setBooked_date(LocalDateTime booked_date) {
		this.booked_date = booked_date;
	}
	public LocalDateTime getCurrentdate() {
		return currentdate.get();
	}
	public void setCurrentdate(Supplier<LocalDateTime> currentdate) {
		this.currentdate = currentdate;
	}
	public int getNumberOfTickets() {
		return numberOfTickets;
	}
	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}
	@Override
	public String toString() {
		return "Booking:- bus id = " + busId + ", customer id = " + customerId + ", booking date = " + booking_date
				+ ", booked on = " + booked_date +", number of tickets = "+ numberOfTickets; //+ ", currentdate=" + currentdate + "";
	}
	
	
}
