package action;



import java.sql.SQLException;

import connect.sqlAction;



public class Person {

	Person(){
		
	}
	Person(String id,String password){

		this.id = id;

		this.password = password;

	}

	public String id;

	public String name;

	public String password;

	public String inputPassword;

	public boolean testRegist() throws SQLException {

		connect.sqlAction.executeSQL("SELECT PASSWORD FROM" + getType(id) + "WHERE "+ id.charAt(0) +"_id = '"+ id +"';");

		password = connect.sqlAction.getString("password");

		return inputPassword == password ? true:false;

	}

	public String getType(String id) {

		if (id.charAt(0) == 's') return "student";

		else if (id.charAt(0) == 't') return "teacher";

		else if (id.charAt(0) == 'a') return "administration";

		else return "wrong";

	}

	public void getName() throws SQLException {

		String command = "SELECT name FROM "+ getType(this.id) +"WHERE ID = '" + this.id+"';";

		sqlAction.executeSQL(command);

		//

	}

	public void setInfo(String info) throws SQLException {

		String command = "UPDATE "+ getType(this.id) +"SET information ='"+info+"' WHERE ID = '" + this.id+"';";

		sqlAction.executeSQL(command);

	}

	

}
