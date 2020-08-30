package com.moxito.RestaurantReservation;

import java.util.Arrays;

public class Table {

	private int id; // for table number
	private int numOfSeats; // number of seat at the Table
	Boolean[] occupiedHours = new Boolean[12]; // We divided our 12 hours into 12 parts. Whenever we book
	// this table set true is occupied for that specified hour. This variable is
	// mainly used
	// to checking availability of this table during any specific time.

	public Table(int numOfSeats, int id) {
		this.numOfSeats = numOfSeats;
		this.id = id;
		Arrays.fill(occupiedHours, false);
	}

	public int getId() {
		return id;
	}

	public int getNumOfSeats() {
		return numOfSeats;
	}

	public Boolean[] getOccupiedHours() {
		return occupiedHours;
	}

	public void setOccupiedHours(Boolean[] occupiedHours) {
		this.occupiedHours = occupiedHours;
	}

	@Override
	public String toString() {
		return "Table id: " + id + ", Seat: " + this.numOfSeats;
	}

}
