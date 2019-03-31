package ro.axonsoft.internship.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Collections;

import ro.axonsoft.internship.api.ClientDescriptor;
import ro.axonsoft.internship.api.HotelDescriptor;
import ro.axonsoft.internship.api.RoomDescriptor;
import ro.axonsoft.internship.api.SearchResult;

public class SearchResultImpl implements SearchResult {

	private ClientDescriptor client;
	private List<HotelDescriptor> hotels;

	@Override
	public String toString() {
		String toReturn = "";
		for(HotelDescriptor hotel : hotels) {
			toReturn += "\t" + hotel.toString();
		}
		return toReturn;
	}

	@Override
	public ClientDescriptor getClient() {
		return client;
	}

	@Override
	public void setClient(ClientDescriptor client) {
		this.client = client;
	}

	@Override
	public List<HotelDescriptor> getHotels() {
		return hotels;
	}

	@Override
	public void setHotels(List<HotelDescriptor> hotels) {
		this.hotels = hotels;
	}

	@Override
	public void addHotel(HotelDescriptor hotel) {
		List<HotelDescriptor> hotelsOrdered = this.hotels;
		hotelsOrdered.add(hotel);

		Collections.sort(hotelsOrdered, new sortTerms());
		this.hotels = hotelsOrdered;
	}

	private class sortTerms implements Comparator<HotelDescriptor> {
		@Override
		public int compare(HotelDescriptor hotelDescriptor1, HotelDescriptor hotelDescriptor2) {
			if (computeAveragePrice(hotelDescriptor1) > computeAveragePrice(hotelDescriptor2))
				return 1;
			else
				return -1;
		}

		private double computeAveragePrice(HotelDescriptor hotelDescriptor) {
			double hotelDescriptorAveragePrice = 0.0;
			int counter = 0;
			for (RoomDescriptor roomDescriptor : hotelDescriptor.getAvailableRooms()) {
				counter++;
				hotelDescriptorAveragePrice += roomDescriptor.getPrice();
			}
			hotelDescriptorAveragePrice /= counter;
			return hotelDescriptorAveragePrice;
		}
	}

}
