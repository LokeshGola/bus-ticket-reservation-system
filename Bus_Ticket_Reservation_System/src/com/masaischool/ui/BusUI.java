package com.masaischool.ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.masaischool.dao.BookingDAO;
import com.masaischool.dao.BookingDAOimpl;
import com.masaischool.dao.BusDAO;
import com.masaischool.dao.BusDAOimpl;
import com.masaischool.dto.BookingDTO;
import com.masaischool.dto.BusDTO;
import com.masaischool.dto.BusDTOimpl;
import com.masaischool.dto.ScheduleDTO;
import com.masaischool.dto.ScheduleDTOimpl;
import com.masaischool.exception.NoRecordFoundException;
import com.masaischool.exception.SomethingWentWrongException;

public class BusUI {
	
	public static void addBus(Scanner sc) {
		
		System.out.println("Enter bus id:");
		String busId = sc.next();
		
		System.out.println("Enter bus name:");
		String busName = sc.next();
		
		System.out.println("Enter bus type:");
		String busType = sc.next();
		
		System.out.println("Enter bus number:");
		String busNumber = sc.next();
		
		System.out.println("Enter total seats:");
		int totalSeats = sc.nextInt();
		
		System.out.println("Enter source:");
		String source = sc.next();
		
		System.out.println("Enter destination:");
		String destination = sc.next();
		
		System.out.println("Enter departure date in dd-mm-yyyy format : ");
		LocalDate dep_date = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd-MM-yyy"));
		System.out.println("Enter departure time in hr:min ");
		LocalTime dep_time= LocalTime.parse(sc.next());
		LocalDateTime departure = LocalDateTime.of(dep_date, dep_time);
		
		
		System.out.println("Enter arrival date in dd-mm-yyyy format :");
		LocalDate arrival_date = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd-MM-yyy"));
		System.out.println("Enter arrival time in hr:min ");
		LocalTime arrival_time= LocalTime.parse(sc.next());
		LocalDateTime arrival = LocalDateTime.of(arrival_date, arrival_time);
		
		BusDTO busDto = new BusDTOimpl(busId, busName, busType, busNumber, totalSeats );
		ScheduleDTO schDto = new ScheduleDTOimpl(source, destination, departure, arrival, busDto) ;
		
		BusDAO busDao = new BusDAOimpl();
		try {
			busDao.addBus(busDto, schDto);
			System.out.println("Bus added successfully.");
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}
	public static void updateBus(Scanner sc) {
//		Update bus details (busName , busType & totalSeats only)
		
		System.out.println("Enter bus id:");
		String busId = sc.next();
		
		System.out.println("Enter bus name:");
		String busName = sc.next();
		
		System.out.println("Enter bus type:");
		String busType = sc.next();
		
		System.out.println("Enter total seats:");
		int totalSeats = sc.nextInt();
		
		BusDTO busDto = new BusDTOimpl(busId, busName, busType, null, totalSeats);
		
		BusDAO busDao = new BusDAOimpl();
		try {
			busDao.updateBus(busDto);
			System.out.println("Bus updated successfully.");
		} catch (SomethingWentWrongException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}	
	}
	public static void deleteBus(Scanner sc) {
		System.out.println("Enter bus id:");
		String busId = sc.next();
		
		BusDAO busDao = new BusDAOimpl();
		try {
			busDao.deleteBus(busId);
			System.out.println("Bus deleted successfully.");
		} catch (SomethingWentWrongException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
	}
	public static void viewAllBookings() {
		BookingDAO bookDao = new  BookingDAOimpl();
		try {
			List<BookingDTO> list = bookDao.getBookingList();
			list.forEach(System.out::println);
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}
	public static void viewBookingsForDateRange(Scanner sc) {
		System.out.println("Enter start date in yyyy-mm-dd format ");
		String startDate = sc.next();
		
		System.out.println("Enter end date in yyyy-mm-dd format ");
		String endDate = sc.next();
		
		BookingDAO bookDao = new  BookingDAOimpl();
		try {
			List<BookingDTO> list = bookDao.getBookingListForDateRange(startDate, endDate);
			list.forEach(System.out::println);
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}
	public static void viewBookingsByBusName(Scanner sc) {
		System.out.println("Enter bus name ");
		String busName = sc.next();
		
		BookingDAO bookDao = new  BookingDAOimpl();
		try {
			List<BookingDTO> list = bookDao.getBookingListByBusName(busName);
			list.forEach(System.out::println);
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}
	public static void viewBookingsByMobileNumber(Scanner sc) {
		System.out.println("Enter mobile number ");
		long mobile = sc.nextLong();
		
		BookingDAO bookDao = new  BookingDAOimpl();
		try {
			List<BookingDTO> list = bookDao.getBookingListByMobileNumber(mobile);
			list.forEach(System.out::println);
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}
}
