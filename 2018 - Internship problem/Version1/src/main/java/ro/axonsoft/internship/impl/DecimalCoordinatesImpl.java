package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.DecimalCoordinates;

public class DecimalCoordinatesImpl implements DecimalCoordinates{

	private double latitude;
	private double longitude;
	
	@Override
	public String toString() {
		return "[" + latitude + ", " + longitude + "]";
	}

	@Override
	public double getLatitude() {
		return this.latitude;
	}
	
	@Override
	public double getLongitude() {
		return this.longitude;
	}

	@Override
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Override
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
