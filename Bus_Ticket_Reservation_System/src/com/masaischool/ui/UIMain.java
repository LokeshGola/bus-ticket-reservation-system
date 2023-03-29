package com.masaischool.ui;

import java.util.Scanner;

public class UIMain {
	
	public static void displayAdminMenu(Scanner sc) {
		System.out.println("Welcome Admin...");
		System.out.println("1. Add bus");
		System.out.println("2. Update bus");
		System.out.println("3. Delete bus");
		System.out.println("4. View all bookings");
		
//		System.out.println("5. Update bus");
//		System.out.println("6. Update bus");
//		System.out.println("7. Update bus");
//		System.out.println("8. Update bus");
//		System.out.println("9. Update bus");
//		System.out.println("10. Update bus");
		System.out.println("0. Exit");
		System.out.println("Entre Selection : ");
	}
	public static void adminMenu(Scanner sc) {
		int choice = 0;
		do {
			displayAdminMenu(sc);
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				BusUI.addBus(sc);
				break;
			case 2:
				BusUI.updateBus(sc);
				break;
			case 3:
				BusUI.deleteBus(sc);
				break;
			case 4:
//				BusUI.viewAllBookings(sc);
				break;
			case 5:
				//
				break;
			case 6:
				//
				break;
			case 7:
				//
				break;
			case 8:
				//
				break;
			case 0:
				System.out.println("Thank you, visit again.");
				break;
			default:
				System.out.println("Invalid selection,try again");
				break;
			}
		}while(choice !=0);
	}
	public static void adminLogin(Scanner sc) {
		System.out.println("Entre username:");
		String username = sc.next();
		System.out.println("Entre password:");
		String password = sc.next();
		if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("root")) {
			adminMenu(sc);
		}else {
			System.out.println("Invalid username and password, try again");
		}
	}
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Welcome to Atlantic Express Bus Reservation...!");
		int choice = 0;
		do {
			System.out.println("1. Admin");
			System.out.println("2. Passenger");
			System.out.println("0. Exit");
			System.out.println("Entre Selection : ");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				adminLogin(sc);
				break;
			case 2:
				//
				break;
			case 0:
				System.out.println("Thank you, visit again.");
				break;
			default:
				System.out.println("Invalid selection,try again");
				break;
			}
		}while(choice !=0);
		
		
		sc.close();
	}
}
