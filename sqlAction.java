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
	public static void updateReservation() throws SQLException {
		Calendar now = Calendar.getInstance();
		String today = now.YEAR+"-"+now.MONTH+"-"+now.DAY_OF_MONTH;
		sqlAction s1 = new sqlAction();
		sqlAction s2 = new sqlAction();
		s1.executeSQL("SELECT r_id,r_date FROM reservation;");
		while(s1.rs.next()){
			String command = "UPDATE reservation SET diatance = DATEDIFF('"+s1.rs.getDate("r_date")
				+"',"+ today +") WHERE r_id = "+s1.rs.getInt("r_id")+";";
			s2.executeSQL(command);
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
