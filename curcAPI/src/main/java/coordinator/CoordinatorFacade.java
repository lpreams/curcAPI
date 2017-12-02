package coordinator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import javax.naming.NamingException;

import database.DatabaseAccess;
import coordinator.Coordinator;

public class CoordinatorFacade {

	private static CoordinatorFacade singleton;
	private DatabaseAccess cao;
	
	
	public CoordinatorFacade() {
		try {
			this.cao = DatabaseAccess.getInstance();
		}catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static CoordinatorFacade getInstance() {
		if(singleton==null) {
			singleton=new CoordinatorFacade();
		}
		return singleton;
	}
	
	public ArrayList<Coordinator> getCoordinators() {	
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Coordinator> CoordinatorArray = new ArrayList<Coordinator>();
		
		try{
			Connection con = cao.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT coordID, coordType, coordName FROM coordinator");
			ResultSet rs = stmt.executeQuery();
		
			while(rs.next()) {
				int coordID = rs.getInt("coordID");
				String type = rs.getString("coordType");
				String name = rs.getString("coordName");
				Coordinator Coordinator = new Coordinator(coordID, type, name);
				CoordinatorArray.add(Coordinator);
			}
			con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return CoordinatorArray;
	}
	
	public Coordinator getCoordinatorByID(int id) {
		try{
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Coordinator coordinator = null;
		
		try{
			Connection con = cao.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM coordinator WHERE coordID=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int coordID = rs.getInt("coordID");
				String type = rs.getString("coordType");
				String name = rs.getString("coordName");
				coordinator = new Coordinator(coordID, type, name);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return coordinator;
	}
	
	public Coordinator createCoordinator(Coordinator coordinator) {
		try{
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String type = coordinator.getType();
		String name = coordinator.getName();
		
		Connection con = null;
		int res = 0;
		Coordinator coord = null;
		
		try{
			con = cao.getConnection();
			PreparedStatement createStmt = con.prepareStatement("INSERT into coordinator (coordType, coordName) VALUES (?,?)");
			createStmt.setString(1, type);
			createStmt.setString(2, name);
			res = createStmt.executeUpdate();
			
			if(res==1) {
				PreparedStatement retrieveStmt = con.prepareStatement("SELECT * from coordinator WHERE coordType=? AND coordName=?");
				retrieveStmt.setString(1, type);
				retrieveStmt.setString(2, name);
				ResultSet rs = retrieveStmt.executeQuery();
			
				while(rs.next()) {
					int coordID = rs.getInt("coordID");
					String cType = rs.getString("coordType");
					String cName = rs.getString("coordName");
					coord = new Coordinator(coordID, cType, cName);
				}
				con.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return coord;		
	}


}
