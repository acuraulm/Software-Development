package acu.project1.business.validators;

import java.util.regex.Pattern;

/**
 * Implementation for HotelReader validation
 * 
 * @author Acu
 *
 */
public class StudentUsernameValidator implements Validator<String> {
	private static final String USERNAME_PATTERN = "[a-zA-Z0-9-]+";

	@Override
	public boolean validate(String attributes) {
		if (!Pattern.compile(USERNAME_PATTERN, Pattern.CASE_INSENSITIVE).matcher(attributes).find()) {
			return false;
		}
		
		return true;

	}

}
