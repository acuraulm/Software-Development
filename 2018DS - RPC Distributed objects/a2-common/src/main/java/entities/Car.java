package entities;
import java.io.Serializable;

public class Car implements Serializable {

	private static final long serialVersionUID = 1L;
	private int year;
	private int engineSize;
	private double price;

	public Car() {
	}

	public Car(int year, int engineSize) {
		this.year = year;
		this.engineSize = engineSize;
	}
	
	public Car(int year, int engineSize, double price) {
		this.year = year;
		this.engineSize = engineSize;
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getEngineSize() {
		return engineSize;
	}

	public void setEngineSize(int engineSize) {
		this.engineSize = engineSize;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + engineSize;
		result = prime * result + year;
		result = prime * result + (int)price;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (engineSize != other.engineSize)
			return false;
		if (year != other.year)
			return false;
		if (price != other.price)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Car [year=" + year + ", engineSize=" + engineSize + ", price=" + price + "]";
	}

}
