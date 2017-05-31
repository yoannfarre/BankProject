package bankproject.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.StringTokenizer;

import bankproject.entities.Account;
import bankproject.entities.Customer;
import bankproject.exceptions.SrvException;
import bankproject.services.SQLiteManager;
import bankproject.services.SrvCustomer;

public class AccountCustomerThread extends Thread {

	int time = 60000 * 7; // 7 minutes

	public static void main(String[] args) {

		AccountCustomerThread act = new AccountCustomerThread("A");
	}

	public AccountCustomerThread(String name) {
		super(name);
		System.out.println("statut du thread " + name + " = " + this.getState());
		this.start();
		System.out.println("statut du thread " + name + " = " + this.getState());
	}

	public void run() {

		while (true) { // TODO Ajouter une condition de sortie

			File file_account_customer = new File(getFileAPath());
			HashSet<Customer> customer_set = new HashSet<Customer>();
			HashSet<Account> account_set = new HashSet<Account>();
			
			if (!file_account_customer.exists()) {
				System.err.println("# Error : \"" + file_account_customer.getName() + "\" n'existe pas.");
				System.exit(1);
			}

			BufferedReader input = null;
			try {
				input = new BufferedReader(new FileReader(file_account_customer));
			} catch (FileNotFoundException e) {
				System.err.println("# Error : impossible to read \"" + file_account_customer + "\".");
				System.exit(1);
			}

			while (true) {
				String ligne = null;
				try {
					ligne = input.readLine();
				} catch (IOException e) {
					System.err.println("# Erreur pendant la lecture de \"" + file_account_customer + "\".");
					System.exit(1);
				}
				if (ligne == null) {
					// condition de sortie de boucle infinie
					break;
				}

				// Interprétation des infos de chaque ligne du fichier

				StringTokenizer st = new StringTokenizer(ligne, "\t\t\t");
				Customer customer = new Customer();
				Account account = new Account();
				int count = 0;

				while (st.hasMoreTokens()) {

					String field = st.nextToken();

					// Saute la première ligne
					if (field.equals("Pays")) {
						break;
					} else {

						switch (count) {
						case 0:
							account.setCountry(field.toUpperCase());
							break;
						case 1:
							customer.setLastname(field);
							break;
						case 2:
							customer.setFirstname(field);
							break;
						case 3:
							account.setSummary(Double.parseDouble(field));
							break;

						}

					}

					count++;
				}
				
				customer_set.add(customer);
				account_set.add(account);
//				System.out.println(account.getSummary());
//				System.out.println(account.getCountry());
//				
//				System.out.println(customer.getFullName());
//				
//				SrvCustomer srvCustomer = SrvCustomer.getInstance();
//				srvCustomer.setDbManager(SQLiteManager.getInstance());
//				
////				Connection connection = srvCustomer.getDbManager().getConnection();
//				
//				try {
//					srvCustomer.save(customer);
//				} catch (SrvException | SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
////				SrvAccount srvAccount = SrvAccount.getInstance();
////				srvAccount.setDbManager(SQLiteManager.getInstance());
				

			}

			try {
				input.close();
			} catch (IOException e) {
				System.err.println("# Error on closing \"" + file_account_customer + "\".");
				System.exit(1);
			}
			
			SrvCustomer srvCustomer = SrvCustomer.getInstance();
			srvCustomer.setDbManager(SQLiteManager.getInstance());
			
//			Connection connection = srvCustomer.getDbManager().getConnection();
			
			for (Customer customer : customer_set){
				

					try {
						srvCustomer.save(customer);
					} catch (SrvException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}
			


			// Suppression du fichier
			// TODO Réactiver la suppression quand les tests seront terminés
			// if (!file_account_customer.delete()) {
			// System.err.println("# Error on deleting \"" +
			// file_account_customer.getName() + "\".");
			// System.exit(1);
			// }
			


			try {
				Thread.sleep(time);
			} catch (InterruptedException ie) {
				continue; // Recommencer en début de boucle
			}
		}

	}

	public static String getFileAPath() {
		String fs = System.getProperty("file.separator");
		String dirPath = System.getProperty("user.dir") + fs + "tmp" + fs + "bank" + fs + "input";
		File dir = new File(dirPath);
		if (!(dir.exists() && dir.isDirectory())) {
			dir.mkdirs();
			System.err.println("# Error : \"" + dir.getName() + "\" n'existe pas.");
		}

		return dirPath + fs + "account_customer.txt";
	}
}
