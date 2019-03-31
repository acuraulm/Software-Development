package ro.axonsoft.internship.impl;

import java.io.IOException;
import java.util.ArrayList;

import ro.axonsoft.internship.api.ClientDescriptor;
import ro.axonsoft.internship.api.DecimalCoordinates;
import ro.axonsoft.internship.api.HotelDescriptor;
import ro.axonsoft.internship.api.HotelFinder;
import ro.axonsoft.internship.api.HotelsReader;
import ro.axonsoft.internship.api.SearchResult;
import ro.axonsoft.internship.builders.SearchResultBuilder;

public class HotelFinderImpl implements HotelFinder{

	public SearchResult getNearby(ClientDescriptor clientDescriptor) {
		SearchResult searchResult = new SearchResultBuilder().setClient(clientDescriptor)
					.setHotels(new ArrayList<>())
					.build();
		
		HotelsReader hotelReader = new HotelsReaderImpl();
		
		try {
			for(HotelDescriptor hotelDescriptor : hotelReader.readFile("src/main/resources/hotels.csv")) {
				if(isNearby(hotelDescriptor, clientDescriptor))
					searchResult.addHotel(hotelDescriptor);			}
		} catch (IOException e) {
			System.out.println("Could not read hotels.csv");
			e.printStackTrace();
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
		
	/*	double distance;
		double aux1 = Math.sin((hotelLatitudeRads - clientLatitudeRads)/2) * Math.sin((hotelLatitudeRads - clientLatitudeRads)/2);
		double aux2 = Math.sin((hotelLongitudeRads - clientLongitudeRads)/2) * Math.sin((hotelLongitudeRads - clientLongitudeRads)/2);
		double aux3 = Math.cos(clientLatitudeRads) * Math.cos(hotelLatitudeRads);
		
		double sqqrt = Math.sqrt(aux1 + aux2*aux3);
		double arcsin = Math.asin(sqqrt);
		
		distance = arcsin*2.0*6.371;
		return distance;
		*/
		return 1000 * 2.0 * 6.371 * Math.asin(Math.sqrt(Math.sin((hotelLatitudeRads - clientLatitudeRads)/2) * Math.sin((hotelLatitudeRads - clientLatitudeRads)/2) + (Math.sin((hotelLongitudeRads - clientLongitudeRads)/2) * Math.sin((hotelLongitudeRads - clientLongitudeRads)/2))*Math.cos(clientLatitudeRads) * Math.cos(hotelLatitudeRads)));
				
	}
	

}
