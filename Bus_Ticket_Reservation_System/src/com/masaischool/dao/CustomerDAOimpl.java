package com.masaischool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.masaischool.dto.CustomerDTO;
import com.masaischool.exception.NoRecordFoundException;
import com.masaischool.exception.SomethingWentWrongException;

public class CustomerDAOimpl implements CustomerDAO{
	
	@Override
	public void login(String username, String password) throws SomethingWentWrongException, NoRecordFoundException{
		Connection con = null;	 
		try {
			con =DBUtils.getConnectionToDatabase();
			String query= "SELECT id FROM customer WHERE username = ? AND password = ? AND customer.is_delete = 0";
			PreparedStatement ps = con.prepareStatement(query); 
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("Invalid username and password. ");
			}
			rs.next();
			LoggedInCustomer.loggedInCustomerId = rs.getInt(1);
		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
			throw new SomethingWentWrongException("unable to login.");
		}finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
//				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void logout() {
		LoggedInCustomer.loggedInCustomerId = 0;
	}

	@Override
	public void signup(CustomerDTO cusDto) throws SomethingWentWrongException {
		Connection con = null;	 
		try {
			con =DBUtils.getConnectionToDatabase();
			String query= "INSERT INTO customer (cus_id, first_name, last_name, address, mobile, username, password) VALUES (?, ?, ?, ?, ?, ?, ?) ";
			PreparedStatement ps = con.prepareStatement(query); 
			ps.setString(1, cusDto.getCusId());
			ps.setString(2, cusDto.getFirstname());
			ps.setString(3, cusDto.getLastname());
			ps.setString(4, cusDto.getAddress());
			ps.setLong(5, cusDto.getMobile());
			ps.setString(6, cusDto.getUsername());
			ps.setString(7, cusDto.getPassword());
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
			throw new SomethingWentWrongException("unable to sign up,try again.");
		}finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
//				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateCustomer(CustomerDTO cusDto) throws SomethingWentWrongException {
		Connection con = null;	 
		try {
			con =DBUtils.getConnectionToDatabase();
			int id = LoggedInCustomer.loggedInCustomerId;
			String query= "UPDATE customer SET  first_name = ?, last_name = ?, address = ?, mobile = ? WHERE id = ? ";
			PreparedStatement ps = con.prepareStatement(query); 
			ps.setString(1, cusDto.getFirstname());
			ps.setString(2, cusDto.getLastname());
			ps.setString(3, cusDto.getAddress());
			ps.setLong(4, cusDto.getMobile());
			ps.setInt(5, id);
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
			throw new SomethingWentWrongException("Unable to update details.");
		}finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
//				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void deleteAccount() throws SomethingWentWrongException {
		Connection con = null;	 
		try {
			con =DBUtils.getConnectionToDatabase();
			int cusId = LoggedInCustomer.loggedInCustomerId;
			String query= "UPDATE customer SET is_delete = 1 WHERE id = ? ";
			PreparedStatement ps = con.prepareStatement(query); 
			ps.setInt(1, cusId);
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
			throw new SomethingWentWrongException("Unable to delete account.");
		}finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
//				e.printStackTrace();
			}
		}
	}
}
