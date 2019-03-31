package ro.axonsoft.internship.api;

import java.util.List;

public interface HotelDescriptor {
	/**
	 * The name of the hotel
	 */
	String getName();
	
	void setName(String name);
	/**
	 * The coordinates of the hotel
	 */
	DecimalCoordinates getCoordinates();
	void setCoordinates(DecimalCoordinates coordinates);
	
	/**
	 * The address of the hotel
	 */
	String getAddress();
	void setAddress(String Address);
	
	/**
	 * The list of available rooms for the hotel
	 */
	List<RoomDescriptor> getAvailableRooms();
	void setAvailableRooms(List<RoomDescriptor> availableRooms);
}
