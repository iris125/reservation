package connect;
import java.sql.*;
import java.util.ArrayList;
public class jdbc {
	//DB's information
	private static final String DRIVERCLASS = "com.mysql.jdbc.Driver";
	private static final String URL = "";//tbc
	private static final String USERNAME = "root";
	private static final String PASSWORD = "123456";
	private static ArrayList<Connection> connset = new ArrayList<>();
	public static Connection conn = connset.get(0);
	public static Statement statement;
	
	public Connection connectToDB() {
	    // Connection to the database
	    if (conn == null) try {
	      Class.forName(DRIVERCLASS);
	      conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	      connset.add(conn);
	    } 
	    catch (Exception ex) {
	      ex.printStackTrace();
	    }
	    return conn;
	  }
	
	public void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
				//????
			}
		}
	}
	 
	}

