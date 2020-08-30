package com.moxito.RestaurantReservation;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class App {
	private static Set<Restaurant> resturentList = new TreeSet<Restaurant>(); // for storing the Restaurant

	public static void main(String[] args) {

		Restaurant restaurant = new Restaurant("PizzaHut", 20, 10);
		resturentList.add(restaurant);

		restaurant = new Restaurant("Spigo", 8, 11);
		resturentList.add(restaurant);

		restaurant = new Restaurant("Zest", 5, 10);
		resturentList.add(restaurant);

		printRestaurantList();
		Scanner scanner = new Scanner(System.in);
		boolean quit = false;
		while (!quit) {
			int action;
			try {
				action = scanner.nextInt();
				if (action > resturentList.size()) {
					throw new IndexOutOfBoundsException();
				}
				scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Please Select the Correct Restaurant: ");
				scanner.nextLine();
				continue;

			} catch (IndexOutOfBoundsException e) {
				System.out.println("Please Select the Available Restaurant: ");
				scanner.nextLine();
				continue;
			}

			if (action == 0) {
				System.out.println("Quited from the App");
				quit = true;
				break;
			}
			int count = 1;
			for (Restaurant res : resturentList) {
				if (action == count) {
					System.out.println("Performing operation on: " + res.getResName());
					performOperation(res);
					break;
				}
				count++;
			}
			printRestaurantList();

		}

	}

	// Perform operations on Restaurant
	private static void performOperation(Restaurant res) {
		printOperationRestaurant();
		Scanner scanner = new Scanner(System.in);
		boolean quit = false;
		while (!quit) {
			int action;
			try {
				action = scanner.nextInt();
				if (action > 3) {
					throw new IndexOutOfBoundsException();
				}
				scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Please Select the Correct Operation: ");
				scanner.nextLine();
				continue;
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Please Select the Available Operation: ");
				scanner.nextLine();
				continue;
			}

			switch (action) {
			case 0:
				quit = true;
				System.out.println("Quited from the Restaurant");
				break;
			case 1:
				System.out.println("Please provide information for Booking Table:");
				System.out.println("Enter Name: ");
				String name = scanner.nextLine();
				System.out.println("Enter Mobile No.: ");
				long mobileNo = scanner.nextLong();
				scanner.nextLine();
				System.out.println("Enter Email: ");
				String email = scanner.nextLine();
				System.out.println("Enter Reservation Start Time(24 hour Format): ");
				int startTime = res.getSystem().exceptionHandling(24);
				System.out.println("Enter Reservation End Time(24 hour Format): ");
				int endTime = res.getSystem().exceptionHandling(24);
				System.out.println("Enter Number of Person: ");
				int noPerson = res.getSystem().exceptionHandling(10);

				Customer customer = new Customer(name, mobileNo, email);
				res.getSystem().bookTable(customer, startTime, endTime, noPerson, res);
				printOperationRestaurant();
				break;
			case 2:
				System.out.println("Enter the Registration id for Cancelling the Table:");
				int id = scanner.nextInt();
				scanner.nextLine();
				if (res.getSystem().cancelReservation(id, res)) {
					System.out.println("Your Table is cancelled.....");
				} else {
					System.out.println("Wrong Reservation id.....");
				}
				printOperationRestaurant();
				break;

			case 3:
				System.out.println("Enter the Registration id for Searching the Table:");
				int number = scanner.nextInt();
				scanner.nextLine();
				res.getSystem().searchReservation(number);
				printOperationRestaurant();
				break;

			default:
				break;
			}
		}

	}

	private static void printRestaurantList() {
		System.out.println("Please select Restarant for booking the Table: ");
		int count = 1;
		System.out.println("\t0: For Quiting after performing the operation: ");
		for (Restaurant res : resturentList) {
			System.out.println("\t" + count + ": " + res);
			count++;
		}
		System.out.println("-----------------------------------------------------------------");

	}

	private static void printOperationRestaurant() {
		System.out.println("Please select Operation: ");
		System.out.println("\t0: Quit from Restaurant");
		System.out.println("\t1: Book Table");
		System.out.println("\t2: Cancel Table");
		System.out.println("\t3: Query Reservation");
		System.out.println("-----------------------------------------------------------------");

	}

}
