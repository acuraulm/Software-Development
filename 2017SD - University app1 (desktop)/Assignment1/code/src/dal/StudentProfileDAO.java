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
import entities.StudentProfile;

public class StudentProfileDAO {

	protected static final Logger LOGGER = Logger.getLogger(StudentProfileDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO StudentProfile (id, identification_number, group_id, regular_id)"
			+ " VALUES (?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM StudentProfile where id = ?";

	public static StudentProfile findById(int StudentProfileId) {
		StudentProfile toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, StudentProfileId);
			rs = findStatement.executeQuery();
			rs.next();

			String identification_number = rs.getString("identification_number");
			int group_id = rs.getInt("group_id");
			int regular_id = rs.getInt("regular_id");

			toReturn = new StudentProfile(StudentProfileId, identification_number, group_id, regular_id);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "StudentProfileDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public static List<StudentProfile> findAll() throws SQLException {
		List<StudentProfile> toReturn = new ArrayList<StudentProfile>();
		Connection dbConnection = ConnectionFactory.getConnection();
		// Statement findStatement = null;
		// ResultSet rs = null;
		PreparedStatement pst = dbConnection.prepareStatement("SELECT * from StudentProfile");
		ResultSet rs = pst.executeQuery();
		// Dacee.tableStudentProfiles.setModel(DbUtils.resultSetToTableModel(rs));
		/*
		 * try{ //findStatement = dbConnection.createStatement(); //rs =
		 * findStatement.executeQuery("SELECT * FROM StudentProfile"); while(rs.next()){
		 * toReturn.add(new StudentProfile(Integer.parseInt(rs.getString("id")),
		 * Integer.parseInt(rs.getString("idStudentProfile")),
		 * Integer.parseInt(rs.getString("idproduct")),
		 * Integer.parseInt(rs.getString("quantity")))); }
		 * Dacee.tableStudentProfile.setModel(DbUtils.resultSetToTableModel(rs)); }
		 * catch (SQLException e) {
		 * LOGGER.log(Level.WARNING,"StudentProfileDAO:findById " + e.getMessage()); }
		 * finally {
		 */
		ConnectionFactory.close(rs);
		// ConnectionFactory.close(findStatement);
		ConnectionFactory.close(dbConnection);
		// }
		return toReturn;
	}

	public static void delete(StudentProfile StudentProfile) {
		Connection dbConnection = ConnectionFactory.getConnection();
		Statement deleteStatement = null;
		try {
			deleteStatement = dbConnection.createStatement();
			deleteStatement.executeUpdate("DELETE FROM StudentProfile where id = " + StudentProfile.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionFactory.close(deleteStatement);
		ConnectionFactory.close(dbConnection);
	}

	public static void update(StudentProfile StudentProfile) {
		Connection dbConnection = ConnectionFactory.getConnection();
		Statement updateStatement = null;
		try {
			updateStatement = dbConnection.createStatement();
			updateStatement.executeUpdate("UPDATE StudentProfile SET id='" + StudentProfile.getId()
					+ "', identification_number='" + StudentProfile.getIdentification_number() + "', group_id='"
					+ StudentProfile.getGroup_id() + "', regular_id='" + StudentProfile.getRegular_id()
					+ "' where id = " + StudentProfile.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionFactory.close(updateStatement);
		ConnectionFactory.close(dbConnection);
	}

	public static int insert(StudentProfile StudentProfile) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, StudentProfile.getId());
			insertStatement.setString(2, StudentProfile.getIdentification_number());
			insertStatement.setInt(3, StudentProfile.getGroup_id());
			insertStatement.setInt(4, StudentProfile.getRegular_id());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "StudentProfileDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
}
