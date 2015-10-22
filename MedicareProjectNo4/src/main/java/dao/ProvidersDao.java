package dao;

import org.slf4j.*;
import model.*;
import java.sql.PreparedStatement;


import org.apache.log4j.BasicConfigurator;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class ProvidersDao {
	
	private static ProvidersDao instance = null;
	
	public static ProvidersDao getInstance() {
		if (instance == null) {
			instance = new ProvidersDao();
		}
		return instance; 
	}
	
	public String mostExpensive(String pcode, String state) {
		
		
    //uncomment this to use logger
	//BasicConfigurator.configure();
	
	//connect to server	
	Cluster cluster;
	Session session;
	cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
	//pick KEYSPACE
	session = cluster.connect("demo");
	
	//compose query
	String query = "SELECT * FROM M3"
                   + " WHERE pcode = '" + pcode + "' AND state = '" + state + "'"
                   + " ALLOW FILTERING;";
	
	//cannot ORDER BY unless we make a materialized view where they are ordered by price
	// at least that is my understanding
	
	//send query to cassandra
    ResultSet results = session.execute(query);

       //don't forget to close connection
 		cluster.close();
 		//convert result to a string
 		StringBuilder sb = new StringBuilder();
 		for (Row row : results) {
 			sb.append(row.getString("lastname"));
 			sb.append(" ");
 			sb.append(row.getString("firstname"));
 			sb.append("/n");
 		}
 		String resultString = sb.toString();
 		return resultString;
	}
}