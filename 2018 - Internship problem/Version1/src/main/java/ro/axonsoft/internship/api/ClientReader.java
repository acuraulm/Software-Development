package ro.axonsoft.internship.api;

import java.io.IOException;
import java.util.List;

public interface ClientReader {
	/**
	 * Reads a file that contains information about clients.
	 * 
	 * @param fileName
	 *            the name of the file.
	 * @return a list of descriptions for clients with the information parsed from
	 *         the file
	 * @throws IOException
	 */
	List<ClientDescriptor> readFile(String fileName) throws IOException;

	/**
	 * Reads a single line from the file.
	 * 
	 * @param line
	 *            the line to read
	 * @return a client description
	 */
	ClientDescriptor readLine(String line);
}
