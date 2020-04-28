package Socket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.User;
import Socket.DBConnect;

/**
 * Operation handling on the client side
 */
public class DatabaseProtocols {
	
	//When log in, check if the account exists
	public boolean checkUser(User user) {
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		conn = DBConnect.getConnection();
		String sql = "select * from userdetails where username=? and password=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	//When registering, check whether you have already registered the account
	public boolean checkregistUser(User user) {
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		conn = DBConnect.getConnection();
		String sql = "select * from userdetails where username=? or email=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2,  user.getEmail());
			rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	//Complete user registration
	public boolean registUser(User user) {
		PreparedStatement stmt = null;
		Connection conn = null;
		conn = DBConnect.getConnection();
		String sql = "insert into userdetails(first_name,last_name,"
				+ "username,password,email,biography)" + "values (?,?,?,?,?,?)";

		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getFirst_name());
			stmt.setString(2, user.getLast_name());
			stmt.setString(3, user.getUsername());
			stmt.setString(4, user.getPassword());
			stmt.setString(5, user.getEmail());
			stmt.setString(6, user.getBiography());
			stmt.executeUpdate();;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

}
