package transfer;

public class StatusDTO {

	private int id;
	private int packageId;
	private RouteDTO[] routes;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPackageId() {
		return packageId;
	}
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	public RouteDTO[] getRoutes() {
		return routes;
	}
	public void setRoutes(RouteDTO[] routes) {
		this.routes = routes;
	}
	@Override
	public String toString() {
		return "StatusDTO [id=" + id + ", packageId=" + packageId + ", routes=" + routes + "]";
	}
	
	
	
	
}
