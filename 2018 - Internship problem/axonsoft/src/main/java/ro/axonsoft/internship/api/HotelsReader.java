package ro.axonsoft.internship.api;

import java.util.List;

import ro.axonsoft.internship.impl.ReaderException;

public interface HotelsReader {
	/**
	 * Reads a file that contains information about hotels.
	 * @param filename the name of the file.
	 * @return a list of descriptions for hotels with the information parsed from the file 
	 */
	List<HotelDescriptor> readFile(final String filename);
	/**
	 * Reads a single line from the file.
	 * @param line the line to read
	 * @return a hotel description
	 * @throws ReaderException
	 */
	HotelDescriptor  readLine(final String line) throws ReaderException;
}
