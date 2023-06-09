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
				list.add( new BookingDTOimpl(rs.getString(1), rs.getString(2), rs.getTimestamp(3).toLocalDateTime() , rs.getTimestamp(4).toLocalDateTime()));
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
				list.add(new BookingDTOimpl(rs.getString(1), rs.getString(2), rs.getTimestamp(3).toLocalDateTime(), rs.getTimestamp(4).toLocalDateTime()));
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
				list.add(new BookingDTOimpl(rs.getString(1), rs.getString(2), rs.getTimestamp(3).toLocalDateTime(), rs.getTimestamp(4).toLocalDateTime()));
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
				list.add(new BookingDTOimpl(rs.getString(1), rs.getString(2), LocalDateTime.parse(rs.getString(3)), LocalDateTime.parse(rs.getString(4))));
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
				throw new NoRecordFoundException("No bus is found.");
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
	public void bookTicket(ScheduleDTO schDto, String date) throws SomethingWentWrongException, NoRecordFoundException {
		Connection con = null;
		try {
			con =DBUtils.getConnectionToDatabase();
			String query = "SELECT bus.id, S.departure_time FROM bus INNER JOIN schedule S ON bus.id = S.bus_id AND bus_number = ? AND DATE(departure_time) = ? AND bus.is_delete = 0 AND S.is_delete = 0 "; 
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, schDto.getBus().getBusNumber());
			ps.setString(2, date);
			
			ResultSet rs= ps.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No bus is found for for this date.");
			}
			rs.next();
			int busId = rs.getInt(1);
			int cusId = LoggedInCustomer.loggedInCustomerId;
			LocalDateTime bookingDate = rs.getTimestamp(2).toLocalDateTime();
			
//			String bookingDate = rs.getString(2);
						
			BookingDTO bookDto= new BookingDTOimpl(null, null, null, null);
			LocalDateTime bookedOn = bookDto.getCurrentdate();
			
			query = "INSERT INTO booking ( bus_id, customer_id, booking_date, booked_on, is_booked ) VALUES (?,?,?,?,?) ";
			ps = con.prepareStatement(query);
			ps.setInt(1, busId);
			ps.setInt(2, cusId);
			ps.setTimestamp(3, Timestamp.valueOf(bookingDate));
			ps.setTimestamp(4, Timestamp.valueOf(bookedOn));
			ps.setInt(5, 1);
			ps.executeUpdate();
			
			// code to decrease the no. of available seats;
			query = "UPDATE bus SET available_seats = available_seats - 1 WHERE id = ? ";
			ps = con.prepareStatement(query);
			ps.setInt(1, busId);
			ps.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
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
				BookingDTO bookDto = new BookingDTOimpl(null, null, rs.getTimestamp(4).toLocalDateTime() , rs.getTimestamp(5).toLocalDateTime());
				
				BusBookingDTO busBookDto = new BusBookingDTOimpl(busDto, bookDto); 
				list.add(busBookDto);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
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
