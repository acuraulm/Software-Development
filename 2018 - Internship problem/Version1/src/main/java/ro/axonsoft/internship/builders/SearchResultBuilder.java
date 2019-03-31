package ro.axonsoft.internship.builders;

import java.util.List;

import ro.axonsoft.internship.api.ClientDescriptor;
import ro.axonsoft.internship.api.HotelDescriptor;
import ro.axonsoft.internship.api.SearchResult;
import ro.axonsoft.internship.impl.SearchResultImpl;

public class SearchResultBuilder {

	private SearchResult searchResult;
	
	public SearchResultBuilder() {
		searchResult = new SearchResultImpl();
	}
	
	public SearchResultBuilder setClient(ClientDescriptor clientDescriptor) {
		searchResult.setClient(clientDescriptor);
		return this;
	}
	
	public SearchResultBuilder setHotels(List<HotelDescriptor> hotels) {
		searchResult.setHotels(hotels);
		return this;
	}
	
	public SearchResult build() {
		return searchResult;
	}
}
