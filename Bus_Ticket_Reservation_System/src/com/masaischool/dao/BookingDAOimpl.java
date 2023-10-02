package com.masaischool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.masaischool.dto.BookingDTO;
import com.masaischool.dto.BookingDTOimpl;
import com.masaischool.dto.BusBookingDTO;
import com.masaischool.dto.BusBookingDTOimpl;
import com.masaischool.dto.BusDTO;
import com.masaischool.dto.BusDTOimpl;
import com.masaischool.dto.ScheduleDTO;
import com.masaischool.dto.ScheduleDTOimpl;
import com.masaischool.exception.NoRecordFoundException;
import com.masaischool.exception.SomethingWentWrongException;
public class BookingDAOimpl implements BookingDAO {

	@Override
	public List<BookingDTO> getBookingList() throws SomethingWentWrongException, NoRecordFoundException {
		Connection con = null;
		List<BookingDTO> list= new ArrayList<>();
		try {
			con =DBUtils.getConnectionToDatabase();
			String query = "SELECT bus.bus_id, customer.cus_id, booking.booking_date, booking.booked_on FROM booking INNER JOIN bus ON "
					+" bus.id = booking.bus_id INNER JOIN customer ON customer.id = booking.customer_id AND bus.is_delete = 0 "; 
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs= ps.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No booking found.");
			}
			while(rs.next()) {
				list.add( new BookingDTOimpl(rs.getString(1), rs.getString(2), rs.getTimestamp(3).toLocalDateTime() , rs.getTimestamp(4).toLocalDateTime(),0));  // here, i have put 0 here, change it using rs accordingly;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("unable to get booking list.");
		}finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
//				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<BookingDTO> getBookingListForDateRange(String startDate, String endDate) throws SomethingWentWrongException, NoRecordFoundException {
		Connection con = null;
		List<BookingDTO> list= new ArrayList<>();
		try {
			con =DBUtils.getConnectionToDatabase();
			String query = "SELECT bus.bus_id, customer.cus_id, booking.booking_date, booking.booked_on FROM booking INNER JOIN bus ON "
					+" bus.id = booking.bus_id INNER JOIN customer ON customer.id = booking.customer_id AND booking_date BETWEEN ? AND ? AND bus.is_delete = 0 AND customer.is_delete = 0 "; 
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, startDate);
			ps.setString(2, endDate);
			ResultSet rs= ps.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No booking found for this date range.");
			}
			while(rs.next()) {
				list.add(new BookingDTOimpl(rs.getString(1), rs.getString(2), rs.getTimestamp(3).toLocalDateTime(), rs.getTimestamp(4).toLocalDateTime(),0));  // here, i have put 0 here, change it using rs accordingly;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("unable to get booking list.");
		}finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
//				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<BookingDTO> getBookingListByBusName(String busName)
			throws SomethingWentWrongException, NoRecordFoundException {

		Connection con = null;
		List<BookingDTO> list= new ArrayList<>();
		try {
			con =DBUtils.getConnectionToDatabase();
			String query = "SELECT bus.bus_id, C.cus_id, booking.booking_date, booking.booked_on FROM booking INNER JOIN bus ON "
					+" bus.id = booking.bus_id INNER JOIN customer C ON C.id = booking.customer_id AND bus.bus_name = ? AND bus.is_delete = 0 AND C.is_delete = 0 "; 
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, busName);
			
			ResultSet rs= ps.executeQuery();
			if( DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No booking found for this bus name.");
			}
			while(rs.next()) {
				list.add(new BookingDTOimpl(rs.getString(1), rs.getString(2), rs.getTimestamp(3).toLocalDateTime(), rs.getTimestamp(4).toLocalDateTime(),0));  // here, i have put 0 here, change it using rs accordingly;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("unable to get booking list.");
		}finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
//				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<BookingDTO> getBookingListByMobileNumber(long mobile)
			throws SomethingWentWrongException, NoRecordFoundException {

		Connection con = null;
		List<BookingDTO> list= new ArrayList<>();
		try {
			con =DBUtils.getConnectionToDatabase();
			String query = "SELECT bus.bus_id, customer.cus_id, booking.booking_date, booking.booked_on FROM booking INNER JOIN bus ON "
					+" bus.id = booking.bus_id INNER JOIN customer ON customer.id = booking.customer_id AND mobile = ? AND bus.is_delete = 0 AND customer.is_delete = 0 "; 
			PreparedStatement ps = con.prepareStatement(query);
			ps.setLong(1, mobile);
			ResultSet rs= ps.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No booking found for this mobile number.");
			}
			while(rs.next()) {
				list.add(new BookingDTOimpl(rs.getString(1), rs.getString(2), LocalDateTime.parse(rs.getString(3)), LocalDateTime.parse(rs.getString(4)),0));  // here, i have put 0 here, change it using rs accordingly;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("unable to get booking list.");
		}finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
//				e.printStackTrace();
			}
		}
		return list;
	}

//	@Override
//	public void bookTicketByBusNumber(String busNumber) throws SomethingWentWrongException, NoRecordFoundException {
//
//	}
	
	@Override
	public List<ScheduleDTO> getSchedule(String date)	throws SomethingWentWrongException, NoRecordFoundException {
		Connection con = null;
		List<ScheduleDTO> list = new ArrayList<>();
		try {
			con =DBUtils.getConnectionToDatabase();
//			String query = "SELECT bus.bus_number, S.source, S.destination FROM bus INNER JOIN schedule S ON "
//					+" bus.id = S.bus_id AND DATE(departure_time) >= ? AND S.is_delete = 0 AND bus.is_delete = 0";
//			String query = "SELECT bus.bus_number, S.source, S.destination, available_seats, "
//					+" DATE(departure_time), TIME(departure_time) FROM bus INNER JOIN schedule S ON "
//					+" bus.id = S.bus_id AND DATE(departure_time) >= '2023-04-02' AND S.is_delete = 0 AND bus.is_delete = 0  ";
			String query = "SELECT bus.bus_number, S.source, S.destination, available_seats, "
					+" departure_time FROM bus INNER JOIN schedule S ON "
					+" bus.id = S.bus_id AND DATE(departure_time) >= ? AND S.is_delete = 0 AND bus.is_delete = 0  ";
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, date);
			ResultSet rs= ps.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No bus is found for the date "+ date+".");
			}
			while(rs.next()) {
				BusDTO busDto= new BusDTOimpl(null, null, null, rs.getString(1), rs.getInt(4));    
				ScheduleDTO schDto = new ScheduleDTOimpl(rs.getString(2), rs.getString(3),  rs.getTimestamp(5).toLocalDateTime()  ,null, busDto);
				list.add(schDto);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("unable to get bus schedule.");
		}finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
//				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public int bookTicket(ScheduleDTO schDto, String date , int numberOfTickets) throws SomethingWentWrongException, NoRecordFoundException {
		Connection con = null;
		try {
			//finding the bus for the given date and schedule;
			con =DBUtils.getConnectionToDatabase();
			String query = "SELECT bus.id, S.departure_time FROM bus INNER JOIN schedule S ON bus.id = S.bus_id AND bus_number = ? AND DATE(departure_time) = ? AND bus.is_delete = 0 AND S.is_delete = 0 "; 
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, schDto.getBus().getBusNumber());
			ps.setString(2, date);
			
			ResultSet rs= ps.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No bus found for this date.");
			}
			rs.next();
			int busId = rs.getInt(1);
			int cusId = LoggedInCustomer.loggedInCustomerId;
			LocalDateTime bookingDate = rs.getTimestamp(2).toLocalDateTime();
			
//			String bookingDate = rs.getString(2);
			
			//code for checking that available seats are >= number Of Tickets applied;
			query = "SELECT bus.available_seats FROM bus INNER JOIN schedule S ON bus.id = S.bus_id AND bus_number = ? AND DATE(departure_time) = ? AND bus.is_delete = 0 AND S.is_delete = 0 ";
			ps=con.prepareStatement(query);
			ps.setString(1, schDto.getBus().getBusNumber());
			ps.setString(2, date);
			rs= ps.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No seat found for this date.");
			}
			rs.next();
			int availableSeats = rs.getInt(1);
			if(availableSeats < numberOfTickets) {
				throw new NoRecordFoundException("Sorry! "+numberOfTickets+" seats are not available. Only "+ availableSeats+" seats are available.");  // throw some other relevant exception here;
			}
			
			
			//code for booking tickets/or inserting the data into booking table;
			BookingDTO bookDto= new BookingDTOimpl(null, null, null, null, numberOfTickets);
			LocalDateTime bookedOn = bookDto.getCurrentdate();
			
			query = "INSERT INTO booking ( bus_id, customer_id, booking_date, booked_on, is_booked, number_of_tickets ) VALUES (?,?,?,?,?,?) ";
			ps = con.prepareStatement(query);
			ps.setInt(1, busId);
			ps.setInt(2, cusId);
			ps.setTimestamp(3, Timestamp.valueOf(bookingDate));
			ps.setTimestamp(4, Timestamp.valueOf(bookedOn));
			ps.setInt(5, 1);
			ps.setInt(6, numberOfTickets);
			ps.executeUpdate();
			
			// code to decrease the no. of available seats;
			query = "UPDATE bus SET available_seats = available_seats - ? WHERE id = ? ";
			ps = con.prepareStatement(query);
			ps.setInt(1, numberOfTickets);
			ps.setInt(2, busId);
			ps.executeUpdate();
			
			//code to take and return the booking Id;
			query = "SELECT id FROM booking order by id DESC Limit 1";
			ps= con.prepareStatement(query);
			rs= ps.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No booking found. ");
			}
			rs.next();
			int booking_id = rs.getInt(1);
			return booking_id;
			
		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
			throw new SomethingWentWrongException("unable to book ticket.");
		}finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
//				e.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean cancelTicket(int booking_id) throws SomethingWentWrongException, NoRecordFoundException {
		Connection con= null;
		try {
			//code to cancel the booking;
			con = DBUtils.getConnectionToDatabase();
			String query = "Update booking set is_cancelled = 1 where id = ? AND is_cancelled = 0 ";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, booking_id);
			int rowsImpacted = ps.executeUpdate();
			if(rowsImpacted==0) {
				//if the booking is already cancelled but customer tries to cancel it again then it will throw an exception "Booking already cancelled" 
				//and it will not allow the customer to cancel the booking again;
				throw new NoRecordFoundException("Booking already cancelled/booking not found with id: "+booking_id);
			}
			
			//code to take the bus id and number_of_tickets from booking table;
			query = "SELECT bus_id, number_of_tickets FROM booking WHERE id = ? "; // here we don't need to write the condition "is_cancelled = 0" because we have used if condition just above;
			ps = con.prepareStatement(query);
			ps.setInt(1, booking_id);
			ResultSet rs = ps.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No record found");
			}
			rs.next();
			int busId = rs.getInt(1);
			int number_of_tickets = rs.getInt(2); 
			
			// code to increase the number of available seats in bus table;
			int number_of_seats = number_of_tickets;
			query = "Update bus set available_seats = available_seats + ? WHERE id = ? AND is_delete = 0 ";
			ps = con.prepareStatement(query);
			ps.setInt(1, number_of_seats);
			ps.setInt(2, busId);
			ps.executeUpdate();
			return true;
			
		}catch(ClassNotFoundException | SQLException e) {
			throw new SomethingWentWrongException("unable to cancel the booking");
			
		}

	}
	
	@Override
	public List<BusBookingDTO> getBookingHistory() throws SomethingWentWrongException, NoRecordFoundException {
		Connection con = null;
		List<BusBookingDTO> list= new ArrayList<>();
		try {
			con =DBUtils.getConnectionToDatabase();
			int cusId =LoggedInCustomer.loggedInCustomerId;
			
			String query = "SELECT bus_name, bus_type, bus_number, booking_date, booked_on FROM bus INNER JOIN booking ON "
					+" bus.id = booking.bus_id AND customer_id = ? AND bus.is_delete = 0 ";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, cusId);
			ResultSet rs= ps.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No booking history found.");
			}
			while(rs.next()) {
				BusDTO busDto = new BusDTOimpl(null, rs.getString(1), rs.getString(2), rs.getString(3), 0);
				BookingDTO bookDto = new BookingDTOimpl(null, null, rs.getTimestamp(4).toLocalDateTime() , rs.getTimestamp(5).toLocalDateTime(),0);  // here, i have put 0 here, change it using rs accordingly;
				
				BusBookingDTO busBookDto = new BusBookingDTOimpl(busDto, bookDto); 
				list.add(busBookDto);
			}
		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
			throw new SomethingWentWrongException("unable to get booking history.");
		}finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
//				e.printStackTrace();
			}
		}
		return list;
	}

	
}
