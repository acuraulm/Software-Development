package bll;



public class DecisionBLL {

	private boolean isAdmin;
	public DecisionBLL() {
		this.setAdmin(false);
	}

	public boolean isValid(String username, String password) {
		AdministratorBLL administratorBll = new AdministratorBLL();
		RegularBLL regularBll = new RegularBLL();
		if(administratorBll.findAdministratorByUsername(username) != null) {
			if(administratorBll.findAdministratorByUsername(username).getPassword().equals(password)) {
				this.isAdmin = true;
				return true;
			}
		}
		if(regularBll.findRegularByUsername(username) != null){
			if(regularBll.findRegularByUsername(username).getPassword().equals(password)) {
				this.isAdmin = false;
				return true;
			}
		}
		return false;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
