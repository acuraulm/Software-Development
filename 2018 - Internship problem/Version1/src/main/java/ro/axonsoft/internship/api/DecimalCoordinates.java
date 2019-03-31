package ro.axonsoft.internship.api;

public interface DecimalCoordinates {
	/**
	 * The latitude
	 * @return The latitude
	 */
	double getLatitude();
	/**
	 * 
	 * @return The longitude
	 */
	double getLongitude();
	/**
	 * Sets the latitude coordinate
	 * @param latitude The latitude coordinate
	 */
	void setLatitude(Double latitude);
	/**
	 * Sets the longitude coordinate
	 * @param longitude The longitude coordinate
	 */
	void setLongitude(Double longitude);
}
