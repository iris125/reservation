package connect;
import java.sql.*;
import java.util.ArrayList;
public class sqlAction{
	private static Connection conn = jdbc.conn;
	private static Statement statement = jdbc.statement;
	private static ResultSet rs;
	public static void executeSQL(String sql) {
	    if (conn == null) {
	    	//try{
	      String Text = "Please connect to a database first";
	      return;
	    }
	    else {
	      String[] commands = sql.replace('\n', ' ').split(";");
	      
	      for (String aCommand: commands) {
	        if (aCommand.trim().toUpperCase().startsWith("SELECT")) {
	          processSQLSelect(aCommand);
	        }
	        else {
	          processSQLNonSelect(aCommand);
	        }
	      }
	    }
	  }
	public static void processSQLSelect(String sql) {
	    try {
	      statement = conn.createStatement();
	      rs = statement.executeQuery(sql);
	    }catch (SQLException ex) {
	      ex.getStackTrace();
	    }
	  }
	public static void processSQLNonSelect(String sql) {
	    try {
	      statement = conn.createStatement();
	      statement.executeUpdate(sql);
	      //?????
	    }
	    catch (SQLException ex) {
	      //?????
	    }
	  }
	
	public static int getNum(String columnLabel){
		int n = 0;
		try {
			n = rs.getInt(columnLabel);
		}catch (SQLException ex) {
			ex.getStackTrace();
		}
		return n;
	}
	public static String getString(String columnLabel){
		String s = "";
		try {
			s = rs.getString(columnLabel);
		}catch (SQLException ex) {
			ex.getStackTrace();
		}
		return s;
	}

}
