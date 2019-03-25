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
import entities.Group;


public class GroupDAO {

	protected static final Logger LOGGER = Logger.getLogger(GroupDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO Group (id,number)"
			+ " VALUES (?,?)";
	private final static String findStatementString = "SELECT * FROM assignmenta1.`group` WHERE id = ?";
	public static Group findById(int GroupId) {
		Group toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setInt(1, GroupId);
			rs = findStatement.executeQuery();
			rs.next();
			String number = rs.getString("number");
			toReturn = new Group(GroupId, number);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"GroupDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	public static List<Group> findAll() throws SQLException{
		List<Group> toReturn = new ArrayList<Group>();
		Connection dbConnection = ConnectionFactory.getConnection();
		//Statement findStatement = null;
		//ResultSet rs = null;
		PreparedStatement pst = dbConnection.prepareStatement("SELECT * from Group");
		ResultSet rs = pst.executeQuery();
		//Dacee.tableGroups.setModel(DbUtils.resultSetToTableModel(rs));
		/* try{
			//findStatement = dbConnection.createStatement();
			//rs = findStatement.executeQuery("SELECT * FROM Group");
			while(rs.next()){
				toReturn.add(new Group(Integer.parseInt(rs.getString("id")),
						Integer.parseInt(rs.getString("idGroup")),
						Integer.parseInt(rs.getString("idproduct")),
						Integer.parseInt(rs.getString("quantity"))));
			}
			Dacee.tableGroup.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"GroupDAO:findById " + e.getMessage());
		} finally {
		*/
			ConnectionFactory.close(rs);
			//ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		//}
		return toReturn;
	}
	public static void delete(Group Group){
			Connection dbConnection = ConnectionFactory.getConnection();
			Statement deleteStatement = null;
			try {
				deleteStatement = dbConnection.createStatement();
				deleteStatement.executeUpdate("DELETE FROM Group where id = " + Group.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
	}
	public static void update(Group Group){
		Connection dbConnection = ConnectionFactory.getConnection();
		Statement updateStatement = null;
		try {
			updateStatement = dbConnection.createStatement();
		//	updateStatement.executeUpdate("UPDATE Group SET id='" + Group.getId() + "', date='" + Group.getDate() +
		//			"', value='" + Group.getValue()+"'  where id = " + Group.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionFactory.close(updateStatement);
		ConnectionFactory.close(dbConnection);
}
	
	
	public static int insert(Group Group) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, Group.getId());
		//	insertStatement.setDate(2, (java.sql.Date) Group.getDate());
		//	insertStatement.setInt(3, Group.getValue());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "GroupDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
}
