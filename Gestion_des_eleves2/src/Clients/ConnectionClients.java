package Clients;
 

import java.sql.Connection;
  import java.sql.DriverManager;
   import java.sql.SQLException;
  

	
	
	public class ConnectionClients {
	 Connection cn;
	 public ConnectionClients() {
		 try {
			Class.forName("org.sqlite.JDBC");
			 cn=DriverManager.getConnection("jdbc:sqlite:data2/ecole2");
			 System.out.println("connection Etablie a SQLite");
		 } catch(Exception e) {
			 System.out.println("Erreur: Pilote SQLite JDBC introuvable ");
			 e.printStackTrace();
		 }
	 }
	 
	 
	 public Connection maConnection() {
		 return cn;
	 }
	
	}


