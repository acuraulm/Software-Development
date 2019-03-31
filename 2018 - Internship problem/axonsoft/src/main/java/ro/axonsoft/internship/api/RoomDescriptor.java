package ro.axonsoft.internship.api;

import ro.axonsoft.internship.impl.RoomType;

public interface RoomDescriptor {
	/**
	 * The price of the room per night
	 */
	double getPrice();
	/**
	 * The type of the room (SINGLE, DOUBLE, SUITE)
	 */
	RoomType getType();
	/**
	 * The number of available rooms
	 */
	int getNumber();
}
