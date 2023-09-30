package com.masaischool.dao;

import java.util.List;

import com.masaischool.dto.BookingDTO;
import com.masaischool.dto.BusBookingDTO;
import com.masaischool.dto.ScheduleDTO;
import com.masaischool.exception.NoRecordFoundException;
import com.masaischool.exception.SomethingWentWrongException;

public interface BookingDAO {
	public List<BookingDTO> getBookingList() throws SomethingWentWrongException ,NoRecordFoundException;
	public List<BookingDTO> getBookingListForDateRange(String startDate, String endDate) throws SomethingWentWrongException ,NoRecordFoundException;
	public List<BookingDTO> getBookingListByBusName(String busName) throws SomethingWentWrongException ,NoRecordFoundException;
	public List<BookingDTO> getBookingListByMobileNumber(long mobile) throws SomethingWentWrongException ,NoRecordFoundException;
	
//	public void bookTicketByBusNumber(String busNumber) throws SomethingWentWrongException ,NoRecordFoundException;
	public List<ScheduleDTO> getSchedule( String date) throws SomethingWentWrongException ,NoRecordFoundException;
	public void bookTicket(ScheduleDTO schDto, String date, int numberOfTickets) throws SomethingWentWrongException, NoRecordFoundException ;

	public List<BusBookingDTO> getBookingHistory() throws SomethingWentWrongException ,NoRecordFoundException;
//	List<ScheduleDTO> getSchedule(String date) throws SomethingWentWrongException, NoRecordFoundException;
	
}
