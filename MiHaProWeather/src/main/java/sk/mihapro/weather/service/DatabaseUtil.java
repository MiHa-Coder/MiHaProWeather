package sk.mihapro.weather.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Class for creating and closing connection, creating table 
 * and saving data to the database.
 * 
 * To be done:  Saving whole json file as object in the database
 * 
 * @author MiHaPro
 *
 */
public class DatabaseUtil {

	// required parameters for creating connection
	private final String url = "jdbc:postgresql://localhost/weather";
	private final String user = "postgres";
	private final String password = "sa";

	// creating connection to database
	public Connection createConnection() {

		Connection con = null;

		try {

			con = DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return con;
	}

	// closing connection with database
	public void closeConnection(Connection con, Statement stmt, PreparedStatement pstat, ResultSet rs) {

		try {
			if (rs != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (pstat != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (stmt != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// inserting selected data to database
	public void addWeatherDataToTable(String location, Double temperature, Double pressure, Double humidity,
			Double windSpeed, Double windAngle) {

		DatabaseUtil ctd = new DatabaseUtil();
		Connection con = ctd.createConnection();
		Statement stmt = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;

		try {

			// SQL statement for reading max id value from database table
			String sql = "SELECT MAX(id) AS max_id FROM weatherData";
			pstat = con.prepareStatement(sql);
			rs = pstat.executeQuery();
			int id = 0;

			if (rs.next()) {

				// getting max id and incrementing by one
				id = rs.getInt("max_id") + 1;
				stmt = con.createStatement();

				// SQL statement for inserting selected data to database table
				sql = "INSERT INTO weatherdata VALUES (" + id + ", '" + location + "', '" + temperature + "', "
						+ pressure + ", " + humidity + ", " + windSpeed + ", " + windAngle + ")";
				stmt.executeUpdate(sql);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			closeConnection(con, stmt, pstat, rs);

		}
	}

	// Creating table in database if does no exists
	public void createTable() {

		DatabaseUtil ctd = new DatabaseUtil();
		Connection con = ctd.createConnection();
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstat = null;

		try {

			// SQL statement for crating new table in database 
			// if given table does not exists
			String sql = "CREATE TABLE IF NOT EXISTS weatherData(id SERIAL NOT NULL PRIMARY KEY, location varchar, temperature double precision,"
					+ "	pressure double precision, humidity double precision, wind_speed double precision, wind_angle double precision)";
			pstat = con.prepareStatement(sql);
			pstat.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			closeConnection(con, stmt, pstat, rs);

		}
	}
}
