package ro.axonsoft.internship.builders;

import ro.axonsoft.internship.api.RoomDescriptor;
import ro.axonsoft.internship.impl.RoomDescriptorImpl;
import ro.axonsoft.internship.impl.RoomType;

public class RoomBuilder {

	private RoomDescriptor room;
	
	public RoomBuilder() {
		room = new RoomDescriptorImpl();
	}
	
	public RoomBuilder setPrice(double price) {
		room.setPrice(price);
		return this;
	}
	
	public RoomBuilder setType(RoomType type) {
		room.setType(type);
		return this;
	}
	
	public RoomBuilder setNumber(int number) {
		room.setNumber(number);
		return this;
	}
	
	public RoomDescriptor build() {
		return room;
	}
}
