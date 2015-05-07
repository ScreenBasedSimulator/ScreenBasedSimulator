package edu.cmu.lti.bic.sbs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.cmu.lti.bic.sbs.gson.Record;

// Notice, do not import com.mysql.jdbc.*
// or you will have problems!

public class DBHelper {
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	private static Connection conn = null;
	
	public static Record[] displayDataBase(String username) throws Exception {
		try {
			//establish connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost/sbs_db?"
					+ "user=root&password=");
			
			// Statements allow to issue SQL queries to the database
			statement = conn.createStatement();

			String sql = "select * from sbs_userdata";
			if (username != null && !username.isEmpty()) {
				sql += " where userName=\"" + username + "\"";
			}
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery(sql);
			
			ArrayList<Record> records = new ArrayList<Record>();
			while (resultSet.next()) {
				// It is possible to get the columns via name
				// also possible to get the columns via the column number
				// which starts at 1
				// e.g. resultSet.getString(2);
				int id = resultSet.getInt("Id");
				String user = resultSet.getString("userName");
				int scenarioId = resultSet.getInt("scenarioId");
				Float score = resultSet.getFloat("score");
				String report = resultSet.getString("report");
				String debrief = resultSet.getString("debrief");
				
				System.out.println("Id: " + id);
				System.out.println("User: " + user);
				System.out.println("scenarioId: " + scenarioId);
				System.out.println("score: " + score);
				System.out.println("report: " + report);
				System.out.println("debrief: " + debrief);
				System.out.println("-----------------------------");
				records.add(new Record(user, scenarioId, score, report, debrief));
			}
			return records.toArray(new Record[1]);
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			close();
		}
		return null;
	}
	/**
	 * 
	 * @param userName
	 * @param scenarioId
	 * @param score
	 * @param report
	 * @return id of the insertion row
	 */
	public static int insertResult(String userName, int scenarioId, double score, String report){	
		if(userName == null){
			userName = "";
		}
		if(report == null){
			report = "";
		}

		// PreparedStatements can use variables and are more efficient
		try {
			//establish connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost/sbs_db?"
					+ "user=root&password=");
			
			preparedStatement = conn
					.prepareStatement("INSERT INTO sbs_userdata VALUES (default, ?, ?, ?, ?, ?)");
			// Parameters start with 1
			preparedStatement.setString(1, userName);
			preparedStatement.setInt(2, scenarioId);
			preparedStatement.setFloat(3, (float) score);
			preparedStatement.setString(4, report);
			preparedStatement.setString(5, "No debriefing.");
			
			preparedStatement.executeUpdate();
			
			preparedStatement = conn.prepareStatement("SELECT LAST_INSERT_ID()");
			ResultSet res = preparedStatement.executeQuery();
			res.absolute(1);
			return res.getInt(1);
			
		} catch (SQLException e) {
			System.err.println("Error occurs while database updating!");
			e.printStackTrace();
		} finally {
			close();
		}
		return -1;
	}
	
	
	public static void updateDebrief(int id, String debrief){	

		// PreparedStatements can use variables and are more efficient
		try {
			//establish connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost/sbs_db?"
					+ "user=root&password=");
			
			preparedStatement = conn
					.prepareStatement("UPDATE sbs_userdata SET debrief = ? WHERE id = ?");
			// Parameters start with 1
			preparedStatement.setString(1, debrief);
			preparedStatement.setInt(2, id);
	
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error occurs while database updating!");
			e.printStackTrace();
		} finally {
			close();
		}
	}
	public static void removeById(int Id){
		try {
			//establish connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost/sbs_db?"
					+ "user=root&password=");
			
			preparedStatement = conn
					.prepareStatement("DELETE FROM sbs_userdata WHERE Id=" + Id);
			// Result set get the result of the SQL query
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error occurs while delete entry from database!");
			e.printStackTrace();
		} finally {
			close();
		}
	}

	//close the connection in finalize function
	private static void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			System.err.println("Error occurs while close DB connection");
		}
	}

	@Override
	protected void finalize() throws Throwable {
		try {
			System.out.println("close databse connection");
			close();
			// release resources, perform cleanup ;
		} catch (Throwable t) {
			throw t;
		}
	}
		
	//test functions
	public static void main(String[] args) throws Exception {
//		DBHelper.insertResult("test1", 1, 10.0, "aaa");
//		DBHelper.insertResult("test2", 1, 10.0, "bbb");
//		DBHelper.removeById(2);
//		DBHelper.updateDebrief(17, "lalala");
//		DBHelper.displayDataBase();
	}
}
