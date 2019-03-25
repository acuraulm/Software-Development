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
import model.Client;
import net.proteanit.sql.DbUtils;
import presentation.Dacee;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ClientDAO {

	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO client (name,address,email,age)"
			+ " VALUES (?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM client where id = ?";
	public static Client findById(int clientId) {
		Client toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, clientId);
			rs = findStatement.executeQuery();
			rs.next();

			String name = rs.getString("name");
			String address = rs.getString("address");
			String email = rs.getString("email");
			int age = rs.getInt("age");
			toReturn = new Client(clientId, name, address, email, age);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	public static List<Client> findAll() throws SQLException{
		List<Client> toReturn = new ArrayList<Client>();
		Connection dbConnection = ConnectionFactory.getConnection();
		//Statement findStatement = null;
		//ResultSet rs = null;
		PreparedStatement pst = dbConnection.prepareStatement("SELECT * from client");
		ResultSet rs = pst.executeQuery();
		Dacee.tableClients.setModel(DbUtils.resultSetToTableModel(rs));
		/* try{
			//findStatement = dbConnection.createStatement();
			//rs = findStatement.executeQuery("SELECT * FROM client");
			while(rs.next()){
				toReturn.add(new Client(Integer.parseInt(rs.getString("id")),
						Integer.parseInt(rs.getString("idclient")),
						Integer.parseInt(rs.getString("idproduct")),
						Integer.parseInt(rs.getString("quantity"))));
			}
			Dacee.tableClient.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
		} finally {
		*/
			ConnectionFactory.close(rs);
			//ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		//}
		return toReturn;
	}
	public static void delete(Client client){
			Connection dbConnection = ConnectionFactory.getConnection();
			Statement deleteStatement = null;
			try {
				deleteStatement = dbConnection.createStatement();
				deleteStatement.executeUpdate("DELETE FROM client where id = " + client.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
	}
	public static void update(Client client){
		Connection dbConnection = ConnectionFactory.getConnection();
		Statement updateStatement = null;
		try {
			updateStatement = dbConnection.createStatement();
			updateStatement.executeUpdate("UPDATE client SET id='" + client.getId() + "', name='" + client.getName() +
					"', address='" + client.getAddress()+"', email='" + client.getEmail()+"', age='"+ client.getAge() +"'  where id = " + client.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionFactory.close(updateStatement);
		ConnectionFactory.close(dbConnection);
}
	
	
	public static int insert(Client client) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, client.getName());
			insertStatement.setString(2, client.getAddress());
			insertStatement.setString(3, client.getEmail());
			insertStatement.setInt(4, client.getAge());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
}
