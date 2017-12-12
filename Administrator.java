package action;

import connect.sqlAction;

public class Administrator extends Person{
	
	public void addTeacher(String name) {
		String id = "t";
		for(int i = 1;i <= 6;i++) {
			int n = (int)(Math.random()*10);
			id += n;
		}
		String password = "666666";
		String command = String.format("INSERT INTO teacher(t_id,t_name,t_password) values ('%s','%s','%s');", id,name,password);
		sqlAction.executeSQL(command);
	}
	public void addStudent(String name) {
		String id = "s";
		for(int i = 1;i <= 6;i++) {
			int n = (int)(Math.random()*10);
			id += n;
		}
		String password = "666666";
		String command = String.format("INSERT INTO student(s_id,s_name,s_password) values ('%s','%s','%s');", id,name,password);
		sqlAction.executeSQL(command);
	}
	public void setMaxVaildNum(int n) {
		String command = "UPDATE setting SET MaxVaildNum = "+ n +";";
		sqlAction.executeSQL(command);
	}
	public void setMinRegretDate(int n) {
		String command = "UPDATE setting SET minRegretDate = "+ n +";";
		sqlAction.executeSQL(command);
	}
}
