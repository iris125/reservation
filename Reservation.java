package action;

import java.sql.SQLException;
import java.util.Calendar;

import connect.sqlAction;

public class Reservation {
	public static int maxVaildNum;
	public static int minRegretDate;
	private int id,distance,year,month;
	
	private String t_id,s_id,date,building,room,begintime,finishtime,vaild_stu;
	private boolean isVaild,wellfinished;
	
	
	public String command = "";
	Reservation(int id) throws SQLException{
		command = "SELECT maxVaildNum,minRegretDate FROM setting;";
		sqlAction s = new sqlAction();
		maxVaildNum = s.rs.getInt("maxVaildNum");
		minRegretDate = s.rs.getInt("minRegretDate");
		command = "SELECT * FROM reservation where r_id = "+id+";";
		this.id = id;
		this.distance = s.rs.getInt("diatance");
		this.year = s.rs.getInt("year");
		this.month = s.rs.getInt("month");
		this.t_id = s.rs.getString("teacher");
		this.s_id = s.rs.getString("student");
		this.date = s.rs.getString("r_date");
		this.building = s.rs.getString("building");
		this.room = s.rs.getString("room");
		this.begintime = s.rs.getString("begintime");
		this.finishtime = s.rs.getString("finishtime");
		this.vaild_stu = s.rs.getString("vaild_stu");
		this.isVaild = s.rs.getBoolean("isvaild");
		this.wellfinished = s.rs.getBoolean("wellfinished");
	}
	Reservation() throws SQLException{
		command = "SELECT maxVaildNum,minRegretDate FROM setting;";
		sqlAction s = new sqlAction();
		maxVaildNum = s.rs.getInt("maxVaildNum");
		minRegretDate = s.rs.getInt("minRegretDate");
	}
	
	
}
