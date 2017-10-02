package database;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseAccess {

	private static DatabaseAccess singleton;
	private DataSource datasource;
	
	private DatabaseAccess() throws NamingException, SQLException {
		
		Context initContext = new InitialContext();
		Context envContext = (Context)initContext.lookup("java:/comp/env");
		this.datasource = (DataSource)envContext.lookup("jdbc/clinic");
	}
	
	public static DatabaseAccess getInstance() throws NamingException, SQLException {
		
		if(singleton == null) {
			singleton = new DatabaseAccess();
		}
		return singleton;
	}
	
	public Connection getConnection() throws SQLException {
		return datasource.getConnection();
	}
	
}
