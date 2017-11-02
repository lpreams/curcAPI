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
	
	private ProviderFacade() throws NamingException, SQLException {
		this.pao = DatabaseAccess.getInstance();
	}
	
	public static ProviderFacade getInstance() throws NamingException, SQLException {
		if(singleton==null) {
			singleton = new ProviderFacade();
		}
		
		return singleton;
	}
	
	public ArrayList<Provider> getProviders() throws SQLException, ClassNotFoundException {
	
		Connection con = pao.getConnection();
		Class.forName("org.h2.Driver");
		
		PreparedStatement stmt = con.prepareStatement("SELECT provID, provLName, provFName, provCredentials FROM provider");
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Provider> providerArray = new ArrayList<Provider>();
		
		int count = 0;
		while(rs.next()) {
			int prov_id = rs.getInt("provID");
			String lName = rs.getString("provLName");
			String fName = rs.getString("provFName");
			String credentials = rs.getString("provCredentials");
			Provider provider = new Provider(prov_id, lName, fName, credentials);
			providerArray.add(provider);
			count++;
		}
		
		if(count>0) {
			return providerArray;
		}
		else {
			return null;
		}
	}

	public ArrayList<Provider> getProviderByLName(String lastName) throws SQLException, ClassNotFoundException {
		
		Connection con = pao.getConnection();
		Class.forName("org.h2.Driver");
		
		PreparedStatement stmt = con.prepareStatement("SELECT provID, provLName, provFName, provCredentials, provLicenseNum, provNPI FROM provider WHERE lName=?");
		stmt.setString(1, lastName + "%");
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Provider> providerArray = new ArrayList<Provider>();
		int count = 0;
		while(rs.next()) {
			int prov_id = rs.getInt("provID");
			String lName = rs.getString("provLName");
			String fName = rs.getString("provFName");
			String credentials = rs.getString("provCredentials");
			String licenseNum = rs.getString("provLicenseNum");
			String npi = rs.getString("provNPI");
			Provider provider = new Provider(prov_id, lName, fName, credentials, licenseNum, npi);
			providerArray.add(provider);
			count++;
		}
		
		if(count>0) {
			return providerArray;
		}
		else {
			return null;
		}
		
	}
	
	public ArrayList<Provider> getProviderByID(int theID) throws SQLException, ClassNotFoundException {
		
		Connection con = pao.getConnection();
		Class.forName("org.h2.Driver");
		
		PreparedStatement stmt = con.prepareStatement("SELECT provID, provLName, provFName, provCredentials, provLicenseNum, provNPI FROM provider WHERE id=?");
		stmt.setInt(1, theID);
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Provider> providerArray = new ArrayList<Provider>();
		int count = 0;
		while(rs.next()) {
			int prov_id = rs.getInt("provID");
			String lName = rs.getString("provLName");
			String fName = rs.getString("provFName");
			String credentials = rs.getString("provCredentials");
			String licenseNum = rs.getString("provLicenseNum");
			String npi = rs.getString("provNPI");
			Provider provider = new Provider(prov_id, lName, fName, credentials, licenseNum, npi);
			providerArray.add(provider);
			count++;
		}
		
		if(count>0) {
			return providerArray;
		}
		else {
			return null;
		}
	}
	
	public ArrayList<Provider> createProvider(Provider provider) throws SQLException, ClassNotFoundException {
		
		Connection con = pao.getConnection();
		Class.forName("org.h2.Driver");
		
		String fName = provider.getFName();
		String lName = provider.getLName();
		String mName = provider.getMName();
		String credentials = provider.getCredentials();
		String licenseNum = provider.getLicense();
		String npi = provider.getNPI();
		Date dob = provider.getDOB();
		
		PreparedStatement createStmt = con.prepareStatement
				("INSERT INTO provider (provID, provLName, provFName, provMName, provCredentials, provLicenseNum, provNPI, provDOB) VALUES (?,?,?,?,?,?,?,?)");
		createStmt.setString(1, null);
		createStmt.setString(2, lName);
		createStmt.setString(3, fName);
		createStmt.setString(4, mName);
		createStmt.setString(5, credentials);
		createStmt.setString(6, licenseNum);
		createStmt.setString(7, npi);
		createStmt.setDate(8, (java.sql.Date) dob);
		
		int res = createStmt.executeUpdate();
				
		if(res==1) {
			PreparedStatement retrieveStmt = con.prepareStatement
					("SELECT provID, provLName, provFName, provCredentials from provider WHERE provLame=? AND provFName=? AND provCredentials=? AND provLicenseNum=? AND provNPI=?");
			retrieveStmt.setString(1, lName);
			retrieveStmt.setString(2, fName);
			retrieveStmt.setString(3, credentials);
			retrieveStmt.setString(4, licenseNum);
			retrieveStmt.setString(5, npi);
			ResultSet rs = retrieveStmt.executeQuery();
			
			ArrayList<Provider> providerArray = new ArrayList<Provider>();
			
			int count = 0;
			while(rs.next()) {
				int new_id = rs.getInt("provID");
				String new_lName = rs.getString("provLName");
				String new_fName = rs.getString("provFName");
				String new_credentials = rs.getString("provCredentials");
				String new_license = rs.getString("provLicenseNum");
				String new_npi = rs.getString("provNPI");
				Provider newProvider = new Provider(new_id, new_fName, new_lName, new_credentials, new_license, new_npi);
				providerArray.add(newProvider);	
				count++;
			}
			
			if(count>0) {
				return providerArray;
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}
	
}








