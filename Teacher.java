package action;

import connect.sqlAction;

public class Teacher extends Person{
	public String address;
	public static void setAddress(String address) {
		String command = "UPDATE teacher SET address ='"+address+"' WHERE ID = '" + Person.id+"';";
		sqlAction.executeSQL(command);
	}
	public static void setName(String name) {
		String command = "UPDATE teacher SET t_name ='"+name+"' WHERE ID = '" + Person.id+"';";
		sqlAction.executeSQL(command);
	}
	public static void setPassword(String password) {
		String command = "UPDATE teacher SET t_password ='"+ password +"' WHERE ID = '" + Person.id+"';";
		sqlAction.executeSQL(command);
	}
	
}
