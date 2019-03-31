package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.ClientDescriptor;
import ro.axonsoft.internship.api.DecimalCoordinates;
import ro.axonsoft.internship.api.HotelDescriptor;
import ro.axonsoft.internship.api.HotelFinder;
import ro.axonsoft.internship.api.HotelsReader;
import ro.axonsoft.internship.api.SearchResult;
import ro.axonsoft.internship.impl.models.DataCreator;

public class HotelFinderImpl implements HotelFinder {

	@Override
	public SearchResult getNearby(ClientDescriptor clientDescriptor) {
		SearchResult searchResult = DataCreator.createSearchResult(clientDescriptor);
		HotelsReader hotelReader = new HotelsReaderImpl();
		
		for(HotelDescriptor hotelDescriptor : hotelReader.readFile("src/main/resources/hotels.csv")) {
			if(isNearby(hotelDescriptor, clientDescriptor))
				searchResult.getHotels().add(hotelDescriptor);
			}
		
		return searchResult;
	}
	private boolean isNearby(HotelDescriptor hotelDescriptor, ClientDescriptor clientDescriptor) {
		if(distanceBetween(clientDescriptor.getCoordinates(), hotelDescriptor.getCoordinates()) <= clientDescriptor.getRadius())
			return true;
		else
			return false;
	}
	
	private double distanceBetween(DecimalCoordinates clientCoordinates, DecimalCoordinates hotelCoordinates) {
		double clientLatitudeRads = Math.toRadians(clientCoordinates.getLatitude());
		double hotelLatitudeRads = Math.toRadians(hotelCoordinates.getLatitude());
		double clientLongitudeRads = Math.toRadians(clientCoordinates.getLongitude());
		double hotelLongitudeRads = Math.toRadians(hotelCoordinates.getLongitude());

		return 1000 * 2.0 * 6.371 * Math.asin(Math.sqrt(Math.sin((hotelLatitudeRads - clientLatitudeRads)/2) * Math.sin((hotelLatitudeRads - clientLatitudeRads)/2) + (Math.sin((hotelLongitudeRads - clientLongitudeRads)/2) * Math.sin((hotelLongitudeRads - clientLongitudeRads)/2))*Math.cos(clientLatitudeRads) * Math.cos(hotelLatitudeRads)));
				
	}
}
