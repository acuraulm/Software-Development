package model;

import java.io.Serializable;

public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int personID;
	private String personName;
	private int personAge;
	
	public Person(int personID, String personName, int personAge){
		this.personID = personID;
		this.personName = personName;
		this.personAge = personAge;
	}
	public Person(int personID){
		this.personID = personID;
	}
	
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public int getPersonAge() {
		return personAge;
	}
	public void setPersonAge(int personAge) {
		this.personAge = personAge;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + personAge;
		result = prime * result + personID;
		result = prime * result + ((personName == null) ? 0 : personName.hashCode());
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
		Person other = (Person) obj;
		if (personAge != other.personAge)
			return false;
		if (personID != other.personID)
			return false;
		if (personName == null) {
			if (other.personName != null)
				return false;
		} else if (!personName.equals(other.personName))
			return false;
		return true;
	}
	@Override
	public String toString() {
	//	return "Person [personID=" + personID + ", personName=" + personName + ", personAge=" + personAge + "]";
		return personName + "";
	}
	

}
