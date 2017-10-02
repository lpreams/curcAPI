package visit;

public class Visit {
	
	private static int visitID;
	private String name;
	private double cost;
	private boolean isInvoiced;
	
	public Visit(int visitID, String name, double cost, boolean isInvoiced) {
		
		this.visitID = visitID;
		this.name = name;
		this.cost = cost;
		this.isInvoiced = isInvoiced;
	}
	
}
