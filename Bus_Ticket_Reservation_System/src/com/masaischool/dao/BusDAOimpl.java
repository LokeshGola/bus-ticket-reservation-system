package com.masaischool.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.masaischool.dto.BusDTO;
import com.masaischool.exception.SomethingWentWrongException;

public class BusDAOimpl implements BusDAO {

	@Override
	public void addBus(BusDTO bus) throws SomethingWentWrongException {
		Connection con = null;	
		try {
			con =DBUtils.getConnectionToDatabase();
			String query= "INSERT INTO bus (bus_id, bus_name, bus_type, bus_number, total_seats, source, "
					+" destination, departure_time, arrival_time) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query); 
			ps.setString(1, bus.getBusId());
			ps.setString(2, bus.getBusName());
			ps.setString(3, bus.getBusType());
			ps.setString(4, bus.getBusNumber());
			
			ps.setInt(5, bus.getTotalSeats());
			ps.setString(6, bus.getSource());
			ps.setString(7, bus.getDestination());
//			ps.setDate(8, Date.valueOf(bus.getDepartureTime()));
//			ps.setDate(9, Date.valueOf(bus.getArrivalTime()));
			ps.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
//			throw new SomethingWentWrongException("unable to add bus.");
		}finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
//				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateBus(BusDTO bus) throws SomethingWentWrongException {
//		Update bus details (busName , busType & totalSeats only)
		Connection con = null;	
		try {
			con =DBUtils.getConnectionToDatabase();
			String query= "UPDATE bus SET bus_name = ?, bus_type = ?, total_seats = ? WHERE bus_id = ? AND is_delete = 0 ";
			PreparedStatement ps = con.prepareStatement(query); 
			ps.setString(1, bus.getBusName());
			ps.setString(2, bus.getBusType());
			ps.setInt(3, bus.getTotalSeats());
			ps.setString(4, bus.getBusId());
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
			throw new SomethingWentWrongException("unable to update bus details.");
		}finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
//				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void deleteBus(String busId) throws SomethingWentWrongException {
		Connection con = null;	
		try {
			con =DBUtils.getConnectionToDatabase();
			String query= "UPDATE bus SET is_delete = 1 WHERE bus_id = ? ";
			PreparedStatement ps = con.prepareStatement(query); 
			ps.setString(1, busId);
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
//			throw new SomethingWentWrongException("unable to delete bus details.");
		}finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
//				e.printStackTrace();
			}
		}
	}
	
	
}
