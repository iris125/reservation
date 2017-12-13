package action;

import java.sql.SQLException;
import java.util.Calendar;

import connect.*;

public class Student extends Person{
	public String command = "";
	public void setName(String name) {
		command = "UPDATE teacher SET s_name ='"+name+"' WHERE t_id = '" + Person.id+"';";
		sqlAction.executeSQL(command);
	}
	public void setPassword(String password) {
		command = "UPDATE teacher SET s_password ='"+ password +"' WHERE t_id = '" + Person.id+"';";
		sqlAction.executeSQL(command);
	}
	public void cancelReservation(int r_id) throws SQLException {
		sqlAction.updateReservation();
		command = "SELECT student,distance FROM reservation WHERE r_id = "+ r_id +";";
		sqlAction.executeSQL(command);
		int n = Reservation.minRegretDate;//最小允许取消的时间
		boolean test = true;
		if(sqlAction.getString("student").equals(Student.id) && sqlAction.getNum("distance") >= n) {
			command = "UPDATE reservation SET student = null WHERE r_id = "+ r_id + ";";
			sqlAction.executeSQL(command);
		}//else??
	}
	public boolean testVaild(String teacher) throws SQLException {
		sqlAction.updateReservation();
		boolean test = true;
		Teacher t = new Teacher(teacher);
		command = "SELECT COUNT(*) AS time,SUM(length) AS totallength FROM reservation WHERE student = '"
			+ Person.id +"' AND r_month = "+ Calendar.MONTH + ";";
		sqlAction.executeSQL(command);
		int myLength = sqlAction.rs.getInt("totallength");
		int myTime = sqlAction.rs.getInt("time");
		int maxLength = t.maxtimeOfMonth;
		int maxTime = t.maxtimeOfMonth;
		test = myLength < maxLength && myTime < maxTime ? true:false;
		return test;
	}
	public void setReservation(int r_id) throws SQLException {
		boolean test = false;
		String teacher = "";
		command = "SELECT r_id FROM reservation WHERE isvaild = true AND vaild_stu LIKE '%"
				+ Student.id +"%';";
		sqlAction.executeSQL(command);
		while(sqlAction.rs.next()) {
			if(sqlAction.getNum("r_id") == r_id) {
				test = true;
				teacher = sqlAction.rs.getString("teacher");
				break;
			}
		}
		if (testVaild(teacher) && test) {
			command = "UPDATE reservation SET student ="+Student.id+" WHERE r_id ="+ r_id +";";
			sqlAction.executeSQL(command);
			command = "UPDATE reservation SET isvaild = false WHERE r_id =" + r_id +";";
		}//else???
	}
	public void getVaildReservation() throws SQLException {
		sqlAction.updateReservation();
		command ="SELECT * FROM reservation WHERE vaild = true AND vaild_stu LIKE '%"+Person.id+
				"%' AND diatance BETWEEN 0 AND" + Reservation.maxVaildNum+ " ;";
		sqlAction.executeSQL(command);
		//???
	}
	public void getMyReservation() throws SQLException {
		sqlAction.updateReservation();
		command = "SELECT r_id,teacher,r_date,begintime,finishtime,building,room,wellfinished FROM reservation WHERE student='"
				+Student.id+"' ORDER BY distance;";
		sqlAction.executeSQL(command);
		//???
	}
	public void getNullReservation() throws SQLException {
		sqlAction.updateReservation();
		command = "SELECT r_id,teacher,r_date,begintime,finishtime,building,room,wellfinished FROM reservation WHERE student='"
				+Student.id+"' AND wellfinished = false ORDER BY distance;";
		sqlAction.executeSQL(command);
		//???
	}
}
