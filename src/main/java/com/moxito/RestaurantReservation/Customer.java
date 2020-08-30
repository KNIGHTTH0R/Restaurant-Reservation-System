package com.moxito.RestaurantReservation;

public class Customer {

	private int tableId; // table number assigned to customer
	private String name;
	private long mobileNo;
	private String email;

	public Customer(String name, long mobileNo, String email) {
		this.name = name;
		this.mobileNo = mobileNo;
		this.email = email;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public String getName() {
		return name;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
