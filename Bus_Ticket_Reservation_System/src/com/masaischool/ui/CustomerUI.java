package com.masaischool.ui;

import java.util.Scanner;

import com.masaischool.dao.CustomerDAO;
import com.masaischool.dao.CustomerDAOimpl;
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
		} catch (SomethingWentWrongException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
