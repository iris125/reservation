package action;
import connect.*;
import java.sql.*;
import connect.jdbc;
public class Person {
	Person(){		
	}
	Person(String id,String password){
		this.id = id;
		this.password = password;
	}
	public static String id;
	public static String name;
	public static String password;
	public static String inputPassword;
	public static boolean testRegist() {
		connect.sqlAction.executeSQL("SELECT PASSWORD FROM" + getType(id) + "WHERE ID = " + "'id';");
		password = connect.sqlAction.getString("password");
		return inputPassword == password ? true:false;
	}
	public static String getType(String s) {
		if (s.charAt(0) == 's') return "student";
		if (s.charAt(0) == 't') return "teacher";
		if (s.charAt(0) == 'a') return "administration";
	}
	
}
