package com.masaischool.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.masaischool.dto.BusDTO;
import com.masaischool.dto.ScheduleDTO;
import com.masaischool.dto.SeatsDTO;
import com.masaischool.dto.SeatsDTOimpl;
import com.masaischool.exception.NoRecordFoundException;
import com.masaischool.exception.SomethingWentWrongException;

public class BusDAOimpl implements BusDAO {

	@Override
	public void addBus(BusDTO bus, ScheduleDTO sch) throws SomethingWentWrongException, NoRecordFoundException {
		Connection con = null;	 
		try {
			con =DBUtils.getConnectionToDatabase();
			String query= "INSERT INTO bus (bus_id, bus_name, bus_type, bus_number, total_seats) VALUES (?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query); 
			ps.setString(1, bus.getBusId());
			ps.setString(2, bus.getBusName());
			ps.setString(3, bus.getBusType());
			ps.setString(4, bus.getBusNumber());
			ps.setInt(5, bus.getTotalSeats());
			ps.executeUpdate();
			// adding schedule 
			ScheduleDAO schDao = new ScheduleDAOimpl();
			schDao.addSchedule(sch);
			
			// getting bus id of int type;
			PreparedStatement ps1 = con.prepareStatement("SELECT id FROM bus WHERE bus_id = ? ");
			ps1.setString(1, bus.getBusId());
			ResultSet rs= ps1.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No bus id found.");
			}
			int busId = rs.getInt(1);			
			// adding seats
			SeatsDAO seatDao = new SeatsDAOimpl() ;
			for(int i=1;i<=bus.getTotalSeats();i++) {
				SeatsDTO seatsDto = new SeatsDTOimpl(busId, i);  
				seatDao.entreSeats(seatsDto);
			}
			
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
