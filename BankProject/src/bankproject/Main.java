package bankproject;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import bankproject.readers.AccountCustomerThread;
import bankproject.readers.OperationThread;
import bankproject.services.AbstractService;
import bankproject.services.SQLiteManager;
import bankproject.services.SrvAccount;
import bankproject.services.SrvCustomer;
import bankproject.services.SrvOperation;
import bankproject.writers.BankStatmentThread;
import bankproject.writers.CustomerStatementThread;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		// Construction de la BDD si nécessaire

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
		
		// Lancement des Threads
		
		AccountCustomerThread act = new AccountCustomerThread("A");
		
		OperationThread ot = new OperationThread("B");
		
		BankStatmentThread bst = new BankStatmentThread("C");

		CustomerStatementThread cst = new CustomerStatementThread("D");
		
		

	}

}
