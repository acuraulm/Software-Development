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
import entities.Report;


public class ReportDAO {

	protected static final Logger LOGGER = Logger.getLogger(ReportDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO Report (id, date, details, student_id)"
			+ " VALUES (?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM Report where id = ?";
	private final static String findStatementStringStudent = "SELECT * FROM Report where student_id = ?";
	public static Report findById(int ReportId) {
		Report toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, ReportId);
			rs = findStatement.executeQuery();
			rs.next();

			Date date = rs.getDate("date");
			String details = rs.getString("details");
			int student_id = rs.getInt("student_id");
			toReturn = new Report(ReportId, date, details, student_id);
		} catch (SQLException e) { 
			LOGGER.log(Level.WARNING,"ReportDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	public static List<Report> findAllByStudentId(int studentId) throws SQLException{
		List<Report> toReturn = new ArrayList<Report>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		//PreparedStatement pst = dbConnection.prepareStatement("SELECT * from Report WHERE student_id =?");
		//pst.setLong(1, studentId);
		ResultSet rs = null;
		try{
			findStatement = dbConnection.prepareStatement(findStatementStringStudent);
			findStatement.setLong(1, studentId);
			rs = findStatement.executeQuery();
			while(rs.next()){
				toReturn.add(new Report(Integer.parseInt(rs.getString(1)),
						rs.getDate(2),
						rs.getString(3),
						Integer.parseInt(rs.getString(4))));
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
	public static List<Report> findAll() throws SQLException{
		List<Report> toReturn = new ArrayList<Report>();
		Connection dbConnection = ConnectionFactory.getConnection();
		Statement findStatement = null;
		PreparedStatement pst = dbConnection.prepareStatement("SELECT * from Report");
		ResultSet rs = pst.executeQuery();
		try{
			findStatement = dbConnection.createStatement();
			rs = findStatement.executeQuery("SELECT * FROM Report");
			while(rs.next()){
				toReturn.add(new Report(Integer.parseInt(rs.getString(1)),
						rs.getDate(2),
						rs.getString(3),
						Integer.parseInt(rs.getString(4))));
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

	public static int insert(Report Report) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, Report.getId());
			insertStatement.setDate(2, (java.sql.Date) Report.getDate());
			insertStatement.setString(3, Report.getDetails());
			insertStatement.setInt(4, Report.getStudent_id());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ReportDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
}
