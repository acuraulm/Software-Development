package ro.axonsoft.internship.impl.models;

import ro.axonsoft.internship.impl.RoomType;

public class Room implements ro.axonsoft.internship.api.RoomDescriptor {

	private final double price;
	private final RoomType type;
	private final int number;

	public Room(RoomBuilder roomBuilder) {
		this.price = roomBuilder.price;
		this.type = roomBuilder.type;
		this.number = roomBuilder.number;
	}

	@Override
	public String toString() {
		if(number==0) {
			return "\t No " + type + " rooms available at the moment.\n";
		}
		if(number == 1) {
			return "\t " + number + " available "+ type.toString().toLowerCase() + " room at " + price + " RON per night" + "\n";
			
		}
		return "\t " + number + " available "+ type.toString().toLowerCase() + " rooms at " + price + " RON per night" + "\n";
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public RoomType getType() {
		return type;
	}

	@Override
	public int getNumber() {
		return number;
	}

	public static class RoomBuilder {
		private double price;
		private RoomType type;
		private int number;

		public RoomBuilder setPrice(double price) {
			this.price = price;
			return this;
		}

		public RoomBuilder setType(RoomType type) {
			this.type = type;
			return this;
		}

		public RoomBuilder setNumber(int number) {
			this.number = number;
			return this;
		}

		public Room build() {
			return new Room(this);
		}
	}

}
