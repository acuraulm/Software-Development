package ro.axonsoft.internship.impl.validators;

import java.util.regex.Pattern;

public class HotelReaderValidator implements Validator<String[]> {
	private static final String NAME_PATTERN = "[a-zA-Z ]+\\.?";
	private static final String COORDINATE_PATTERN = "^[0-9.]*$";
	private static final String NUMBER_PATTERN = "^[0-9]*$";
	private static final String PRICE_PATTERN = "^[0-9.]*$";

	@Override
	public boolean validate(String[] attributes) {
		if ((attributes.length < 4)
				|| !Pattern.compile(NAME_PATTERN, Pattern.CASE_INSENSITIVE).matcher(attributes[0]).find()
				|| !Pattern.compile(COORDINATE_PATTERN).matcher(attributes[2]).find()
				|| !Pattern.compile(COORDINATE_PATTERN).matcher(attributes[3]).find()) {
			return false;
		}
		if (attributes.length > 4 && attributes.length <= 6) {
			if (!Pattern.compile(NUMBER_PATTERN).matcher(attributes[4]).find()
					|| !Pattern.compile(PRICE_PATTERN).matcher(attributes[5]).find()) {
				return false;
			}
		}
		if (attributes.length > 6 && attributes.length <= 8) {
			if (!Pattern.compile(NUMBER_PATTERN).matcher(attributes[6]).find()
					|| !Pattern.compile(PRICE_PATTERN).matcher(attributes[7]).find()) {
				return false;
			}
		}
		if (attributes.length > 8 && attributes.length <= 10) {
			if (!Pattern.compile(NUMBER_PATTERN).matcher(attributes[8]).find()
					|| !Pattern.compile(PRICE_PATTERN).matcher(attributes[9]).find()) {
				return false;
			}
		}
		return true;

	}

}
