package ro.axonsoft.internship.impl.models;

import java.util.List;

import ro.axonsoft.internship.api.ClientDescriptor;
import ro.axonsoft.internship.api.HotelDescriptor;

public class Result implements ro.axonsoft.internship.api.SearchResult {

	private final ClientDescriptor client;
	private final List<HotelDescriptor> hotels;
	
	public Result(ResultBuilder resultBuilder) {
		this.client = resultBuilder.client;
		this.hotels = resultBuilder.hotels;
	}

	@Override
	public String toString() {
		String toReturn = "" + client + "\n" + "\n";
		for(HotelDescriptor hotel : hotels) {
			toReturn += hotel.toString();
		}
		return toReturn;
	}

	@Override
	public ClientDescriptor getClient() {
		return client;
	}

	@Override
	public List<HotelDescriptor> getHotels() {
		return hotels;
	}

	public static class ResultBuilder{
		private ClientDescriptor client;
		private List<HotelDescriptor>  hotels;
		
		public ResultBuilder setClient(ClientDescriptor client) {
			this.client = client;
			return this;
		}
		public ResultBuilder setHotels(List<HotelDescriptor> hotels) {
			this.hotels = hotels;
			return this;
		}
		public Result build() {
			return new Result(this);
		}
	}
}
