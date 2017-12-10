package action;

import java.sql.SQLException;

import connect.sqlAction;

public class Student extends Person{
	
	public static void setName(String name) {
		String command = "UPDATE teacher SET s_name ='"+name+"' WHERE ID = '" + Person.id+"';";
		sqlAction.executeSQL(command);
	}
	public static void setPassword(String password) {
		String command = "UPDATE teacher SET s_password ='"+ password +"' WHERE ID = '" + Person.id+"';";
		sqlAction.executeSQL(command);
	}
	public static void cancelReservation(int r_id) throws SQLException {
		sqlAction s = new sqlAction();
		String command = "SELECT student FROM reservation WHERE r_id = "+ r_id +";";
		s.executeSQL(command);
		if(s.getString("student").equals(Student.id)) {
			command = "UPDATE reservation SET student = null WHERE r_id = "+ r_id + ";";
			sqlAction.executeSQL(command);
		}//else throw??
	}
	public static void setReservation(int r_id) throws SQLException {
		boolean test = false;
		String command = "SELECT r_id FROM reservation WHERE isvaild = true AND vaild_stu LIKE '%"
				+ Student.id +"';";
		sqlAction act = new sqlAction();
		act.executeSQL(command);
		while(act.rs.next()) {
			if(act.getNum("r_id") == r_id) test = true;
		}
		if (test) {
			command = "UPDATE reservation SET student ="+Student.id+" WHERE r_id ="+r_id+";";
			sqlAction.executeSQL(command);
			command = "UPDATE reservation SET isvaild = false WHERE r_id ="+r_id+";";
		}//else???
	}
	public static void getVaildReservation() {
		sqlAction act = new sqlAction();
		String command ="SELECT ";
	}
}
