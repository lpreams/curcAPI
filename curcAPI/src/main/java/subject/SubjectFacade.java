package subject;
import java.sql.Connection;
import database.DatabaseAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import javax.naming.NamingException;

public class SubjectFacade {
	
	private static SubjectFacade singleton;
	private DatabaseAccess sao;
	
	private SubjectFacade() throws NamingException, SQLException {
		this.sao = DatabaseAccess.getInstance();
	}
	
	private SubjectFacade getInstance() throws NamingException, SQLException {
		if(singleton==null) {
			singleton = new SubjectFacade();
		}
		return singleton;
	}

}
