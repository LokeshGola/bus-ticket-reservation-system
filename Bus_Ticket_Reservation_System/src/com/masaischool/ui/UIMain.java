package com.masaischool.ui;

import java.util.Scanner;

public class UIMain {
	
	public static void displayAdminMenu(Scanner sc) {
		System.out.println("\n");
		System.out.println("  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("  ^     Welcome Admin...!                                      ^");
		System.out.println("  ^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~^");
		System.out.println("  ^     1. Add bus                                             ^");
		System.out.println("  ^     2. Update bus                                          ^");
		System.out.println("  ^     3. Delete bus                                          ^");
		System.out.println("  ^     4. View all bookings                                   ^");
		System.out.println("  ^     5. View bookings for a date range                      ^");
		System.out.println("  ^     6. View bookings by bus name                           ^");
		System.out.println("  ^     7. View bookings by mobile number of a passenger       ^");
		System.out.println("  ^     0. Exit                                                ^");
		System.out.println("  ^     Entre Selection :                                      ^");
		System.out.println("  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
	public static void adminMenu(Scanner sc) {
		String choice = null;
		do {
			displayAdminMenu(sc);
			choice = sc.next();
			switch(choice) {
			case "1":
				BusUI.addBus(sc);
				break;
			case "2":
				BusUI.updateBus(sc);
				break;
			case "3":
				BusUI.deleteBus(sc);
				break;
			case "4":
				BusUI.viewAllBookings();
				break;
			case "5":
				BusUI.viewBookingsForDateRange(sc);
				break;
			case "6":
				BusUI.viewBookingsByBusName(sc);
				break;
			case "7":
				BusUI.viewBookingsByMobileNumber(sc);
				break;
			case "0":
				System.out.println("Thank you admin.");
				break;
			default:
				System.out.println("Invalid selection,try again");
				break;
			}
		}while(!choice.equals("0"));
	}
	public static void adminLogin(Scanner sc) {
		System.out.println("Entre username:");
		String username = sc.next();
		System.out.println("Entre password:");
		String password = sc.next();
		if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("root")) {
			adminMenu(sc);
		}else {
			System.out.println("Invalid username or password, try again");
		}
	}
	public static void displayCustomerMenu(Scanner sc) {
		System.out.println("\n");
		System.out.println("  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
		System.out.println("  ^     Welcome User...!                 ^ ");
		System.out.println("  ^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~^ ");
		System.out.println("  ^     1. View bus list                 ^ ");
		System.out.println("  ^     2. Book ticket                   ^ ");
		System.out.println("  ^     3. Cancel ticket                 ^ ");
		System.out.println("  ^     4. View booking history          ^ ");
		System.out.println("  ^     5. Update customer               ^ ");
		System.out.println("  ^     6. Delete account                ^ ");
		System.out.println("  ^     0. Log out                       ^ ");
		System.out.println("  ^     Entre Selection :                ^ ");
		System.out.println("  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
	}
	public static void customerMenu(Scanner sc) {
		String choice = null;
		do {
			displayCustomerMenu(sc);
			choice = sc.next();
			switch(choice) {
			case "1":
				CustomerUI.viewBusList();
				break;
			case "2":
				CustomerUI.bookTicket(sc);
				break;
			case "3":
				CustomerUI.cancelTicket(sc);
				break;
			case "4":
				CustomerUI.getBookingHistory();
				break;
			case "5":
				CustomerUI.updateCustomer(sc);
				break;
			case "6":
				CustomerUI.deleteAccount();
				CustomerUI.logout();     // After deleting the account, called the log out method
				break;
			case "0":
				System.out.println("Thank you user. have a good day.");
				CustomerUI.logout();
				break;
			default:
				System.out.println("Invalid selection,try again");
				break;
			}
		}while(!choice.equals("0"));
	}
	public static void customerLogin(Scanner sc) {
		if(CustomerUI.login(sc)) {
			customerMenu(sc);
		}
		else {
			System.out.println("Invalid username or password, try again");
		}
	}
	public static void customerSignup(Scanner sc) {
		CustomerUI.signup(sc);
	}
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("\n");
		System.out.println("  ============================================================");
		System.out.println("  ((     Welcome to Atlantic Express Bus Reservation...!    ((");
		System.out.println("  ))                                                        ))");
		System.out.println("  ============================================================");
		String choice = null;
		do {
			System.out.println("  ==============================");
			System.out.println("  ||  1. Admin Login          ||");
			System.out.println("  ||  2. Customer Login       ||");
			System.out.println("  ||  3. Customer Sign up     ||");
			System.out.println("  ||  0. Exit                 ||");
			System.out.println("  ||  Entre Selection :       ||");
			System.out.println("  ==============================");
			choice = sc.next();
			switch(choice) {
			case "1":
				adminLogin(sc);
				break;
			case "2":
				customerLogin(sc);
				break;
			case "3":
				customerSignup(sc);
				break;
			case "0":
				System.out.println("Thank you, visit again.");
				break;
			default:
				System.out.println("Invalid selection,try again");
				break;
			}
		}while(!choice.equals("0"));
		
		sc.close();
	}
}
