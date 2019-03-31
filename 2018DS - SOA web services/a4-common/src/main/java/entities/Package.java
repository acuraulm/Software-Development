package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="package")
public class Package {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Appuser sender;
	@ManyToOne
	private Appuser receiver;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private String senderCity;
	@Column
	private String destinationCity;
	@Column
	private Boolean tracking;
	
	public Appuser getSender() {
		return sender;
	}
	public void setSender(Appuser sender) {
		this.sender = sender;
	}
	public Appuser getReceiver() {
		return receiver;
	}
	public void setReceiver(Appuser receiver) {
		this.receiver = receiver;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSenderCity() {
		return senderCity;
	}
	public void setSenderCity(String senderCity) {
		this.senderCity = senderCity;
	}
	public String getDestinationCity() {
		return destinationCity;
	}
	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}
	public Boolean getTracking() {
		return tracking;
	}
	public void setTracking(Boolean tracking) {
		this.tracking = tracking;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Package [id=" + id + ", sender=" + sender.getUsername() + ", receiver=" + receiver.getUsername() + ", name=" + name
				+ ", description=" + description + ", senderCity=" + senderCity + ", destinationCity=" + destinationCity
				+ ", tracking=" + tracking + "]";
	}
	
	

}
