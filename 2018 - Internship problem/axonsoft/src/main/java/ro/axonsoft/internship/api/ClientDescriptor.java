package ro.axonsoft.internship.api;

public interface ClientDescriptor {
	/**
	 * The name of the client
	 */
	String getName();
	/**
	 * The coordinates where the client is positioned
	 */
	DecimalCoordinates getCoordinates();
	/**
	 * The radius of the search area for the client
	 */
	int getRadius();
}
