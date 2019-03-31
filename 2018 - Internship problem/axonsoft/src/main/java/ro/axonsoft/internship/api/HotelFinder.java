package ro.axonsoft.internship.api;

public interface HotelFinder {
	/**
	 * Search for the hotels that are in the search area.
	 * @param clientDescription the description of the client (name, coordinates and search radius)
	 * @return the result of search for the client
	 */
	SearchResult getNearby(ClientDescriptor clientDescriptor);
}
