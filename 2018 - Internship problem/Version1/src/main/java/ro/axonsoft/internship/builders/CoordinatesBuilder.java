package ro.axonsoft.internship.builders;

import ro.axonsoft.internship.api.DecimalCoordinates;
import ro.axonsoft.internship.impl.DecimalCoordinatesImpl;

public class CoordinatesBuilder {
	private DecimalCoordinates coordinates;

	public CoordinatesBuilder() {
		coordinates = new DecimalCoordinatesImpl();
	}

	public CoordinatesBuilder setLatitude(double latitude) {
		coordinates.setLatitude(latitude);
		return this;
	}


	public CoordinatesBuilder setLongitude(double longitude) {
		coordinates.setLongitude(longitude);
		return this;
	}


	public DecimalCoordinates build() {
		return coordinates;
	}
}
