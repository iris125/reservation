package action;



import java.sql.SQLException;

import connect.sqlAction;



public class Teacher extends Person{

	public String address;

	
	public int maxnumOfMonth;

	public int maxtimeOfMonth;

	public int maxunllnum;

	public static String command = "";

	Teacher(String id,String password){

		super(id,password);
		
		command = "SELECT maxnumOfMonth,maxtimeOfMonth,maxunllnum,address FROM teacher WHERE t_id = '" + id + "';";

		aqlAction s = new sqlAction();
		
		s.executeSQL(command);

		maxnumOfMonth = s.rs.getInt("maxnumOfMonth");

		maxtimeOfMonth = s.rs.getInt("maxtimeOfMonth");

		maxnumOfMonth = s.rs.getInt("maxunllnum");
		
		address = s.rs.getString("address");
	}

	Teacher(String id)throws SQLException {
		
		super(id);
		
		command = "SELECT maxnumOfMonth,maxtimeOfMonth,maxunllnum,address FROM teacher WHERE t_id = '" + id + "';";

		aqlAction s = new sqlAction();
		
		s.executeSQL(command);

		maxnumOfMonth = s.rs.getInt("maxnumOfMonth");

		maxtimeOfMonth = s.rs.getInt("maxtimeOfMonth");

		maxnumOfMonth = s.rs.getInt("maxunllnum");
		
		address = s.rs.getString("address");

	}

	public void setMaxnumOfMonth(int n) throws SQLException {

		command = "UPDATE teacher SET maxnumOfMonth ="+ n +" WHERE t_id = '" + this.id+"';";

		sqlAction.executeSQL(command);

	}

	public void setMaxtimeOfMonth(int n) throws SQLException {

		command = "UPDATE teacher SET MaxtimeOfMonth ="+ n +" WHERE t_id = '" + this.id+"';";

		sqlAction.executeSQL(command);

	}

	public void setMaxunllnum(int n) throws SQLException {

		command = "UPDATE teacher SET maxunllnum ="+ n +" WHERE t_id = '" + this.id+"';";

		sqlAction.executeSQL(command);

	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws SQLException {

		command = "UPDATE teacher SET address ='"+address+"' WHERE t_id = '" + this.id+"';";

		sqlAction.executeSQL(command);

	}

	public void setName(String name) throws SQLException {

		command = "UPDATE teacher SET t_name ='"+name+"' WHERE t_id = '" + this.id+"';";

		sqlAction.executeSQL(command);

	}

	public  void setPassword(String password) throws SQLException {

		command = "UPDATE teacher SET t_password ='"+ password +"' WHERE t_id = '" + this.id+"';";

		sqlAction.executeSQL(command);

	}

	public void setResult(String r_id) throws SQLException {

		command = "SELECT distance FROM reservation WHERE teacher = '"+this.id+"' AND r_id = "+r_id+";";

		sqlAction.executeSQL(command);

		if (sqlAction.rs.getInt("diatance") <= 0) {

			command = "UPDATE reservation SET wellfinished = false WHERE r_id = "+ r_id +";"; 

			sqlAction.executeSQL(command);

		}

	}public void addValid_Stu(int r_id,String s_id) throws SQLException {
		
		command = "SELECT valid_stu FROM reservation WHERE teacher = '"+this.id+"' AND r_id = "+r_id+";";
		
		sqlAction.executeSQL(command);
		
		String allstu=sqlAction.rs.getString("valid_stu")+" "+s_id+" ";
		
		command="UPDATE reservation SET vaild_stu='"+ allstu +"' WHERE r_id = '" + r_id + "';";
		
		sqlAction.executeSQL(command);
		
	}public String CheckNumOfRes() throws SQLException {
		sqlAction.updateReservation();
		
		int count=0;
		
		String n = null;
		
		command="SELECT valid_stu FROM reservation WHERE teacher = '"+this.id+"';";
		
		sqlAction s = new sqlAction();
		
		s.executeSQL(command);
		
		while(s.rs.next()) {
		
		if(!s.rs.getString("student").equals(null)) {
			
			count++;
			n += s.rs.getString("r_id")+"、";
			}
		}
		return "您本月共有 "+count+" 次预约记录，预约编号分别为： "+n;
		
		
	}public String RevResult() throws SQLException {
		
		sqlAction.updateReservation();
		command= "SELECT r_id FROM reservation WHERE teacher = '"+this.id+"';";
		
		sqlAction s1 = new sqlAction();
		
		s1.executeSQL(command);
		
		ArrayList<Reservation> result = new ArrayList<>();
		
		
		String s="预约编号      预约学生ID   日期                 起始时间   终止时间    预约地点          预约状态\n";
		
		while(s1.rs.next()) 
			result.add(new Reservation(s1.rs.getInt("r_id"));
				   
		for(Reservation r : result){
			if(r.student != null){
				String place=r.building+r.room;
			
				String state;
			
				String date = r.date;
			
				if(r.wellfinished && r.distance < 0) 
					state="已完成";
				else if(r.wellfinished && r.distance>=0)
					state="未完成";
				else 
					state="失约";
			}
			
			s += String.format("%-11d%-13s%-13s%-13s%-9s%-10s%-10s%s\n",r.id,r.student,date,r.begintime,s1.rs.r.finishtime,place,state);
			}
		}
		return s;
		
	}public void changeAddress(int id,String building,String room){
		
		command = String.format("UPDATE reservation SET building='%s' room = '%s' WHERE r_id = %d;",building,room,id);
		
		sqlAction.excuteSQL(command);
	
	}public void removeStu(int id,String s_id){
	
		command = String.format("SELECT vaild_stu FROM reservation WHERE r_id =;",id);
		
		sqlAction s = new sqlAction();
		
		s.excuteSQL(command);
		
		String s = s.rs.fetString("vaild_stu");
		
		if(s.contains(s_id)) 
		
			s.replace(s_id,"");
}
