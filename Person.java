package action;

import connect.sqlAction;

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
		connect.sqlAction.executeSQL("SELECT PASSWORD FROM" + getType(id) + "WHERE "+ id.charAt(0) +"_id = '"+ id +"';");
		password = connect.sqlAction.getString("password");
		return inputPassword == password ? true:false;
	}
	public static String getType(String id) {
		if (id.charAt(0) == 's') return "student";
		else if (id.charAt(0) == 't') return "teacher";
		else if (id.charAt(0) == 'a') return "administration";
		else return "wrong";
	}
	public static void getName() {
		String command = "SELECT name FROM "+ getType(Person.id) +"WHERE ID = '" + Person.id+"';";
		sqlAction.executeSQL(command);
		//
	}
	public static void setInfo(String info) {
		String command = "UPDATE "+ getType(Person.id) +"SET information ='"+info+"' WHERE ID = '" + Person.id+"';";
		sqlAction.executeSQL(command);
	}
	
}
