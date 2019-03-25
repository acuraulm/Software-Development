package entities;


public class DVD {

	private String title;
	private int year;
	private Double price;
	
	public DVD(String title, int year, Double price) {
		super();
		this.title = title;
		this.year = year;
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "DVD [title=" + title + ", year=" + year + ", price=" + price + "]";
	}
	
	
}
