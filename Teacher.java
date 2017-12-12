package action;

import java.sql.SQLException;

import connect.sqlAction;

public class Teacher extends Person{
	public String address;
	public int maxnumOfMonth;
	public int maxtimeOfMonth;
	public int maxunllnum;
	public static String command = "";
	Teacher(){
		//此处大概需要继承Person
	}
	Teacher(String id)throws SQLException {
		command = "SELECT maxnumOfMonth,maxtimeOfMonth,maxunllnum FROM teacher WHERE t_id = '" + id + "';";
		sqlAction.executeSQL(command);
		maxnumOfMonth = sqlAction.rs.getInt("maxnumOfMonth");
		maxtimeOfMonth = sqlAction.rs.getInt("maxtimeOfMonth");
		maxnumOfMonth = sqlAction.rs.getInt("maxunllnum");
	}
	public void setMaxnumOfMonth(int n) {
		command = "UPDATE teacher SET maxnumOfMonth ="+ n +" WHERE t_id = '" + Person.id+"';";
		sqlAction.executeSQL(command);
	}
	public void setMaxtimeOfMonth(int n) {
		command = "UPDATE teacher SET MaxtimeOfMonth ="+ n +" WHERE t_id = '" + Person.id+"';";
		sqlAction.executeSQL(command);
	}
	public void setMaxunllnum(int n) {
		command = "UPDATE teacher SET maxunllnum ="+ n +" WHERE t_id = '" + Person.id+"';";
		sqlAction.executeSQL(command);
	}
	public String getAddress() {
		return address;
	}
	public static void setAddress(String address) {
		command = "UPDATE teacher SET address ='"+address+"' WHERE t_id = '" + Person.id+"';";
		sqlAction.executeSQL(command);
	}
	public static void setName(String name) {
		command = "UPDATE teacher SET t_name ='"+name+"' WHERE t_id = '" + Person.id+"';";
		sqlAction.executeSQL(command);
	}
	public static void setPassword(String password) {
		command = "UPDATE teacher SET t_password ='"+ password +"' WHERE t_id = '" + Person.id+"';";
		sqlAction.executeSQL(command);
	}
	public void setResult(int r_id) throws SQLException {
		command = "SELECT distance FROM reservation WHERE teacher = '"+Teacher.id+"' AND r_id = "+r_id+";";
		sqlAction.executeSQL(command);
		if (sqlAction.rs.getInt("diatance") <= 0) {
			command = "UPDATE reservation SET wellfinished = false WHERE r_id = "+ r_id +";"; 
			sqlAction.executeSQL(command);
		}
	}
}
