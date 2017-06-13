package bankproject;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import bankproject.services.AbstractService;
import bankproject.services.SQLiteManager;
import bankproject.services.SrvAccount;
import bankproject.services.SrvCustomer;
import bankproject.services.SrvOperation;

public class InitDB {

	public static void main(String[] args) throws Exception {
		
		InitDB ini = new InitDB();

		ini.deleteTables();
		
	}

	public void deleteTables() throws Exception {

		ArrayList<AbstractService> services_list = new ArrayList<AbstractService>();

		SrvCustomer srvcustomer = SrvCustomer.getInstance(SQLiteManager.getInstance());
		services_list.add(srvcustomer);

		SrvAccount srvaccount = SrvAccount.getInstance(SQLiteManager.getInstance());
		services_list.add(srvaccount);

		SrvOperation srvOperation = SrvOperation.getInstance(SQLiteManager.getInstance());
		services_list.add(srvOperation);

		Connection connection;

		for (AbstractService as : services_list) {

			connection = as.getDbManager().getConnection();
			Statement st = connection.createStatement();
			st.execute(as.dropTableInDB());

		}
	}
		
	
		
		
		
	

	public InitDB() throws Exception {

		init();

	}

	public void init() throws Exception {

		ArrayList<AbstractService> services_list = new ArrayList<AbstractService>();

		SrvCustomer srvcustomer = SrvCustomer.getInstance(SQLiteManager.getInstance());
		services_list.add(srvcustomer);

		SrvAccount srvaccount = SrvAccount.getInstance(SQLiteManager.getInstance());
		services_list.add(srvaccount);

		SrvOperation srvOperation = SrvOperation.getInstance(SQLiteManager.getInstance());
		services_list.add(srvOperation);

		Connection connection;

		for (AbstractService as : services_list) {

			connection = as.getDbManager().getConnection();
			Statement st = connection.createStatement();
			st.execute(as.createTableInDB());

		}
	}

}
