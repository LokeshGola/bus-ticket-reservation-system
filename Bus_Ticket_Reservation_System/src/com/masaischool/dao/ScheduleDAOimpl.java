package com.masaischool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.masaischool.dto.ScheduleDTO;
import com.masaischool.exception.NoRecordFoundException;
import com.masaischool.exception.SomethingWentWrongException;

public class ScheduleDAOimpl implements ScheduleDAO {

	@Override
	public void addSchedule(ScheduleDTO schDto) throws SomethingWentWrongException ,NoRecordFoundException{
		Connection con = null;	 
		try {
			con =DBUtils.getConnectionToDatabase();
			 
			PreparedStatement ps1 = con.prepareStatement("SELECT id FROM bus WHERE bus_id = ? ");
			ps1.setString(1, schDto.getBus().getBusId());   ////// doubt here 
			ResultSet rs= ps1.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No bus id found.");
			}
			rs.next();
			int busId = rs.getInt(1);			
			
			String query= "INSERT INTO schedule (bus_id, source, destination, departure_time, arrival_time ) VALUES (?, ?, ?, ?, ? ) ";
			PreparedStatement ps = con.prepareStatement(query); 
			ps.setInt(1, busId);
			ps.setString(2, schDto.getSource());
			ps.setString(3, schDto.getDestination());
			ps.setTimestamp(4, Timestamp.valueOf(schDto.getDepartureTime()));
			ps.setTimestamp(5, Timestamp.valueOf(schDto.getArrivalTime()));
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
			throw new SomethingWentWrongException("unable to add schedule.");
		}finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
//				e.printStackTrace();
			}
		}
	}
	
}
