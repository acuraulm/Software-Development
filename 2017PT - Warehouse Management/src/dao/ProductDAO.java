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
import model.Product;
import net.proteanit.sql.DbUtils;
import presentation.Dacee;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ProductDAO {

	protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO product (name,price,quantity)"
			+ " VALUES (?,?,?)";
	private final static String findStatementString = "SELECT * FROM product where id = ?";
	public static Product findById(int productId) {
		Product toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, productId);
			rs = findStatement.executeQuery();
			rs.next();

			String name = rs.getString("name");
			Double price = rs.getDouble("price");
			int quantity = rs.getInt("quantity");
			toReturn = new Product(productId, name, price, quantity);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ProductDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	public static void decrease(Product product, int quant){
		Connection dbConnection = ConnectionFactory.getConnection();
		Statement updateStatement = null;
		try {
			updateStatement = dbConnection.createStatement();
			updateStatement.executeUpdate("UPDATE product SET id='" + product.getId() + "', name='" + product.getName() +
					"', price='" + product.getPrice()+"', quantity='" + (product.getQuantity() - quant) +"'  where id = " + product.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionFactory.close(updateStatement);
		ConnectionFactory.close(dbConnection);
	}
	public static List<Product> findAll() throws SQLException{
		List<Product> toReturn = new ArrayList<Product>();
		Connection dbConnection = ConnectionFactory.getConnection();
		//Statement findStatement = null;
		//ResultSet rs = null;
		PreparedStatement pst = dbConnection.prepareStatement("SELECT * from product");
		ResultSet rs = pst.executeQuery();
		Dacee.tableProducts.setModel(DbUtils.resultSetToTableModel(rs));
		/* try{
			//findStatement = dbConnection.createStatement();
			//rs = findStatement.executeQuery("SELECT * FROM product");
			while(rs.next()){
				toReturn.add(new Product(Integer.parseInt(rs.getString("id")),
						Integer.parseInt(rs.getString("idproduct")),
						Integer.parseInt(rs.getString("idproduct")),
						Integer.parseInt(rs.getString("quantity"))));
			}
			Dacee.tableProduct.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ProductDAO:findById " + e.getMessage());
		} finally {
		*/
			ConnectionFactory.close(rs);
			//ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		//}
		return toReturn;
	}
	public static void delete(Product product){
			Connection dbConnection = ConnectionFactory.getConnection();
			Statement deleteStatement = null;
			try {
				deleteStatement = dbConnection.createStatement();
				deleteStatement.executeUpdate("DELETE FROM product where id = " + product.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
	}
	public static void update(Product product){
		Connection dbConnection = ConnectionFactory.getConnection();
		Statement updateStatement = null;
		try {
			updateStatement = dbConnection.createStatement();
			updateStatement.executeUpdate("UPDATE product SET id='" + product.getId() + "', name='" + product.getName() +
					"', price='" + product.getPrice()+"', quantity='" + product.getQuantity()+"'  where id = " + product.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionFactory.close(updateStatement);
		ConnectionFactory.close(dbConnection);
}
	
	
	public static int insert(Product product) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, product.getName());
			insertStatement.setDouble(2, product.getPrice());
			insertStatement.setInt(3, product.getQuantity());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
}
