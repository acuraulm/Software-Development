package ro.axonsoft.internship.api;

import java.util.List;

public interface HotelDescriptor {
	/**
	 * The name of the hotel
	 */
	String getName();
	/**
	 * The coordinates of the hotel
	 */
	DecimalCoordinates getCoordinates();
	/**
	 * The address of the hotel
	 */
	String getAddress();
	/**
	 * The list of available rooms for the hotel
	 */
	List<RoomDescriptor> getAvailableRooms();
}
