package bankproject;

import bankproject.readers.AccountCustomerThread;
import bankproject.readers.OperationThread;
import bankproject.writers.BankStatementThread;
import bankproject.writers.CustomerStatementThread;

public class Main {

	public static void main(String[] args) throws Exception {

		// Construction de la BDD si nécessaire

		InitDB init = new InitDB();

		// Lancement des Threads

		AccountCustomerThread act = new AccountCustomerThread("A");

		OperationThread ot = new OperationThread("B");

		BankStatementThread bst = new BankStatementThread("C");

		CustomerStatementThread cst = new CustomerStatementThread("D");

	}

}
