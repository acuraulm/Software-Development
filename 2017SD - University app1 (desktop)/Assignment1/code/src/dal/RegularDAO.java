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
import entities.Regular;


public class RegularDAO {

	protected static final Logger LOGGER = Logger.getLogger(RegularDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO Regular (id,username,password,pnc,name)"
			+ " VALUES (?,?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM Regular where id = ?";
	private final static String findStatementStringUsername = "SELECT * FROM Regular where username = ?";
	private final static String findStatementStringName = "SELECT * FROM Regular where name = ?";
	public static Regular findById(int RegularId) {
		Regular toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, RegularId);
			rs = findStatement.executeQuery();
			rs.next();

			String username = rs.getString("username");
			String password = rs.getString("password");
			int pnc = rs.getInt("pnc");
			String name = rs.getString("name");
			toReturn = new Regular(RegularId, username, password, pnc, name);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"RegularDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	public static Regular findByUsername(String usernameRegular) {
		Regular toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementStringUsername);
			findStatement.setString(1, usernameRegular );
			rs = findStatement.executeQuery();
			rs.next();
			int id = rs.getInt("id");
			String password = rs.getString("password");
			String name = rs.getString("name");
			int pnc = rs.getInt("pnc");
			toReturn = new Regular(id, usernameRegular, password, pnc, name);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"RegularDAO:findByUsername " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	public static Regular findByName(String nameRegular) {
		Regular toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementStringName);
			findStatement.setString(1, nameRegular );
			rs = findStatement.executeQuery();
			rs.next();
			int id = rs.getInt("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			int pnc = rs.getInt("pnc");
			toReturn = new Regular(id, username, password, pnc, nameRegular);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"RegularDAO:findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	public static List<Regular> findAll() throws SQLException{
		List<Regular> toReturn = new ArrayList<Regular>();
		Connection dbConnection = ConnectionFactory.getConnection();
		Statement findStatement = null;
		PreparedStatement pst = dbConnection.prepareStatement("SELECT * from Regular");
		ResultSet rs = pst.executeQuery();
		try{
			findStatement = dbConnection.createStatement();
			rs = findStatement.executeQuery("SELECT * FROM Regular");
			while(rs.next()){
				toReturn.add(new Regular(Integer.parseInt(rs.getString(1)),
						rs.getString(2),
						rs.getString(3),
						Integer.parseInt(rs.getString(4)),
						rs.getString(5)));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"CourseDAO:findById " + e.getMessage());
		} finally {
		
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	public static void delete(Regular Regular){
			Connection dbConnection = ConnectionFactory.getConnection();
			Statement deleteStatement = null;
			try {
				deleteStatement = dbConnection.createStatement();
				deleteStatement.executeUpdate("DELETE FROM Regular where id = " + Regular.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
	}
	public static void update(Regular Regular){
		Connection dbConnection = ConnectionFactory.getConnection();
		Statement updateStatement = null;
		try {
			updateStatement = dbConnection.createStatement();
			updateStatement.executeUpdate("UPDATE Regular SET id='" 
			+ Regular.getId() + "', username='" 
			+ Regular.getUsername() +"', password='" 
			+ Regular.getPassword()+"', pnc='" 
			+ Regular.getPnc()+"', name='"
			+ Regular.getName() +"'  where id = " 
			+ Regular.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionFactory.close(updateStatement);
		ConnectionFactory.close(dbConnection);
}
	
	
	public static int insert(Regular Regular) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, Regular.getId());
			insertStatement.setString(2, Regular.getUsername());
			insertStatement.setString(3, Regular.getPassword());
			insertStatement.setInt(4, Regular.getPnc());
			insertStatement.setString(5, Regular.getName());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "RegularDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
}
