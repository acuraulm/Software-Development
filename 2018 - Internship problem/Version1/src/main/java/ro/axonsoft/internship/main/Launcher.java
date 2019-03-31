package ro.axonsoft.internship.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ro.axonsoft.internship.api.ClientDescriptor;
import ro.axonsoft.internship.api.ClientReader;
import ro.axonsoft.internship.api.HotelDescriptor;
import ro.axonsoft.internship.api.HotelFinder;
import ro.axonsoft.internship.api.HotelsReader;
import ro.axonsoft.internship.api.SearchResult;
import ro.axonsoft.internship.api.Writer;
import ro.axonsoft.internship.impl.ClientReaderImpl;
import ro.axonsoft.internship.impl.HotelFinderImpl;
import ro.axonsoft.internship.impl.HotelsReaderImpl;
import ro.axonsoft.internship.impl.SearchResultImpl;
import ro.axonsoft.internship.impl.WriterImpl;

public class Launcher {
	public static void main(String[] args) {
		getWriter().writeResult(getResults());
	}

	/**
	 * Creates the writer that will print the result
	 * @return an instance of {@link Writer}
	 */
	private static Writer getWriter() {
		return new WriterImpl();
	}
	
	/**
	 * Returns a list with the search results for each client
	 * @return the list of results
	 */
	private static List<SearchResult> getResults() {
		List<SearchResult> results = new ArrayList<>();
		ClientReader clientReader = new ClientReaderImpl();
		HotelFinder hotelFinder = new HotelFinderImpl();
		try {
			for(ClientDescriptor clientDescriptor :  clientReader.readFile("src/main/resources/clients.csv"))
				results.add(hotelFinder.getNearby(clientDescriptor));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return results;
	}
}
