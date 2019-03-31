package ro.axonsoft.internship.builders;

import java.util.List;

import ro.axonsoft.internship.api.DecimalCoordinates;
import ro.axonsoft.internship.api.HotelDescriptor;
import ro.axonsoft.internship.api.RoomDescriptor;
import ro.axonsoft.internship.impl.HotelDescriptorImpl;

public class HotelBuilder {
	private HotelDescriptor hotel;

	public HotelBuilder() {
		hotel = new HotelDescriptorImpl();
	}

	public HotelBuilder setName(String name) {
		hotel.setName(name);
		return this;
	}

	public HotelBuilder setCoordinates(DecimalCoordinates coordinates) {
		hotel.setCoordinates(coordinates);
		return this;
	}

	public HotelBuilder setAddress(String address) {
		hotel.setAddress(address);
		return this;
	}

	public HotelBuilder setAvailableRooms(List<RoomDescriptor> availableRooms) {
		hotel.setAvailableRooms(availableRooms);
		return this;
	}

	public HotelDescriptor build() {
		return hotel;
	}
}
