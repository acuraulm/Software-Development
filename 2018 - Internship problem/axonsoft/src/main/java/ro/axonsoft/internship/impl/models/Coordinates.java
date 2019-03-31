package ro.axonsoft.internship.impl.models;

import ro.axonsoft.internship.api.DecimalCoordinates;

public class Coordinates implements DecimalCoordinates {

	private final double latitude;
	private final double longitude;
	
	private Coordinates(CoordinatesBuilder coordinatesBuilder) {
		this.latitude = coordinatesBuilder.latitude;
		this.longitude = coordinatesBuilder.longitude;
	}
	@Override
	public double getLatitude() {
		return latitude;
	}
	@Override
	public double getLongitude() {
		return longitude;
	}
	
	public static class CoordinatesBuilder{
		private double latitude;
		private double longitude;
		
		public CoordinatesBuilder setLatitude(double latitude) {
			this.latitude = latitude;
			return this;
		}
		public CoordinatesBuilder setLongitude(double longitude) {
			this.longitude = longitude;
			return this;
		}
		
		public Coordinates build() {
			return new Coordinates(this);
		}
	}

}
