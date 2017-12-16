package action;



import java.sql.SQLException;

import java.util.Calendar;



import connect.*;



public class Student extends Person{

	public String command = "";
	
	public Student(String id ,String password) {
		super(id,password);
		
	}

	public void cancelReservation(int r_id) throws SQLException {

		sqlAction.updateReservation();
		
		Reservation r= new Reservation(r_id);

		int n = Reservation.minRegretDate;//最小允许取消的时间

		boolean test = true;

		if(r.student.equals(this.id) && r.distance >= n) {

			command = "UPDATE reservation SET student = null WHERE r_id = "+ r_id + ";";

			sqlAction newact = new sqlAction();
			
			act.executeSQL(command);

		}else {
			//system.out.println("超过预约取消期限，预约取消操作无效！");
		}

	}

	public boolean testVaild(String teacher) throws SQLException {

		sqlAction.updateReservation();

		boolean test = true;

		Teacher t = new Teacher(teacher);
		
		sqlAction s = new sqlAction();
		
		Calenda c = Calendar.getInstance();

		command = "SELECT COUNT(*) AS time,SUM(length) AS totallength FROM reservation WHERE student = '"

			+ this.id +"' AND r_month = "+ c.get(Calendar.MONTH) + "AND r_year = "+c.get(Calendar.YEAR)+";";

		s.executeSQL(command);

		int myLength = s.rs.getInt("totallength");

		int myTime = s.rs.getInt("time");

		int maxLength = t.maxtimeOfMonth;

		int maxTime = t.maxtimeOfMonth;

		test = myLength < maxLength && myTime < maxTime ? true:false;

		return test;

	}

	public void setReservation(int r_id) throws SQLException {
		
		Reservation r = new Reservation(r_id);
		
		String teacher = r.teacher;
		
		boolean test = r.vaild_stu.contains(this.id) ? ture : false; 

		if (testVaild(teacher) && test) {

			command = "UPDATE reservation SET student ="+this.id+" WHERE r_id ="+ r_id +";";

			s.executeSQL(command);

			command = "UPDATE reservation SET isvaild = false WHERE r_id =" + r_id +";";

		}//else System.out.print("不符合预约条件");

	}

	public void getVaildReservation() throws SQLException {
		
		sqlAction s = new sqlAction();

		sqlAction.updateReservation();

		command ="SELECT r_id FROM reservation WHERE vaild = true AND vaild_stu LIKE '%"+this.id+

				"%' AND diatance BETWEEN 0 AND" + Reservation.maxVaildNum+ " ;";

		s.executeSQL(command);

		ArrayList<Reservation> allvaild = new ArrayList<>();
		
		while(s.rs.Next()){
			String r_id = rs.getInt("r_id");
			Reservation r = new Reservation(r_id);
			allvaild.add(r);
		}
		//print allvaild??
	}

	public void getMyReservation() throws SQLException {

		sqlAction.updateReservation();
		
		sqlAction s = new sqlAction();

		command = "SELECT r_id,teacher,r_date,begintime,finishtime,building,room,wellfinished FROM reservation WHERE student='"

				+this.id+"' ORDER BY distance;";

		sqlAction.executeSQL(command);

		ArrayList<Reservation> my = new ArrayList<>();
		
		while(s.rs.Next()){
			String r_id = rs.getInt("r_id");
			Reservation r = new Reservation(r_id);
			my.add(r);
		}
		
		//???

	}

	public void getNullReservation() throws SQLException {

		sqlAction.updateReservation();

		command = "SELECT r_id FROM reservation WHERE student='" +this.id+"' AND wellfinished = false ORDER BY distance;";

		sqlAction.executeSQL(command);


		ArrayList<Reservation> my = new ArrayList<>();
		
		while(s.rs.Next()){
			String r_id = rs.getInt("r_id");
			Reservation r = new Reservation(r_id);
			my.add(r);
		}

	}

}
