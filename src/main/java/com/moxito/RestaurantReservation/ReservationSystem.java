package com.moxito.RestaurantReservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ReservationSystem {
	private static int reservationNumber; // reservation id assigned to customer
	protected HashMap<Integer, Reservation> reservationMap; // map of reservation make at restaurant
	private ArrayList<Table> tables; // list of tables at restaurant

	public ReservationSystem() {
		tables = new ArrayList<Table>();
		reservationMap = new HashMap<Integer, Reservation>();
		reservationNumber = 50000;
	}

	public ArrayList<Table> getTables() {
		return tables;
	}

	public void setTables(ArrayList<Table> tables) {
		this.tables = tables;
	}

	public void addTable(Table tableRes) {
		tables.add(tableRes);
	}

	// to cancel the reservation
	public boolean cancelReservation(int id, Restaurant res) {
		if (this.reservationMap.containsKey(id)) {
			Reservation reservation = reservationMap.get(id);
			Table table = reservation.getTable();
			/*
			 * System.out.println("Table before cancel: "); for (int i = 0; i <
			 * table.occupiedHours.length; i++) {
			 * System.out.println(table.occupiedHours[i]); }
			 */
			Table cancTable = null;
			for (Table tab : res.getSystem().getTables()) {
				if (table.equals(tab)) {
					cancTable = tab;
					break;
				}
			}
			int startTime = reservation.getStartTime();
			int endTime = reservation.getEndTime();
			int operatingHours = startTime - reservation.getRestaurantTimeStarting();
			int restaurantHour = endTime - startTime;
			for (int i = operatingHours; i < operatingHours + restaurantHour; i++) {
				cancTable.occupiedHours[i] = false;
			}
			/*
			 * System.out.println("Table after cancel: "); for (int i = 0; i <
			 * cancTable.occupiedHours.length; i++) {
			 * System.out.println(cancTable.occupiedHours[i]); }
			 */
			reservationMap.remove(id);
			return true;
		} else {
			return false;
		}
	}

	public void bookTable(Customer customer, int startTime, int endTime, int noPerson, Restaurant res) {
		// boolean available = checkAvailability(startTime, endTime, noPerson,
		// tablesRestaurant);
		if (startTime < res.getTimeOfStarting() || res.getTimeOfClosing() < endTime) {
			System.out.println("Restaurant is Closed");
			return;
		}
		ArrayList<Table> availableTables = new ArrayList<Table>();
		for (Table tab : res.getSystem().getTables()) {
			int availSeats = tab.getNumOfSeats();
			if (noPerson <= availSeats && noPerson + 2 > availSeats) {
				int operatingHours = startTime - res.getTimeOfStarting();
				int restaurantHour = endTime - startTime;
				boolean reserve = false;
				for (int i = operatingHours; i < operatingHours + restaurantHour; i++) {
					if (tab.getOccupiedHours()[i] == true) {
						reserve = true;
					}
				}
				if (!reserve) {
					availableTables.add(tab);
				}
			}

		}
		if (availableTables.isEmpty()) {
			System.out.println("Table is not available, Please try to book Table at different time Slot");
			return;
		}
		printAvailableTable(availableTables, startTime, endTime);
		Scanner scanner = new Scanner(System.in);
		int selectedTable = exceptionHandling(availableTables.size());
		Table reserveTable = availableTables.get(selectedTable - 1);
		Table registeredTable = null;
		for (Table tab : res.getSystem().getTables()) {
			if (reserveTable.equals(tab)) {
				customer.setTableId(tab.getId());
				int operatingHours = startTime - res.getTimeOfStarting();
				int restaurantHour = endTime - startTime;
				boolean reserve = false;
				for (int i = operatingHours; i < operatingHours + restaurantHour; i++) {
					tab.occupiedHours[i] = true;
				}
				registeredTable = tab;
				break;
			}

		}
		Reservation reservation = new Reservation(reservationNumber, customer, registeredTable, startTime, endTime,
				noPerson, res.getTimeOfStarting());
		reservationMap.put(reservationNumber, reservation);
		reservationNumber++;
		reservation.notifyUserWithDetails();
	}

	public void searchReservation(int id) {
		if (this.reservationMap.containsKey(id)) {
			Reservation reservation = reservationMap.get(id);
			reservation.notifyUserWithDetails(id);
		} else {
			System.out.println("This Table is not booked by you");
		}

	}

	public int exceptionHandling(int limit) {
		Scanner scanner = new Scanner(System.in);
		int number;
		while (true) {
			try {
				number = scanner.nextInt();
				if (number > limit) {
					throw new IndexOutOfBoundsException();
				}
				scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("\tPlease Enter the again Number(Integer only): ");
				scanner.nextLine();
				continue;
			} catch (IndexOutOfBoundsException e) {
				System.out.println("\tPlease Enter the Within the Range 1-" + limit + ": ");
				scanner.nextLine();
				continue;
			}
			break;
		}

		return number;
	}

	private void printAvailableTable(ArrayList<Table> availableTables, int startTime, int endTime) {
		System.out.println("Availabe Table from " + startTime + ":00 to " + endTime + ":00, Select Table: ");
		int count = 1;
		for (Table t : availableTables) {
			System.out.println("\t" + count + "-->" + " Table No.: " + t.getId());
			count++;
		}
	}

}
