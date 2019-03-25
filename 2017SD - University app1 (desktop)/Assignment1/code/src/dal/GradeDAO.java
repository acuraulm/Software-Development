package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import entities.Grade;


public class GradeDAO {

	protected static final Logger LOGGER = Logger.getLogger(GradeDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO Grade (id, date, value, course_id, course_administrator_id, student_id)"
			+ " VALUES (?,?,?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM Grade where id = ?";
	private final static String findStatementStringStudent = "SELECT * FROM Grade where student_id = ? AND course_id = ?";
	public static Grade findById(int GradeId) {
		Grade toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, GradeId);
			rs = findStatement.executeQuery();
			rs.next();

			Date date = rs.getDate("date");
			int value = rs.getInt("value");
			int course_id = rs.getInt("course_id");
			int course_administrator_id = rs.getInt("course_administrator_id");
			int student_id = rs.getInt("student_id");
			toReturn = new Grade(GradeId, date, value, course_id, course_administrator_id, student_id);
		} catch (SQLException e) { 
			LOGGER.log(Level.WARNING,"GradeDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	public static Grade findByStudentId(int studentId, int courseId) {
		Grade toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementStringStudent);
			findStatement.setLong(1, studentId);
			findStatement.setLong(2, courseId);
			rs = findStatement.executeQuery();
			rs.next();
			int id = rs.getInt("id");
			Date date = rs.getDate("date");
			int value = rs.getInt("value");
			int course_administrator_id = rs.getInt("course_administrator_id");
			toReturn = new Grade(id, date, value, courseId, course_administrator_id, studentId);
		} catch (SQLException e) { 
			LOGGER.log(Level.WARNING,"GradeDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	public static List<Grade> findAll() throws SQLException{
		List<Grade> toReturn = new ArrayList<Grade>();
		Connection dbConnection = ConnectionFactory.getConnection();
		Statement findStatement = null;
		PreparedStatement pst = dbConnection.prepareStatement("SELECT * from Grade");
		ResultSet rs = pst.executeQuery();
		try{
			findStatement = dbConnection.createStatement();
			rs = findStatement.executeQuery("SELECT * FROM Grade");
			while(rs.next()){
				toReturn.add(new Grade(Integer.parseInt(rs.getString(1)),
						rs.getDate(2),
						Integer.parseInt(rs.getString(3)),
						Integer.parseInt(rs.getString(4)),
						Integer.parseInt(rs.getString(5)),
						Integer.parseInt(rs.getString(6))));
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
	public static void delete(Grade Grade){
			Connection dbConnection = ConnectionFactory.getConnection();
			Statement deleteStatement = null;
			try {
				deleteStatement = dbConnection.createStatement();
				deleteStatement.executeUpdate("DELETE FROM Grade where id = " + Grade.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
	}
	public static void update(Grade grade){
		Connection dbConnection = ConnectionFactory.getConnection();
		Statement updateStatement = null;
		try {
			updateStatement = dbConnection.createStatement();
		//	UPDATE assignmentt1.grade SET value = 6 WHERE student_id = 1 AND course_id = 1
			updateStatement.executeUpdate("UPDATE Grade SET date='" 
					+ grade.getDate() + "', value='" + grade.getValue()+"', course_id='" 
					+ grade.getCourse_id() +"', student_id='" 
					+ grade.getStudent_id() + "'  where student_id = " 
					+ grade.getStudent_id() + " and course_id=" 
					+ grade.getCourse_id());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionFactory.close(updateStatement);
		ConnectionFactory.close(dbConnection);
}
	
	
	public static int insert(Grade Grade) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, Grade.getId());
			insertStatement.setDate(2, (java.sql.Date) Grade.getDate());
			insertStatement.setInt(3, Grade.getValue());
			insertStatement.setInt(4, Grade.getCourse_id());
			insertStatement.setInt(5, Grade.getCourse_Administrator_id());
			insertStatement.setInt(6, Grade.getStudent_id());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "GradeDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
}
