package database;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.h2.jdbcx.JdbcConnectionPool;

public class DatabaseAccess {

	private static DatabaseAccess singleton;
//	private DataSource datasource;
	private JdbcConnectionPool cp;
	
	private DatabaseAccess() throws NamingException, SQLException {
		
//		Context initContext = new InitialContext();
//		Context envContext = (Context)initContext.lookup("java:/comp/env");
//		this.datasource = (DataSource)envContext.lookup("jdbc/clinic");	
		cp = JdbcConnectionPool.create("jdbc:h2:C:/Users/Alex/git/curcAPI", "sa", "");
		
	}
	
	public static DatabaseAccess getInstance() throws NamingException, SQLException {
		
		if(singleton == null) {
			singleton = new DatabaseAccess();
		}
		return singleton;
	}
	
	public Connection getConnection() throws SQLException {
		return cp.getConnection();
//		return datasource.getConnection();
	}
	
}
