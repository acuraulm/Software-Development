package ro.axonsoft.internship.api;

import java.util.List;

import ro.axonsoft.internship.impl.ReaderException;

public interface ClientReader {
	/**
	 * Reads a file that contains information about clients.
	 * @param filename the name of the file.
	 * @return a list of descriptions for clients with the information parsed from the file 
	 */
	List<ClientDescriptor> readFile(String filename);
	/**
	 * Reads a single line from the file.
	 * @param line the line to read
	 * @return a client description
	 * @throws ReaderException
	 */
	ClientDescriptor readLine(String line) throws ReaderException;
}
