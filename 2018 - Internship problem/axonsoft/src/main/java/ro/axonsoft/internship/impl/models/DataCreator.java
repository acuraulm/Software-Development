package ro.axonsoft.internship.impl.models;

import java.util.ArrayList;
import java.util.List;

import ro.axonsoft.internship.api.*;
import ro.axonsoft.internship.api.SearchResult;
import ro.axonsoft.internship.impl.RoomType;

public class DataCreator {

	public static ClientDescriptor createClient(String[] attributes) {
		if (attributes.length >= 3) {
			return new Client.ClientBuilder().setName(attributes[0])
					.setCoordinates(createCoordinates(attributes[1], attributes[2]))
					.setRadius(Integer.parseInt(attributes[3])).build();
		} else
			return null;
	}

	public static DecimalCoordinates createCoordinates(String latitude, String longitude) {
		return new Coordinates.CoordinatesBuilder().setLatitude(Double.parseDouble(latitude))
				.setLongitude(Double.parseDouble(longitude)).build();
	}

	public static HotelDescriptor createHotel(String[] attributes) {
		if(attributes.length >= 4) {
			List<RoomDescriptor> availableRooms = new ArrayList<>();
				if (attributes.length >= 6) {
					availableRooms.add(createRoom(attributes[4], attributes[5], RoomType.SINGLE));
					if (attributes.length >= 8) {
						availableRooms.add(createRoom(attributes[6], attributes[7], RoomType.DOUBLE));
						if (attributes.length >= 10) {
							availableRooms.add(createRoom(attributes[8], attributes[9], RoomType.SUITE));
						}
					}
				}
			return new Hotel.HotelBuilder().setName(attributes[0]).setAddress(attributes[1])
					.setCoordinates(createCoordinates(attributes[2], attributes[3]))
					.setAvailableRooms(availableRooms)
					.build();
		}
		else
			return null;
	}

	public static RoomDescriptor createRoom(String number, String price, RoomType type) {
		return new Room.RoomBuilder().setPrice(Double.parseDouble(price))
				.setNumber(Integer.parseInt(number))
				.setType(type).build();
	}

	public static SearchResult createSearchResult(ClientDescriptor clientDescriptor) {
		if(clientDescriptor != null) {
			return new Result.ResultBuilder().setClient(clientDescriptor)
					.setHotels(new ArrayList<>())
					.build();
		}
		else
			return null;
	}
}
