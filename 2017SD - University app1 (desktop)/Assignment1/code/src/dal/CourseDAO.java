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
import entities.Course;


public class CourseDAO {

	protected static final Logger LOGGER = Logger.getLogger(CourseDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO Course (id, name, administrator_id)"
			+ " VALUES (?,?,?)";
	private final static String findStatementString = "SELECT * FROM Course where id = ?";
	private final static String findStatementStringName = "SELECT * FROM Course where name = ?";
		public static Course findById(int CourseId) {
		Course toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, CourseId);
			rs = findStatement.executeQuery();
			rs.next();

			String name = rs.getString("name");
			int administrator_id = rs.getInt("administrator_id");
			toReturn = new Course(CourseId, name, administrator_id);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"CourseDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	public static Course findByName(String courseName) {
		Course toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementStringName);
			findStatement.setString(1, courseName );
			rs = findStatement.executeQuery();
			rs.next();
			int id = rs.getInt("id");
			int administrator_id = rs.getInt("administrator_id");
			toReturn = new Course(id, courseName, administrator_id);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"AdministratorDAO:findByUsername " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	public static List<Course> findAll() throws SQLException{
		List<Course> toReturn = new ArrayList<Course>();
		Connection dbConnection = ConnectionFactory.getConnection();
		Statement findStatement = null;
		PreparedStatement pst = dbConnection.prepareStatement("SELECT * from Course");
		ResultSet rs = pst.executeQuery();
		try{
			findStatement = dbConnection.createStatement();
			rs = findStatement.executeQuery("SELECT * FROM Course");
			while(rs.next()){
				toReturn.add(new Course(Integer.parseInt(rs.getString("id")),
						rs.getString("name"),
						Integer.parseInt(rs.getString("Administrator_id"))));
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
	public static void delete(Course Course){
			Connection dbConnection = ConnectionFactory.getConnection();
			Statement deleteStatement = null;
			try {
				deleteStatement = dbConnection.createStatement();
				deleteStatement.executeUpdate("DELETE FROM Course where id = " + Course.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
	}
	public static void update(Course Course){
		Connection dbConnection = ConnectionFactory.getConnection();
		Statement updateStatement = null;
		try {
			updateStatement = dbConnection.createStatement();
			updateStatement.executeUpdate("UPDATE Course SET id='" + Course.getId() + "', name='" + Course.getName() +
					"', administrator_id='" + Course.getAdministrator_id()+"',  where id = " + Course.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionFactory.close(updateStatement);
		ConnectionFactory.close(dbConnection);
}
	
	
	public static int insert(Course Course) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, Course.getId());
			insertStatement.setString(2, Course.getName());
			insertStatement.setInt(3, Course.getAdministrator_id());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "CourseDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
}
