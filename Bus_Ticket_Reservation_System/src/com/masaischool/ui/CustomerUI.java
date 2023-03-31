package com.masaischool.ui;

import java.util.List;
import java.util.Scanner;

import com.masaischool.dao.BookingDAO;
import com.masaischool.dao.BookingDAOimpl;
import com.masaischool.dao.BusDAO;
import com.masaischool.dao.BusDAOimpl;
import com.masaischool.dao.CustomerDAO;
import com.masaischool.dao.CustomerDAOimpl;
import com.masaischool.dto.BusBookingDTO;
import com.masaischool.dto.BusDTO;
import com.masaischool.dto.CustomerDTO;
import com.masaischool.dto.CustomerDTOimpl;
import com.masaischool.exception.NoRecordFoundException;
import com.masaischool.exception.SomethingWentWrongException;

public class CustomerUI {
	public static boolean login(Scanner sc) {
		System.out.println("Entre user name :");
		String username= sc.next();
		
		System.out.println("Entre password :");
		String password= sc.next();
 
		CustomerDAO cusDao = new CustomerDAOimpl();
		try {
			cusDao.login(username, password);
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;		
		
	}
	public static void logout() {
		CustomerDAO cusDao = new CustomerDAOimpl();
		cusDao.logout();
	}
	public static void signup(Scanner sc) {
		System.out.println("Entre customer id :");
		String cusId= sc.next();
		
		System.out.println("Entre first name :");
		String firstname= sc.next();
		
		System.out.println("Entre last name :");
		String lastname= sc.next();
		
		System.out.println("Entre address :");
		String address= sc.next();
		
		System.out.println("Entre mobile number :");
		long mobile= sc.nextLong();
		
		System.out.println("Entre user name :");
		String username= sc.next();
		
		System.out.println("Entre password :");
		String password= sc.next();
		
		CustomerDTO cusDto = new CustomerDTOimpl(cusId, firstname, lastname, address, mobile, username, password);
		
		CustomerDAO cusDao = new CustomerDAOimpl();
		try {
			cusDao.signup(cusDto);
			System.out.println("Congratulations..! Sign-up successful.");
		} catch (SomethingWentWrongException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void viewBusList() {
		BusDAO busDao = new BusDAOimpl();
		try {
			List<BusDTO> list= busDao.getBusList();
			list.forEach(System.out::println);
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
		
	}
	public static void bookTicketByBusNumber(Scanner sc) {
		System.out.println("Entre bus number");
		String busNumber = sc.next();
		
		BookingDAO bookDao = new BookingDAOimpl();
//		bookDao.bookTicketByBusNumber(busNumber);
	}
	
	public static void cancelTicket(Scanner sc) {
		
	}
	public static void updateCustomer(Scanner sc) {
		
		System.out.println("Entre first name :");
		String firstname= sc.next();
		
		System.out.println("Entre last name :");
		String lastname= sc.next();
		
		System.out.println("Entre address :");
		String address= sc.next();
		
		System.out.println("Entre mobile number :");
		long mobile= sc.nextLong();
		
		CustomerDTO cusDto = new CustomerDTOimpl(null, firstname, lastname, address, mobile, null, null);
		
		CustomerDAO cusDao = new CustomerDAOimpl();
		try {
			cusDao.updateCustomer(cusDto);
			System.out.println("Customer details updated successfully");
		} catch (SomethingWentWrongException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}
	public static void getBookingHistory() {
//		List<BusBookingDTO> 
		BookingDAO bookDao = new BookingDAOimpl();
		try {
			List<BusBookingDTO> list = bookDao.getBookingHistory();
			list.forEach(System.out::println);
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}
	public static void deleteAccount() {
		CustomerDAO cusDao = new CustomerDAOimpl() ;
		try {
			cusDao.deleteAccount();
			
		} catch (SomethingWentWrongException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}
}
