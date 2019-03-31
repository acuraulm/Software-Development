package ro.axonsoft.internship.api;

import java.util.List;

public interface SearchResult {
	
	/**
	 * The client for which the search was made
	 */
	ClientDescriptor getClient();
	
	/**
	 * The description of the hotels found for client
	 */
	List<HotelDescriptor> getHotels();
}
