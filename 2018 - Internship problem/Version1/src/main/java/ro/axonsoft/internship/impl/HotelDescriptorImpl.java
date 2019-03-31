package ro.axonsoft.internship.impl;

import java.util.List;

import ro.axonsoft.internship.api.DecimalCoordinates;
import ro.axonsoft.internship.api.HotelDescriptor;
import ro.axonsoft.internship.api.RoomDescriptor;

public class HotelDescriptorImpl implements HotelDescriptor {

	private String name;
	private DecimalCoordinates coordinates;
	private String address;
	private List<RoomDescriptor> availableRooms;

	@Override
	public String toString() {
	//	return "[" + name + ", " + coordinates + ", " + address
	//			+ ", " + availableRooms.toString() + "]";
		String toReturn = name + "\n";
		if(availableRooms.isEmpty())
			return toReturn + "No rooms available\n";
		for(RoomDescriptor room : availableRooms)
			toReturn +=  room.toString();
		return toReturn;
		//return name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public DecimalCoordinates getCoordinates() {
		return coordinates;
	}

	@Override
	public void setCoordinates(DecimalCoordinates coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public List<RoomDescriptor> getAvailableRooms() {
		return availableRooms;
	}

	@Override
	public void setAvailableRooms(List<RoomDescriptor> availableRooms) {
		this.availableRooms = availableRooms;
	}

}
