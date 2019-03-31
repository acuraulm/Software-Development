package ro.axonsoft.internship.api;

import java.util.List;

public interface Writer {
	/**
	 * Writes the nearby hotels information for each client
	 */
	void writeResult(final List<SearchResult> results);
}
