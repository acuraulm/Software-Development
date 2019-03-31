package ro.axonsoft.internship.api;

public interface ClientDescriptor {

	/**
	 * The name of the client
	 */
	String getName();

	/**
	 * Sets the name of the client
	 * 
	 * @param name
	 *            The name of the client
	 */
	void setName(String name);

	/**
	 * The coordinates where the client is positioned
	 */
	DecimalCoordinates getCoordinates();

	/**
	 * Sets the coordinates of the client
	 * 
	 * @param coordinats
	 *            The coordinates where the client is positioned
	 */
	void setCoordinates(DecimalCoordinates coordinates);

	/**
	 * The radius of the search area for the client
	 */
	int getRadius();

	/**
	 * Sets the radius of the search area for the client
	 * 
	 * @param radius
	 *            The radius of search
	 */
	void setRadius(int radius);

}
