package ro.axonsoft.internship.impl.validators;

public interface Validator<T> {
	public boolean validate(T t);

}
