package ro.axonsoft.internship.impl.models;

import ro.axonsoft.internship.api.ClientDescriptor;
import ro.axonsoft.internship.api.DecimalCoordinates;


public class Client implements ClientDescriptor{

	private final String name;
	private final DecimalCoordinates coordinates;
	private final int radius;
	
	@Override
	public String toString() {
		return "" + name;
	}
	private Client(ClientBuilder clientBuilder) {
		this.name = clientBuilder.name;
		this.coordinates = clientBuilder.coordinates;
		this.radius = clientBuilder.radius;
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
	public int getRadius() {
		return radius;
	}

	public static class ClientBuilder {
		private String name;
		private DecimalCoordinates coordinates;
		private int radius;
		
		public ClientBuilder setName(String name) {
			this.name = name;
			return this;
		}
		
		public ClientBuilder setCoordinates(DecimalCoordinates coordinates) {
			this.coordinates = coordinates;
			return this;
		}
		
		public ClientBuilder setRadius(int radius) {
			this.radius = radius;
			return this;
		}
		
		public Client build() {
			return new Client(this);
		}
	}

}
