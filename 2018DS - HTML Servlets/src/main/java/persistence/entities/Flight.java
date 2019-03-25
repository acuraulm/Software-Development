package persistence.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "flights")
public class Flight implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1433294605809070118L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private int number;
	@Column
	private String type;
	@ManyToOne
	private City departureCity;
	@Column
	private Date departureDate;
	@ManyToOne
	private City arrivalCity;
	@Column
	private Date arrivalDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public City getDepartureCity() {
		return departureCity;
	}
	public void setDepartureCity(City departureCity) {
		this.departureCity = departureCity;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public City getArrivalCity() {
		return arrivalCity;
	}
	public void setArrivalCity(City arrivalCity) {
		this.arrivalCity = arrivalCity;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	/*@Override
	public String toString() {
		return "Flight [id=" + id + ", number=" + number + ", type=" + type + ", departureCity=" + departureCity.getName()
				+ ", departureDate=" + departureDate + ", arrivalCity=" + arrivalCity.getName() + ", arrivalDate=" + arrivalDate
				+ "]";
	}*/
	
	
}
