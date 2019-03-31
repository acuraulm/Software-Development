package ro.axonsoft.internship.api;

import ro.axonsoft.internship.impl.RoomType;

public interface RoomDescriptor {
	/**
	 * The price of the room per night
	 */
	double getPrice();
	void setPrice(double price);
	/**
	 * The type of the room (SINGLE, DOUBLE, SUITE)
	 */
	RoomType getType();
	void setType(RoomType type);
	/**
	 * The number of available rooms
	 */
	int getNumber();
	void setNumber(int number);
}
