package com.masaischool.dto;

public class SeatsDTOimpl implements SeatsDTO {
	private int busId;
	private int seatNo;
	public SeatsDTOimpl(int busId, int seatNo) {
		super();
		this.busId = busId;
		this.seatNo = seatNo;
	}
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	@Override
	public String toString() {
		return "Seats bus id= " + busId + ", seat no. = " + seatNo ;
	}
	
}
