package com.masaischool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.masaischool.dto.SeatsDTO;
import com.masaischool.exception.NoRecordFoundException;
import com.masaischool.exception.SomethingWentWrongException;

public class SeatsDAOimpl implements SeatsDAO {

	@Override
	public void entreSeats(SeatsDTO seatsDto) throws SomethingWentWrongException {
		Connection con = null;	 
		try {
			con =DBUtils.getConnectionToDatabase();
			String query= "INSERT INTO seats (bus_id, seat_no) VALUES (?, ?) ";
			PreparedStatement ps = con.prepareStatement(query); 
			ps.setInt(1, seatsDto.getBusId());
			ps.setInt(2, seatsDto.getSeatNo());
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("unable to add seats.");
		}finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
//				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Integer> getAvailableSeats(String busNumber) throws SomethingWentWrongException, NoRecordFoundException {
		Connection con = null;	 
		try {
			con =DBUtils.getConnectionToDatabase();
//			String query= "INSERT INTO seats (bus_id, seat_no) VALUES (?, ?) ";
//			PreparedStatement ps = con.prepareStatement(query); 
//			ps.setInt(1, seatsDto.getBusId());
//			ps.setInt(2, seatsDto.getSeatNo());
//			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("unable to add seats.");
		}finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
//				e.printStackTrace();
			}
		}
		return null;
	}

	
	
	
}
