package com.moxito.RestaurantReservation;

import java.util.ArrayList;

public class Reservation {
	private int reservationId; // reservation id at Restaurant
	private Table table; // table assigned
	private Customer askedPerson; // who asks for this reservation
	private int startTime; // start time of table
	private int endTime;
	private int totalPerson;
	private int restaurantTimeStarting;

	// totalPerson may be greater than the seats available for a particular
	// table
	ArrayList<Table> allocatedTables;

	public Reservation(int reservationId, Customer askedPerson, Table table, int startTime, int endTime,
			int totalPerson, int restaurantTimeStarting) {
		this.reservationId = reservationId;
		this.askedPerson = askedPerson;
		this.table = table;
		this.startTime = startTime;
		this.endTime = endTime;
		this.totalPerson = totalPerson;
		this.restaurantTimeStarting = restaurantTimeStarting;
	}

	public int getReservationId() {
		return reservationId;
	}

	public Table getTable() {
		return table;
	}

	public Customer getAskedPerson() {
		return askedPerson;
	}

	public int getStartTime() {
		return startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setAskedPerson(Customer askedPerson) {
		this.askedPerson = askedPerson;
	}

	public int getTotalPerson() {
		return totalPerson;
	}

	public ArrayList<Table> getAllocatedTables() {
		return allocatedTables;
	}

	public void setAllocatedTables(ArrayList<Table> allocatedTables) {
		this.allocatedTables = allocatedTables;
	}

	public int getRestaurantTimeStarting() {
		return restaurantTimeStarting;
	}

	public void notifyUserWithDetails() {
		System.out.println("Table is booked......");
		System.out.println("\tReservation id: " + this.reservationId);
		System.out.println("\tTable Number: " + this.askedPerson.getTableId());
		System.out.println("\tBooked by: " + this.askedPerson);
		System.out.println("\tStart by: " + this.startTime + ":00");
	}

	public void notifyUserWithDetails(int bookingId) {
		System.out.println("Your Reservation details: ");
		System.out.println("\tReservation id: " + bookingId);
		System.out.println("\tTable Number: " + this.askedPerson.getTableId());
		System.out.println("\tBooked by: " + this.askedPerson);
		System.out.println("\tStart by: " + this.startTime + ":00");
	}

}
