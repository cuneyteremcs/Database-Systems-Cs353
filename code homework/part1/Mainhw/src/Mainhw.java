/**
 * @(#)Mainhw.java
 *
 * Mainhw application
 *
 * @author Cüneyt EREM
 * @version 1.00 2016/12/1
 */
 
import java.sql.*;

public class Mainhw {
    
    public static void main(String[] args) {
    	
    	Statement stmt = null;
    	
    	try {
    	String host= "jdbc:mysql://dijkstra2.ug.bcc.bilkent.edu.tr/cuneyt_erem";
    	String uName = "cuneyt.erem";
    	String uPass = "h3b7t7fg";
    	
    	Connection conn = DriverManager.getConnection( host, uName, uPass );
    	
    	System.out.println("try block starts");
    	
    	System.out.println("Creating table in given database...");
     	stmt = conn.createStatement();
     	
     	
		String drop = "DROP TABLE IF EXISTS guest_show; ";
		String drop2 = "DROP TABLE IF EXISTS guest; "; 
		String drop3 = 	"DROP TABLE IF EXISTS show2; ";
		String drop4 = 	"DROP TABLE IF EXISTS channel; "; 
		String drop5 = 	"DROP TABLE IF EXISTS host; ";
					
		stmt.executeUpdate(drop);
		stmt.executeUpdate(drop2);
		stmt.executeUpdate(drop3);
		stmt.executeUpdate(drop4);
		stmt.executeUpdate(drop5);
		
      	 
        String sql = "CREATE TABLE host " +
                   "(hid INTEGER not NULL, " +
                   " name VARCHAR(50), " + 
                   " nickname VARCHAR(50), " + 
                   " password VARCHAR(50), " + 
                   " title VARCHAR(50), " +	
                   " profession VARCHAR(50), " +
                   " PRIMARY KEY ( hid ))"; 
                   	      	
        String sql2 = "CREATE TABLE channel " +
                   "(cid INTEGER not NULL, " +
                   " cname VARCHAR(50), " +            	
        		   " PRIMARY KEY ( cid ))";
        		            	
        String sql3 = "CREATE TABLE show2 " +
                   "(sid INTEGER not NULL, " +
                   " pname VARCHAR(50), " + 
                   " time TIME, " +  
                   " day VARCHAR(50), " +  
                   " hid INTEGER not NULL, " + 
                   " cid INTEGER not NULL, " +
                   " FOREIGN KEY ( hid) REFERENCES host(hid), " +
                   " FOREIGN KEY ( cid) REFERENCES channel(cid), " +      	
        		   " PRIMARY KEY ( sid ))";	
        		   	   		   
        String sql4 = "CREATE TABLE guest " +
                   "(gid INTEGER not NULL, " +
                   " gname VARCHAR(50), " +  
                   " title VARCHAR(50), " +	
                   " profession VARCHAR(50), " +
                   " short_bio VARCHAR(1000), " +          	
        		   " PRIMARY KEY ( gid ))";
        		   
         String sql5 = "CREATE TABLE guest_show " +
                   "(gid INTEGER not NULL, " +
                   " sid INTEGER not NULL, " +
                   " date DATE, " + 
                   " FOREIGN KEY ( gid) REFERENCES guest(gid), " + 
                   " FOREIGN KEY ( sid) REFERENCES show2(sid), " +	
        		   " PRIMARY KEY ( gid, sid, date ))";
        

		
		
		
        stmt.executeUpdate(sql);
        stmt.executeUpdate(sql2);
        stmt.executeUpdate(sql3);
        stmt.executeUpdate(sql4);
        stmt.executeUpdate(sql5);
        
        System.out.println("Created tables in given database...");
        
        
        stmt.executeUpdate("INSERT INTO host " + 
        	"VALUES (1, 'Fatih Altaylý', 'altayli', 1111, 'Mr.', 'journalist')");
        stmt.executeUpdate("INSERT INTO host " + 
        	"VALUES (2, 'Cuneyt Ozdemir', 'ozdemir', 2222, 'Mr.', 'journalist')");
        stmt.executeUpdate("INSERT INTO host " + 
        	"VALUES (3, 'Neil deGrasse Tyson', 'tyson', 3333, 'Dr.', 'astrophysicist')");
    	
    	
    	stmt.executeUpdate("INSERT INTO channel " + 
        	"VALUES (1, 'National Gegraphic')");
        stmt.executeUpdate("INSERT INTO channel " + 
        	"VALUES (2, 'CNN TURK')");
        stmt.executeUpdate("INSERT INTO channel " + 
        	"VALUES (3, 'Haberturk')");
        	
        stmt.executeUpdate("INSERT INTO show2 " + 
        	"VALUES (1, 'Teke Tek', '23:00:00', 'Tuesday', 1, 3)");
        stmt.executeUpdate("INSERT INTO show2 " + 
        	"VALUES (2, '5N1K', '22:00:00', 'Sunday', 2, 2)");
        stmt.executeUpdate("INSERT INTO show2 " + 
        	"VALUES (3, 'Startalk', '22:00:00', 'Monday', 3, 1)");
        	
        stmt.executeUpdate("INSERT INTO guest " + 
        	"VALUES (5, 'Celal Sengor', 'Prof. Dr.', 'geologist', 'short bio 1 ')");
        stmt.executeUpdate("INSERT INTO guest " + 
        	"VALUES (6, 'Ilber Ortaylý', 'Prof. Dr.', 'historian', 'short bio 2 ')");
        stmt.executeUpdate("INSERT INTO guest " + 
        	"VALUES (7, 'Mayim Bialik', 'Mrs.', 'actress', 'short bio 3 ')");
        stmt.executeUpdate("INSERT INTO guest " + 
        	"VALUES (8, 'Orhan Pamuk', 'Mr.', 'novelist', 'short bio 4 ')");
        stmt.executeUpdate("INSERT INTO guest " + 
        	"VALUES (9, 'Fazil Say', 'Mr.', 'pianist', 'short bio 5 ')");
    	
    	stmt.executeUpdate("INSERT INTO guest_show " + 
        	"VALUES (5, 1, '2016.11.22')");
        stmt.executeUpdate("INSERT INTO guest_show " + 
        	"VALUES (6, 1, '2016.11.22')");
        stmt.executeUpdate("INSERT INTO guest_show " + 
        	"VALUES (7, 3, '2016.11.21')");
        stmt.executeUpdate("INSERT INTO guest_show " + 
        	"VALUES (8, 2, '2016.11.27')");
        stmt.executeUpdate("INSERT INTO guest_show " + 
        	"VALUES (9, 2, '2016.11.27')");
        	
    	
    	String query = "select * from host ;" ;
    	ResultSet rs = stmt.executeQuery(query) ;
    	
    	System.out.println(" host table : ");
    	while (rs.next())
     	 {
	        int hid = rs.getInt("hid");
	        String name = rs.getString("name");
	        String nickname = rs.getString("nickname");
	        int password = rs.getInt("password");
	        String title = rs.getString("title");
	        String profession = rs.getString("profession");
	        
	        System.out.format("%s, %s, %s, %s, %s, %s\n", hid, name, nickname, password, title, profession);
        }
        
        String query2 = "select * from channel ;" ;
    	ResultSet rs2 = stmt.executeQuery(query2) ;
    	
    	System.out.println(" \n channel table : ");
    	while (rs2.next())
     	 {
	        int cid = rs2.getInt("cid");
	        String cname = rs2.getString("cname");
	        
	        System.out.format("%s, %s\n", cid, cname);
        }
        
        String query3 = "select * from show2 ;" ;
    	ResultSet rs3 = stmt.executeQuery(query3) ;
    	
    	System.out.println(" \n show table : ");
    	while (rs3.next())
     	 {
	        int sid = rs3.getInt("sid");
	        String pname = rs3.getString("pname");
	        Time time = rs3.getTime("time");
	        String day = rs3.getString("day");
	        int hid = rs3.getInt("hid");
	        int cid = rs3.getInt("cid");
	        
	        System.out.format("%s, %s, %s, %s, %s, %s\n", sid, pname, time, day, hid, cid);
        }
        
        String query4 = "select * from guest;" ;
    	ResultSet rs4 = stmt.executeQuery(query4) ;
    	
    	System.out.println(" \n guest table : ");
    	while (rs4.next())
     	 {
     	 	int gid = rs4.getInt("gid");
	        String gname = rs4.getString("gname");
	        String title = rs4.getString("title");
	        String profession = rs4.getString("profession");
	        String short_bio = rs4.getString("short_bio");
	        
	        System.out.format("%s, %s, %s, %s, %s\n", gid, gname, title, profession, short_bio);
        }
        
		String query5 = "select * from guest_show ;" ;
    	ResultSet rs5 = stmt.executeQuery(query5) ;
    	
    	System.out.println(" \n guest_show table : ");
    	while (rs5.next())
     	 {
	        int gid = rs5.getInt("gid");
	        int sid = rs5.getInt("sid");
	        Date date = rs5.getDate("date");
	        
	        System.out.format("%s, %s, %s\n", gid, sid, date);
        }
        
      	stmt.close();
    	
    	}
    	catch (SQLException err) {
    		System.out.println(err.getMessage());
    	}
    	
    	
    }
}


