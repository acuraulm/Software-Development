package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.ClientDescriptor;
import ro.axonsoft.internship.api.DecimalCoordinates;

public class ClientDescriptorImpl implements ClientDescriptor {

	private String name;
	private DecimalCoordinates coordinates;
	private int radius;

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public DecimalCoordinates getCoordinates() {
		return this.coordinates;
	}

	@Override
	public int getRadius() {
		return this.radius;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setCoordinates(DecimalCoordinates coordinates) {
		this.coordinates = coordinates;

	}

	@Override
	public void setRadius(int radius) {
		this.radius = radius;

	}

	@Override
	public String toString() {
		return "[" + name + ", " + coordinates + ", " + radius + "]";
	}
}
