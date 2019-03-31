package ro.axonsoft.internship.impl.validators;

import java.util.regex.Pattern;

public class ClientReaderValidator implements Validator<String[]> {
	private static final String NAME_PATTERN = "[a-zA-Z]+\\.?";
	private static final String ADDRESS_PATTERN = "^[a-zA-Z0-9_.-]*$";
	private static final String COORDINATE_PATTERN = "^[0-9.]*$";

	@Override
	public boolean validate(String[] attributes) {
		if ((attributes.length < 4)
				|| !Pattern.compile(NAME_PATTERN, Pattern.CASE_INSENSITIVE).matcher(attributes[0]).find()
				|| !Pattern.compile(ADDRESS_PATTERN, Pattern.CASE_INSENSITIVE).matcher(attributes[1]).find()
				|| !Pattern.compile(COORDINATE_PATTERN).matcher(attributes[2]).find()
				|| !Pattern.compile(COORDINATE_PATTERN).matcher(attributes[3]).find()) {
			return false;
		} else {
			return true;
		}

	}
}
