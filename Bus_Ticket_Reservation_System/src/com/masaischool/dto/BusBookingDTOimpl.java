package com.masaischool.dto;

public class BusBookingDTOimpl implements BusBookingDTO {
	private BusDTO busDto;
	private BookingDTO bookDto;
	public BusBookingDTOimpl(BusDTO busDto, BookingDTO bookDto) {
		super();
		this.busDto = busDto;
		this.bookDto = bookDto;
	}
	public BusDTO getBusDto() {
		return busDto;
	}
	public void setBusDto(BusDTO busDto) {
		this.busDto = busDto;
	}
	public BookingDTO getBookDto() {
		return bookDto;
	}
	public void setBookDto(BookingDTO bookDto) {
		this.bookDto = bookDto;
	}
	@Override
	public String toString() {
		return "Booking : bus name = " + busDto.getBusName()+ ", type = "+ busDto.getBusType() + ", number = "+ busDto.getBusNumber()
		          + ", booking date = " + bookDto.getBooking_date() + ", booked on = "+ bookDto.getBooked_date();
	}
	
}
