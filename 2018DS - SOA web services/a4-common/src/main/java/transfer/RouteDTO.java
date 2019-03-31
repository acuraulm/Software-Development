package transfer;

import java.util.Date;

public class RouteDTO {

	private int id;
	private int status_id;
	private String city;
	private Date time;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "RouteDTO [id=" + id + ", status_id=" + status_id + ", city=" + city + ", time=" + time + "]";
	}
	
	
}
