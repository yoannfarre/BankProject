package bankproject.readers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.StringTokenizer;

import bankproject.entities.Account;
import bankproject.entities.Customer;
import bankproject.enumerations.CountryEnum;
import bankproject.exceptions.SrvException;
import bankproject.services.SQLiteManager;
import bankproject.services.SrvAccount;
import bankproject.services.SrvCustomer;

public class AccountCustomerReader extends AbstractReader { // TODO Corriger
															// Erreur sur la
															// méthode
															// getFileInputPath()

	/********************************
	 ********** Builders ************
	 ********************************/

	public AccountCustomerReader() throws Exception {

		this.input = null;
		this.file = new File(getFileInputPath());
		readFile();

	}

	/********************************
	 ********** Methods ************
	 ********************************/

	public String getFileInputPath() {

		String dirPath = getFileInputPrimaryPath() + "account_customer.txt";
		// System.out.println(dirPath);

		// File dir = new File(dirPath);
		//
		// System.out.println(dir.getName());
		//
		// if (!(dir.exists() && dir.isDirectory())) {
		// dir.mkdirs();
		// System.err.println("# Error : \"" + dir.getName() + "\" n'existe pas.
		// Test");
		// }

		return dirPath;

	}

	public void readSpecificFile() throws Exception {

		Scanner sc = new Scanner(file);
		int i = 0;

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (i > 0) {
				Scanner line_scanner = new Scanner(line);
				CountryEnum country = CountryEnum.getByLongName(line_scanner.next().trim());
				String lastName = line_scanner.next().trim();
				String firstName = line_scanner.next().trim();
				Integer amount = line_scanner.nextInt();
				line_scanner.close();

				saveCustomer(firstName, lastName);

				SrvCustomer srvcustomer = SrvCustomer.getInstance(SQLiteManager.getInstance());
				Customer customer = srvcustomer.get(firstName, lastName);

				saveAccount(customer, country, amount);
			}
			i++;
		}

	}

	private void saveCustomer(String firstName, String lastName) throws Exception {
		SrvCustomer srvcustomer = SrvCustomer.getInstance(SQLiteManager.getInstance());
		Customer customer = srvcustomer.get(firstName, lastName);
		srvcustomer.save(customer);
	}

	private void saveAccount(Customer customer, CountryEnum country, Integer amount) throws Exception {
		Account account = new Account();
		account.setCustomer_id(customer.getId());
		account.setCountry(country);
		account.buildNumber(country);

		if (amount > 0) {
			account.setSummary(new Double(amount));
		} else {
			throw new Exception("A new account can't have a negative balance");
		}

		SrvAccount srvaccount = SrvAccount.getInstance(SQLiteManager.getInstance());
		SrvAccount.getInstance(SQLiteManager.getInstance()).save(account);

	}

}
