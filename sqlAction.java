package connect;

import java.sql.*;

import java.util.ArrayList;

import java.util.Calendar;

public class sqlAction{

	public static Connection conn = jdbc.conn;

	public static Statement statement = jdbc.statement;

	public static ResultSet rs;

	public static void executeSQL(String sql) throws SQLException {

	    if (conn == null) {

	    	//try{

	      String Text = "连接无法找到，请先建立连接";

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

	public static void processSQLSelect(String sql) throws SQLException {

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

	public static String getString(String columnLabel) throws SQLException{

		String s = "";

		try {

			s = rs.getString(columnLabel);

		}catch (SQLException ex) {

			ex.getStackTrace();

		}

		return s;

	}

	public static void updateReservation() throws SQLException {

		Calendar now = Calendar.getInstance();

		String today = "'"+now.YEAR+"-"+now.MONTH+"-"+now.DAY_OF_MONTH+"'";

		String command = "";

		sqlAction.executeSQL("SELECT r_id,r_date FROM reservation;");

		while(sqlAction.rs.next()){

			command += "UPDATE reservation SET distance = DATEDIFF('"+sqlAction.rs.getDate("r_date")

				+"',"+ today +") WHERE r_id = "+sqlAction.rs.getInt("r_id")+";";

		}

		sqlAction.executeSQL(command);

		command = "UPDATE reservation SET vaild = false WHERE distance <= 0;";

		sqlAction.executeSQL(command);

	}

}
