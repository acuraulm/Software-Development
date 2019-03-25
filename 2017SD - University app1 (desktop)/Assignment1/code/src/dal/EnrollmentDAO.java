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
import entities.Enrollment;


public class EnrollmentDAO {

	protected static final Logger LOGGER = Logger.getLogger(EnrollmentDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO Enrollment (course_id, student_id)"
			+ " VALUES (?,?)";
	private final static String findStatementString = "SELECT * FROM Enrollment where id = ?";
	public static Enrollment findById(int EnrollmentId) {
		Enrollment toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, EnrollmentId);
			rs = findStatement.executeQuery();
			rs.next();

			int course_id = rs.getInt("course_id");
			int student_id = rs.getInt("student_id");
			toReturn = new Enrollment(course_id, student_id);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"EnrollmentDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	public static List<Enrollment> findAll() throws SQLException{
		List<Enrollment> toReturn = new ArrayList<Enrollment>();
		Connection dbConnection = ConnectionFactory.getConnection();
		Statement findStatement = null;
		PreparedStatement pst = dbConnection.prepareStatement("SELECT * from Enrollment");
		ResultSet rs = pst.executeQuery();
		try{
			findStatement = dbConnection.createStatement();
			rs = findStatement.executeQuery("SELECT * FROM Enrollment");
			while(rs.next()){
				toReturn.add(new Enrollment(Integer.parseInt(rs.getString("course_id")),
						Integer.parseInt(rs.getString("student_id"))));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"EnrollmentDAO:findById " + e.getMessage());
		} finally {
		
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	public static void delete(Enrollment Enrollment){
			Connection dbConnection = ConnectionFactory.getConnection();
			Statement deleteStatement = null;
			try {
				deleteStatement = dbConnection.createStatement();
				deleteStatement.executeUpdate("DELETE FROM Enrollment where course_id = " + Enrollment.getCourse_id() + " AND student_id =" + Enrollment.getStudent_id());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
	}
	
	public static int insert(Enrollment Enrollment) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, Enrollment.getCourse_id());
			insertStatement.setInt(2, Enrollment.getStudent_id());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "EnrollmentDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
}
