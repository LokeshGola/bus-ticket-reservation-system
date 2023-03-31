package com.masaischool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.masaischool.dto.BookingDTO;
import com.masaischool.dto.BookingDTOimpl;
import com.masaischool.dto.BusBookingDTO;
import com.masaischool.dto.BusBookingDTOimpl;
import com.masaischool.dto.BusDTO;
import com.masaischool.dto.BusDTOimpl;
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
					+" bus.id = booking.bus_id INNER JOIN customer ON customer.cus_id = booking.customer_id "; 
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs= ps.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No booking found.");
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

	@Override
	public List<BookingDTO> getBookingListForDateRange(String startDate, String endDate) throws SomethingWentWrongException, NoRecordFoundException {
		Connection con = null;
		List<BookingDTO> list= new ArrayList<>();
		try {
			con =DBUtils.getConnectionToDatabase();
			String query = "SELECT bus.bus_id, customer.cus_id, booking.booking_date, booking.booked_on FROM booking INNER JOIN bus ON "
					+" bus.id = booking.bus_id INNER JOIN customer ON customer.cus_id = booking.customer_id AND booking_date BETWEEN ? AND ? "; 
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, startDate);
			ps.setString(1, endDate);
			ResultSet rs= ps.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No booking found for this date range.");
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

	@Override
	public List<BookingDTO> getBookingListByBusName(String busName)
			throws SomethingWentWrongException, NoRecordFoundException {

		Connection con = null;
		List<BookingDTO> list= new ArrayList<>();
		try {
			con =DBUtils.getConnectionToDatabase();
			String query = "SELECT bus.bus_id, customer.cus_id, booking.booking_date, booking.booked_on FROM booking INNER JOIN bus ON "
					+" bus.id = booking.bus_id INNER JOIN customer ON customer.cus_id = booking.customer_id AND bus_name = ? "; 
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, busName);
			ResultSet rs= ps.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No booking found for this bus name.");
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

	@Override
	public List<BookingDTO> getBookingListByMobileNumber(long mobile)
			throws SomethingWentWrongException, NoRecordFoundException {

		Connection con = null;
		List<BookingDTO> list= new ArrayList<>();
		try {
			con =DBUtils.getConnectionToDatabase();
			String query = "SELECT bus.bus_id, customer.cus_id, booking.booking_date, booking.booked_on FROM booking INNER JOIN bus ON "
					+" bus.id = booking.bus_id INNER JOIN customer ON customer.cus_id = booking.customer_id AND mobile = ? "; 
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

	@Override
	public void bookTicketByBusNumber(String busNumber) throws SomethingWentWrongException, NoRecordFoundException {
		Connection con = null;
		try {
			con =DBUtils.getConnectionToDatabase();
			String query = "SELECT id FROM bus WHERE bus_number = ? AND is_delete = 0 "; 
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, busNumber);
			ResultSet rs= ps.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No bus is found.");
			}
//			rs.next();
//			int busId = rs.getInt(1);
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
	}

	@Override
	public List<BusBookingDTO> getBookingHistory() throws SomethingWentWrongException, NoRecordFoundException {
		Connection con = null;
		List<BusBookingDTO> list= new ArrayList<>();
		try {
			con =DBUtils.getConnectionToDatabase();
			int cusId =LoggedInCustomer.loggedInCustomerId;
			
//			String query = "SELECT bus_id FROM booking WHERE customer_id = ? ";
//			PreparedStatement ps = con.prepareStatement(query);
//			ps.setInt(1, cusId);
//			ResultSet rs= ps.executeQuery();
//			if(DBUtils.isResultSetEmpty(rs)) {
//				throw new NoRecordFoundException("No booking history found.");
//			}
//			rs.next();
//			int busId = rs.getInt(1);
			
			String query = "SELECT bus_name, bus_type, bus_number, booking_date, booked_on FROM bus INNER JOIN booking ON "
					+" bus.id = booking.bus_id AND customer_id = ? ";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, cusId);
			ResultSet rs= ps.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No booking history found.");
			}
			while(rs.next()) {
				BusDTO busDto = new BusDTOimpl(null, rs.getString(1), rs.getString(2), rs.getString(3), 0);
				BookingDTO bookDto = new BookingDTOimpl(null, null, LocalDateTime.parse(rs.getString(4)), LocalDateTime.parse(rs.getString(5)));
				
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
