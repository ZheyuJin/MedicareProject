package dao;

import org.slf4j.*;
import model.*;
import java.sql.PreparedStatement;


import org.apache.log4j.BasicConfigurator;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class UsersDao {
	
	private static UsersDao instance = null;
	
	public static UsersDao getInstance() {
		if (instance == null) {
			instance = new UsersDao();
		}
		return instance; 
	}
	
	public void create(User user) {
		
		System.out.println("began create");
	//BasicConfigurator.configure();
		
	Cluster cluster;
	Session session;
	cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
	session = cluster.connect("demo");
	
	System.out.println("started session");
	
	String insertUser = "INSERT INTO users (lastname, age, city, email, firstname) VALUES ('?', ?, '?', '?', '?');";
	//PreparedStatement insertStmt = null;
	
	insertUser = insertUser.replaceFirst("[?]", user.getLastName());
	insertUser = insertUser.replaceFirst("[?]", "" + user.getAge());
	insertUser = insertUser.replaceFirst("[?]", user.getCity());
	insertUser = insertUser.replaceFirst("[?]", user.getEmail());
	insertUser = insertUser.replaceFirst("[?]", user.getFirstName());
	
	System.out.println(insertUser);
	
//	insertStmt.setString(1,  user.getLastName());
//	insertStmt.setInt(2, user.getAge());
//	insertStmt.setString(3, user.getCity());
//	insertStmt.setString(4, user.getEmail());
//	insertStmt.setString(5, user.getFirstName());
//	return user;
    session.execute(insertUser);

 // Clean up the connection by closing it
 		cluster.close();
   // return user;
	// Insert one record into the users table
//			session.execute("INSERT INTO users (lastname, age, city, email, firstname) VALUES ('Jones', 35, 'Austin', 'bob@example.com', 'Bob')");
	}
}
