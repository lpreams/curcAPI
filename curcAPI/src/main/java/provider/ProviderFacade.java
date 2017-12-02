package provider;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.naming.NamingException;

import database.DatabaseAccess;

public class ProviderFacade {
	
	private static ProviderFacade singleton;
	private DatabaseAccess pao;
	
	public ProviderFacade() {
		try{ this.pao = DatabaseAccess.getInstance(); } 
		catch(NamingException | SQLException e) { e.printStackTrace(); }
	}
	
	
	public static ProviderFacade getInstance() {
		if(singleton==null) { singleton = new ProviderFacade(); }
		return singleton;
	}
	
	
	public ArrayList<Provider> getProviders() {	
		try { Class.forName("org.h2.Driver"); } 
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		
		ArrayList<Provider> providerArray = new ArrayList<Provider>();
		
		try{
			Connection con = pao.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT provID, provLName, provFName, provCredentials FROM provider");
			ResultSet rs = stmt.executeQuery();
		
			while(rs.next()) {
				int prov_id = rs.getInt("provID");
				String lName = rs.getString("provLName");
				String fName = rs.getString("provFName");
				String credentials = rs.getString("provCredentials");
				Provider provider = new Provider(prov_id, lName, fName, credentials);
				providerArray.add(provider); }
			
			con.close(); } 
		catch(SQLException e) { e.printStackTrace(); }
		return providerArray;
	}

	
	public ArrayList<Provider> getProviderByName(String lastName, String firstName) {
		try { Class.forName("org.h2.Driver"); } 
		catch (ClassNotFoundException e) { e.printStackTrace(); }
				
		ArrayList<Provider> providerArray = new ArrayList<Provider>();
		
		try{
			Connection con = pao.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT provID, provLName, provFName, provCredentials, provLicenseNum, provNPI FROM provider WHERE lName LIKE ? AND fName LIKE ?");
			stmt.setString(1, lastName + "%");
			stmt.setString(2, firstName + "%");
			ResultSet rs = stmt.executeQuery();
		
			while(rs.next()) {
				int prov_id = rs.getInt("provID");
				String lName = rs.getString("provLName");
				String fName = rs.getString("provFName");
				String credentials = rs.getString("provCredentials");
				String licenseNum = rs.getString("provLicenseNum");
				String npi = rs.getString("provNPI");
				Provider provider = new Provider(prov_id, lName, fName, credentials, licenseNum, npi);
				providerArray.add(provider); }
			con.close(); } 
		catch(SQLException e) { e.printStackTrace(); }
		return providerArray;		
	}
	
	
	public Provider getProviderByID(int theID) {
		try { Class.forName("org.h2.Driver"); } 
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		
		Provider provider = null;
	
		try{
			Connection con = pao.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT provID, provLName, provFName, provCredentials, provLicenseNum, provNPI FROM provider WHERE id=?");
			stmt.setInt(1, theID);
			ResultSet rs = stmt.executeQuery();
		
			while(rs.next()) {
				int prov_id = rs.getInt("provID");
				String lName = rs.getString("provLName");
				String fName = rs.getString("provFName");
				String credentials = rs.getString("provCredentials");
				String licenseNum = rs.getString("provLicenseNum");
				String npi = rs.getString("provNPI");
				provider = new Provider(prov_id, lName, fName, credentials, licenseNum, npi); }
			con.close(); } 
		catch(SQLException e) { e.printStackTrace(); }
		return provider;
	}
	
	
	public Provider createProvider(Provider provider) {
		try { Class.forName("org.h2.Driver"); } 
		catch (ClassNotFoundException e1) { e1.printStackTrace(); }
				
		Provider newProvider = null;
		
		try{
			Connection con = pao.getConnection();
			PreparedStatement createStmt = con.prepareStatement
					("INSERT INTO provider (provID, provLName, provFName, provMName, provCredentials, provLicenseNum, provNPI, provDOB) VALUES (?,?,?,?,?,?,?,?)");
			createStmt.setString(1, null);
			createStmt.setString(2, provider.getLName());
			createStmt.setString(3, provider.getFName());
			createStmt.setString(4, provider.getMName());
			createStmt.setString(5, provider.getCredentials());
			createStmt.setString(6, provider.getLicense());
			createStmt.setString(7, provider.getNPI());
			createStmt.setDate(8, provider.getDOB());
			int res = createStmt.executeUpdate();

			if(res==1) {
				PreparedStatement retrieveStmt = con.prepareStatement
						("SELECT provID, provLName, provFName, provCredentials from provider WHERE provLame=? AND provFName=? AND provCredentials=? AND provLicenseNum=? AND provNPI=?");
				retrieveStmt.setString(1, provider.getLName());
				retrieveStmt.setString(2, provider.getFName());
				retrieveStmt.setString(3, provider.getCredentials());
				retrieveStmt.setString(4, provider.getLicense());
				retrieveStmt.setString(5, provider.getNPI());
				ResultSet rs = retrieveStmt.executeQuery();

				while(rs.next()) {
					int id = rs.getInt("provID");
					String lName = rs.getString("provLName");
					String fName = rs.getString("provFName");
					String credentials = rs.getString("provCredentials");
					String license = rs.getString("provLicenseNum");
					String npi = rs.getString("provNPI");
					newProvider = new Provider(id, fName, lName, credentials, license, npi); } } } 
		catch(SQLException e) { e.printStackTrace(); }
		return newProvider;
	}
	
	
}







