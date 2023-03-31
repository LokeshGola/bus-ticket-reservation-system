package com.masaischool.dto;

import java.time.LocalDateTime;
import java.util.function.Supplier;

public interface BookingDTO {
	public String getBusId()  ;
	public void setBusId(String busId) ; 
	public String getCustomerId()  ;
	public void setCustomerId(String customerId);  
	public LocalDateTime getBooking_date() ; 
	public void setBooking_date(LocalDateTime booking_date)  ;
	public LocalDateTime getBooked_date()  ;
	public void setBooked_date(LocalDateTime booked_date) ; 
	public Supplier<LocalDateTime> getCurrentdate()  ;
	public void setCurrentdate(Supplier<LocalDateTime> currentdate) ; 
}
