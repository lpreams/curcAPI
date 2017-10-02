package coordinator;
import java.util.ArrayList;

public class Coordinator {
	
	private final int coordinatorID;
	private String fName;
	private String lName;
	private int permissions;	
	private String credentials = null;
	private String licenseNum = null;
	
	public Coordinator(int coordinatorID, String fname, String lName, int permissions) {
		this.coordinatorID = coordinatorID;
		this.fName = fName;
		this.lName = lName;
		this.permissions = permissions;
	}
	
	private int getCoordinatorID() {
		return coordinatorID;
	}
	
	private String getName() {
		return fName + " " + lName;
	}
	
	private void setfName(String fName) {
		this.fName = fName;
	}
	
	private void setlName(String lName) {
		this.lName = lName;
	}
	
	private int getPermissions() {
		return permissions;
	}
	
	private int setPermissions(int level) {
		this.permissions = level;
		return permissions;
	}
	
	private void setCredentials(String credentials) {
		this.credentials = credentials;
	}
	
	private String getCredentials() {
		return credentials;
	}
	
	private void setLicenseNum() {
		this.licenseNum = licenseNum;
	}
	
	private String getLicenseNum() {
		
		if(licenseNum != null) {
			return licenseNum;
		} else { 
			return "No license on file!";
		}
	}

}
