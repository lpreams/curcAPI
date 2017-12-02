package sponsor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import database.DatabaseAccess;

public class SponsorFacade {
	
	private static SponsorFacade singleton;
	private DatabaseAccess sao;
	
	public SponsorFacade() {
		try { this.sao = DatabaseAccess.getInstance(); } 
		catch(NamingException | SQLException e) { e.printStackTrace(); } 		
	}
	
	
	public static SponsorFacade getInstance() { 
		if(singleton==null) { singleton = new SponsorFacade(); }
		return singleton; 	
	}
	
	
	public ArrayList<Sponsor> getSponsors() {
		try{ Class.forName("org.h2.Driver"); } 
		catch (ClassNotFoundException e) { e.printStackTrace();	}
		
		ArrayList<Sponsor> sponsorArray = new ArrayList<Sponsor>();
		
		try{ 
			Connection con = sao.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * from sponsor");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int sponsorID = rs.getInt("sponsorID");
				String sponsorName = rs.getString("sponsorName");
				Sponsor sponsor = new Sponsor (sponsorID, sponsorName);
				sponsorArray.add(sponsor); }
			con.close(); } 
		
		catch(SQLException e) {	e.printStackTrace(); }
		return sponsorArray; 	
	}
	
	
	public ArrayList<Sponsor> getSponsorsByName(String name) {
		try{ Class.forName("org.h2.Driver"); } 
		catch(ClassNotFoundException e) { e.printStackTrace(); }
		
		ArrayList<Sponsor> sponsorList = new ArrayList<Sponsor>();
		
		try{
			Connection con = sao.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * from sponsor WHERE name LIKE ?");
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int sponsorID = rs.getInt("sponsorID");
				String sponsorName = rs.getString("sponsorID");
				Sponsor sponsor = new Sponsor(sponsorID, sponsorName);
				sponsorList.add(sponsor); }
			con.close(); }
		
		catch(SQLException e) { e.printStackTrace(); }
		return sponsorList; 
	}
	
	
	public Sponsor getSponsorByID(int id) {
		try{ Class.forName("org.h2.Driver"); } 
		catch(ClassNotFoundException e) { e.printStackTrace(); }
		
		Sponsor sponsor = null;
		
		try{
			Connection con = sao.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM sponsor where sponsorID=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int sponsorID = rs.getInt("sponsorID");
				String sponsorName = rs.getString("sponsorName");
				sponsor = new Sponsor(sponsorID, sponsorName); }
			con.close(); } 
	
		catch(SQLException e ) { e.printStackTrace();	}
		return sponsor; 
	}
	
	
	public Sponsor createSponsor(Sponsor sponsor) {
		try{ Class.forName("org.h2.Driver"); }
		catch(ClassNotFoundException e) { e.printStackTrace(); }
		
		Sponsor newSponsor = null;
	
		try {
			Connection con = sao.getConnection();
			PreparedStatement createStmt = con.prepareStatement
				("INSERT INTO sponsor (sponsorName) VALUES (?)");
			createStmt.setString(1, sponsor.getName());
			int res = createStmt.executeUpdate();
			
			if(res==1) {
				PreparedStatement retrieveStmt = con.prepareStatement
					("SELECT * from sponsor WHERE sponsorName=?");
				retrieveStmt.setString(1, sponsor.getName());
				ResultSet rs = retrieveStmt.executeQuery();
				
				while(rs.next()) {
					int sponsorID = rs.getInt("sponsorID");
					String sponsorName = rs.getString("sponsorName");
					newSponsor = new Sponsor(sponsorID, sponsorName); }	
				}
			con.close(); } 
		catch(SQLException e) { e.printStackTrace(); }
		return newSponsor;
	}
	
	

}
