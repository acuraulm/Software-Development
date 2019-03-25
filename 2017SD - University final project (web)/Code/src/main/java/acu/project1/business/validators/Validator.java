package acu.project1.business.validators;

/**
 * Interface used for validation of input data
 * 
 * @author Acu
 *
 * @param <T>
 * 
 */
public interface Validator<T> {
	/**
	 * 
	 * @param t
	 *            <b> (T) </b>
	 * @return <b> true </b> if valid data input <br>
	 *         <b> false</b> if invalid data input
	 */
	public boolean validate(T t);

}
