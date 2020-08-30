package com.moxito.RestaurantReservation;

import java.util.TreeMap;

public class Restaurant implements Comparable<Restaurant> {

	private String resName;
	private int capacity;
	private int tableId;
	private int timeOfStarting;
	private int timeOfClosing;

	private ReservationSystem system;

	public Restaurant() {

	}

	public Restaurant(String resName, int capacity, int openingTime) {
		this.resName = resName;
		this.capacity = capacity; // No. of tables accommodated in restaurant
		this.timeOfStarting = openingTime;
		this.timeOfClosing = 22;
		this.tableId = 1;
		system = new ReservationSystem();
		InitializeRestaurant();

	}

	public ReservationSystem getSystem() {
		return system;
	}

	public String getResName() {
		return resName;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getTimeOfStarting() {
		return timeOfStarting;
	}

	public int getTimeOfClosing() {
		return timeOfClosing;
	}

	@Override
	public int compareTo(Restaurant obj) {
		return this.resName.compareTo(obj.resName);
	}

	@Override
	public String toString() {

		return this.resName;

	}

	public void InitializeRestaurant() {
		// to store number of seat of specific type in restaurant
		TreeMap<Integer, Integer> seatingPercentage = new TreeMap<Integer, Integer>();
		seatingPercentage.put(4, 40); // Key-seat per table and value percent of capacity
		seatingPercentage.put(6, 30);
		seatingPercentage.put(8, 20);
		seatingPercentage.put(10, 10);

		int lastSeats = 1;
		int seatRestaurant = this.capacity;
		for (int seatsAvailable : seatingPercentage.keySet()) {
			int seatAllocated;
			if (lastSeats < seatingPercentage.size()) {
				seatAllocated = Math.round((float) this.capacity * seatingPercentage.get(seatsAvailable) / 100);
				seatRestaurant -= seatAllocated;
			} else {
				seatAllocated = seatRestaurant;
			}
			lastSeats++;

			for (int i = 1; i <= seatAllocated; i++) {
				Table table = new Table(seatsAvailable, this.tableId);
				system.addTable(table);
				this.tableId++;
			}
		}

	}

}
