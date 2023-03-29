package com.masaischool.ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.masaischool.dao.BusDAO;
import com.masaischool.dao.BusDAOimpl;
import com.masaischool.dto.BusDTO;
import com.masaischool.dto.BusDTOimpl;
import com.masaischool.exception.SomethingWentWrongException;

public class BusUI {
	
	public static void addBus(Scanner sc) {
		
		System.out.println("Entre bus id:");
		String busId = sc.next();
		
		System.out.println("Entre bus name:");
		String busName = sc.next();
		
		System.out.println("Entre bus type:");
		String busType = sc.next();
		
		System.out.println("Entre bus number:");
		String busNumber = sc.next();
		
		System.out.println("Entre total seats:");
		int totalSeats = sc.nextInt();
		
		System.out.println("Entre source:");
		String source = sc.next();
		
		System.out.println("Entre destination:");
		String destination = sc.next();
		
		System.out.println("Entre departure date in dd-mm-yyyy format : ");
		LocalDate dep_date = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd-MM-yyy"));
		System.out.println("Entre departure time in hr:min ");
		LocalTime dep_time= LocalTime.parse(sc.next());
		LocalDateTime departure = LocalDateTime.of(dep_date, dep_time);
		
		
		System.out.println("Entre arrival date in dd-mm-yyyy format :");
		LocalDate arrival_date = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd-MM-yyy"));
		System.out.println("Entre arrival time in hr:min ");
		LocalTime arrival_time= LocalTime.parse(sc.next());
		LocalDateTime arrival = LocalDateTime.of(arrival_date, arrival_time);
		
		BusDTO busDto = new BusDTOimpl(busId, busName, busType, busNumber, totalSeats, source, destination, departure, arrival );
		
		BusDAO busDao = new BusDAOimpl();
		try {
			busDao.addBus(busDto);
			System.out.println("Bus added successfully.");
		} catch (SomethingWentWrongException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}
	public static void updateBus(Scanner sc) {
//		Update bus details (busName , busType & totalSeats only)
		
		System.out.println("Entre bus id:");
		String busId = sc.next();
		
		System.out.println("Entre bus name:");
		String busName = sc.next();
		
		System.out.println("Entre bus type:");
		String busType = sc.next();
		
		System.out.println("Entre total seats:");
		int totalSeats = sc.nextInt();
		
		BusDTO busDto = new BusDTOimpl(busId, busName, busType, null, totalSeats, null, null, null, null);
		
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
		System.out.println("Entre bus id:");
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
	public static void viewAllBookings(Scanner sc) {
		
	}
}
