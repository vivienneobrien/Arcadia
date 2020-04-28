package Socket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *	Database wrapper class
 */
public class DBConnect {
	private static final String driver = "org.postgresql.Driver"; // file input
	private static final String url = "jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk/";
	private static final String username = "cordoba";
	private static final String password = "9x14kds0hb";
	private static Connection con = null;
	//Load Driver
	static
	{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){

		if(con == null){
			try {
				con = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}
}
