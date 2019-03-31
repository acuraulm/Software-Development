package ro.axonsoft.internship.impl.models;

import java.util.List;

import ro.axonsoft.internship.api.DecimalCoordinates;
import ro.axonsoft.internship.api.HotelDescriptor;
import ro.axonsoft.internship.api.RoomDescriptor;

public class Hotel implements HotelDescriptor{
	
	private final String name;
	private final DecimalCoordinates coordinates;
	private final String address;
	private final List<RoomDescriptor> availableRooms;
	
	
	public Hotel(HotelBuilder hotelBuilder) {
		this.name = hotelBuilder.name;
		this.coordinates = hotelBuilder.coordinates;
		this.address = hotelBuilder.address;
		this.availableRooms = hotelBuilder.availableRooms;
	}

	@Override
	public String toString() {
		String toReturn = name + "\n";
		if(availableRooms.isEmpty())
			return toReturn + "\t No rooms available\n";
		for(RoomDescriptor room : availableRooms)
			toReturn +=  room.toString();
		return toReturn;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public DecimalCoordinates getCoordinates() {
		return coordinates;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public List<RoomDescriptor> getAvailableRooms() {
		return availableRooms;
	}
	
	public static class HotelBuilder{
		private String name;
		private DecimalCoordinates coordinates;
		private String address;
		private List<RoomDescriptor> availableRooms;
		
		public HotelBuilder setName(String name) {
			this.name = name;
			return this;
		}
		
		public HotelBuilder setCoordinates(DecimalCoordinates coordinates) {
			this.coordinates = coordinates;
			return this;
		}
		public HotelBuilder setAddress(String address) {
			this.address = address;
			return this;
		}
		public HotelBuilder setAvailableRooms(List<RoomDescriptor> availableRooms) {
			this.availableRooms = availableRooms;
			return this;
		}
		public Hotel build() {
			return new Hotel(this);
		}
	}



}
