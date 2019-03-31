package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.RoomDescriptor;

public class RoomDescriptorImpl implements RoomDescriptor{

	@Override
	public String toString() {
		if(number==0) {
			return "";
		}
		return "Room type:" + type + ", Price:" + price + ", Rooms Available:" + number + "\n";
	}

	private double price;
	private RoomType type;
	private int number;
	
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

	@Override
	public void setPrice(double price) {
		this.price = price;
		
	}

	@Override
	public void setType(RoomType type) {
		this.type = type;
	}

	@Override
	public void setNumber(int number) {
		this.number = number;
	}

}
