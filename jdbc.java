package connect;

import java.sql.*;

import java.util.ArrayList;

public class jdbc {

	//DB's information

	private static final String DRIVERCLASS = "com.mysql.jdbc.Driver";

	private static final String URL = "";//tbc

	private static final String USERNAME = "root";

	private static final String PASSWORD = "123456";

	private static ArrayList<Connection> connset = new ArrayList<>();

	public static Connection conn = connset.get(0);

	public static Statement statement;

	

	public Connection connectToDB() throws SQLException {

	    // Connection to the database

	    if (conn == null) try {

	      Class.forName(DRIVERCLASS);
	      
	      //system.out.println("驱动加载成功");
	      
	      //system.out.println("数据库连接建立成功");
	     
	      conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

	      connset.add(conn);

	    } 

	    catch (ClassNotFoundException ex) {
	    	
	      //system.out.println("加载数据库驱动程序异常");
	      
	      ex.printStackTrace();

	    }

	    return conn;

	  }

	

	public void closeConnection() throws CloseConnnectionException{

		if(conn != null) {

			try {
				
				conn.close();

			}catch(SQLException e) {

				//system.out.println("数据库连接无法关闭");
				
				e.printStackTrace();

				

			}

		}

	}

	 

	}

