package coordinator;

public class Coordinator {
	
	private int cordID;
	private String name=null;
	private String type=null;	
	private String credentials = null;
	private String licenseNum = null;
	
	public Coordinator(String type, String name) {
		this.name = name;
		this.type = type;
	}
	
	public Coordinator(int cordID, String type, String name) {
		this.cordID = cordID;
		this.name = name;
		this.type = type;
	}
	
	private int getcordID() { return cordID; }
	
	String getName() { return name; }
	
	private void setName(String fName) { this.name = name; }
	
	String getType() { return type; }
	
	private void setTypes(String type) { this.type = type; }
	
	private void setCredentials(String credentials) { this.credentials = credentials; }
	
	String getCredentials() { return credentials; }
	
	private void setLicenseNum() { this.licenseNum = licenseNum; }
	
	String getLicenseNum() { return licenseNum; }

}
