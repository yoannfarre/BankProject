package bankproject;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bankproject.services.AbstractService;
import bankproject.services.SQLiteManager;
import bankproject.services.SrvAccount;
import bankproject.services.SrvCustomer;
import bankproject.services.SrvOperation;

public class Main {

	public static void main(String[] args) throws SQLException {

		ArrayList<AbstractService> services_list = new ArrayList<AbstractService>();

		SrvCustomer srvCustomer = SrvCustomer.getInstance();
		srvCustomer.setDbManager(SQLiteManager.getInstance());
		services_list.add(srvCustomer);

		SrvAccount srvAccount = SrvAccount.getInstance();
		srvAccount.setDbManager(SQLiteManager.getInstance());
		services_list.add(srvAccount);

		SrvOperation srvOperation = SrvOperation.getInstance();
		srvOperation.setDbManager(SQLiteManager.getInstance());
		services_list.add(srvOperation);

		Connection connection;

		for (AbstractService as : services_list) {

			connection = as.getDbManager().getConnection();
			Statement st = connection.createStatement();
			st.execute(as.createTableInDB());

		}


		
		

	}

}
