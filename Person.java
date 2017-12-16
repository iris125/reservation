

package action;



import java.sql.SQLException;

import connect.sqlAction;



public class Person {

	Person(String id){
		this.id = id;
		
		String command = "SELECT name,information FROM "+ getType(this.id) +"WHERE ID = '" + this.id+"';";

		sqlAction s = new sqlAction();
		s.executeSQL(command);
		name = s.rs.getString("name");
		info = s.rs.getString("information");
	}
	Person(String id,String password){

		this.id = id;

		this.password = password;
		
		String command = "SELECT name,information FROM "+ getType(this.id) +"WHERE ID = '" + this.id+"';";

		sqlAction s = new sqlAction();
		
		s.executeSQL(command);

		name = s.rs.getString("name");
		info = s.rs.getString("information");

	}

	public String id;

	public String name;

	public String password;

	public String inputPassword;
	
	public String info;

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

	public String getName() throws SQLException {
		
		return name; 

	}
	
	public String getInfo() throws SQLException{
		return info;
	}

	public void setInfo(String info) throws SQLException {

		String command = "UPDATE "+ getType(this.id) +"SET information ='"+info+"' WHERE id = '" + this.id+"';";

		sqlAction.executeSQL(command);

	}

	public void setName(String name){
		String command = "UPDATE" + getType(this.id)+"SET name = '"+name+"' WHERE id ='"+this.id+";";
		sqlAction.executeSQL(command);
	}
	
	public void setPassword(String password){
		String command = "UPDATE" + getType(this.id)+"SET password = '"+password+"' WHERE id ='"+this.id+";";
		sqlAction.executeSQL(command);
	}
}
