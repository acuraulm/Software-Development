package model;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Orders {
	private int id;
	private int idclient;
	private int idproduct;
	private int quantity;

	public Orders(int idclient, int idproduct, int quantity) {
		super();
		this.idclient = idclient;
		this.idproduct = idproduct;
		this.setQuantity(quantity);
	}


	public Orders(int id, int idclient, int idproduct, int quantity) {
		super();
		this.id = id;
		this.idclient = idclient;
		this.idproduct = idproduct;
		this.setQuantity(quantity);
	}


	public Orders(int id){
		super();
		this.id=id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getIdclient() {
		return idclient;
	}


	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}


	public int getIdproduct() {
		return idproduct;
	}


	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
	}


	@Override
	public String toString() {
		return "Orders [id=" + id + ", idclient=" + idclient + ", idproduct=" + idproduct + ", quantity=" + quantity + "]";
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



}
