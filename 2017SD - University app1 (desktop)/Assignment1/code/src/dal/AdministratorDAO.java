package dal;

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
import entities.Administrator;


public class AdministratorDAO {

	protected static final Logger LOGGER = Logger.getLogger(AdministratorDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO Administrator (id,username,password,name)"
			+ " VALUES (?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM Administrator where id = ?";
	private final static String findStatementStringUsername = "SELECT * FROM Administrator where username = ?";
	public static Administrator findById(int AdministratorId) {
		Administrator toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, AdministratorId);
			rs = findStatement.executeQuery();
			rs.next();

			String username = rs.getString("username");
			String password = rs.getString("password");
			String name = rs.getString("name");
			toReturn = new Administrator(AdministratorId, username, password,name);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"AdministratorDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	public static Administrator findByUsername(String usernameAdmin) {
		Administrator toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementStringUsername);
			findStatement.setString(1, usernameAdmin );
			rs = findStatement.executeQuery();
			rs.next();
			int id = rs.getInt("id");
			String password = rs.getString("password");
			String name = rs.getString("name");
			toReturn = new Administrator(id, usernameAdmin, password,name);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"AdministratorDAO:findByUsername " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	public static List<Administrator> findAll() throws SQLException{
		List<Administrator> toReturn = new ArrayList<Administrator>();
		Connection dbConnection = ConnectionFactory.getConnection();
		//Statement findStatement = null;
		//ResultSet rs = null;
		PreparedStatement pst = dbConnection.prepareStatement("SELECT * from Administrator");
		ResultSet rs = pst.executeQuery();
		//Dacee.tableAdministrators.setModel(DbUtils.resultSetToTableModel(rs));
		/* try{
			//findStatement = dbConnection.createStatement();
			//rs = findStatement.executeQuery("SELECT * FROM Administrator");
			while(rs.next()){s
				toReturn.add(new Administrator(Integer.parseInt(rs.getString("id")),
						Integer.parseInt(rs.getString("idAdministrator")),
						Integer.parseInt(rs.getString("idproduct")),
						Integer.parseInt(rs.getString("quantity"))));
			}
			Dacee.tableAdministrator.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"AdministratorDAO:findById " + e.getMessage());
		} finally {
		*/
			ConnectionFactory.close(rs);
			//ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		//}
		return toReturn;
	}
	public static void update(Administrator Administrator){
		Connection dbConnection = ConnectionFactory.getConnection();
		Statement updateStatement = null;
		try {
			updateStatement = dbConnection.createStatement();
			updateStatement.executeUpdate("UPDATE Administrator SET id='" + Administrator.getId() + "', username='" + Administrator.getName() +
					"', password='" + Administrator.getPassword()+"', name='"+ Administrator.getName() +"'  where id = " + Administrator.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionFactory.close(updateStatement);
		ConnectionFactory.close(dbConnection);
}
	
	
	public static int insert(Administrator Administrator) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, Administrator.getId());
			insertStatement.setString(2, Administrator.getUsername());
			insertStatement.setString(3, Administrator.getPassword());
			insertStatement.setString(4, Administrator.getName());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "AdministratorDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
}
