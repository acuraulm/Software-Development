package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Orders;
import net.proteanit.sql.DbUtils;
import presentation.Dacee;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class OrdersDAO {

	protected static final Logger LOGGER = Logger.getLogger(OrdersDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO orders (idclient, idproduct, quantity)"
			+ " VALUES (?,?,?)";
	private final static String findStatementString = "SELECT * FROM orders where id = ?";

	public static List<Orders> findAll() throws SQLException{
		List<Orders> toReturn = new ArrayList<Orders>();
		Connection dbConnection = ConnectionFactory.getConnection();
		//Statement findStatement = null;
		//ResultSet rs = null;
		PreparedStatement pst = dbConnection.prepareStatement("SELECT * from orders");
		ResultSet rs = pst.executeQuery();
		Dacee.tableOrders.setModel(DbUtils.resultSetToTableModel(rs));
		/* try{
			//findStatement = dbConnection.createStatement();
			//rs = findStatement.executeQuery("SELECT * FROM orders");
			while(rs.next()){
				toReturn.add(new Orders(Integer.parseInt(rs.getString("id")),
						Integer.parseInt(rs.getString("idclient")),
						Integer.parseInt(rs.getString("idproduct")),
						Integer.parseInt(rs.getString("quantity"))));
			}
			Dacee.tableOrders.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"OrdersDAO:findById " + e.getMessage());
		} finally {
		*/
			ConnectionFactory.close(rs);
			//ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		//}
		return toReturn;
	}
	
	public static void delete(Orders orders){
			Connection dbConnection = ConnectionFactory.getConnection();
			Statement deleteStatement = null;
			try {
				deleteStatement = dbConnection.createStatement();
				deleteStatement.executeUpdate("DELETE FROM orders where id = " + orders.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
	}
	public static Orders findById(int ordersId) {
		Orders toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, ordersId);
			rs = findStatement.executeQuery();
			rs.next();
			int idclient = rs.getInt("idclient");
			int idproduct = rs.getInt("idproduct");
			int quantity = rs.getInt("quantity");
			toReturn = new Orders(ordersId, idclient, idproduct, quantity);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"OrdersDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public static void update(Orders orders){
		Connection dbConnection = ConnectionFactory.getConnection();
		Statement updateStatement = null;
		try {
			updateStatement = dbConnection.createStatement();
			updateStatement.executeUpdate("UPDATE orders SET id='" + orders.getId() + "', idclient='" + orders.getIdclient() +
					"', idproduct='" + orders.getIdproduct()+ "', quantity='" + orders.getQuantity() + "'  where id = " + orders.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionFactory.close(updateStatement);
		ConnectionFactory.close(dbConnection);
}
	
	
	public static int insert(Orders orders) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, orders.getIdclient());
			insertStatement.setInt(2, orders.getIdproduct());
			insertStatement.setInt(3, orders.getQuantity());
			insertStatement.executeUpdate();
			
			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrdersDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
}
