package bankproject;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import bankproject.services.SQLiteManager;
import bankproject.services.SrvAccount;
import bankproject.services.SrvCustomer;

public class Main {

	public static void main(String[] args) throws SQLException {
		SrvCustomer srvCustomer = SrvCustomer.getInstance();
		srvCustomer.setDbManager(SQLiteManager.getInstance());
		Connection connection = srvCustomer.getDbManager().getConnection();
		Statement st = connection.createStatement();
		st.execute(srvCustomer.createTableInDB());
		
//		SrvAccount srvAccount = SrvAccount.getInstance();
//		srvAccount.setDbManager(SQLiteManager.getInstance());
//		Connection connection = srvAccount.getDbManager().getConnection();
//		Statement st = connection.createStatement();
//		st.execute(srvAccount.createTableInDB());
	}

}
