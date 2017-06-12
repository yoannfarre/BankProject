package bankproject.readers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.StringTokenizer;

import bankproject.entities.Account;
import bankproject.entities.Customer;
import bankproject.entities.Operation;
import bankproject.enumerations.CountryEnum;
import bankproject.enumerations.TypeOperationEnum;
import bankproject.exceptions.SrvException;
import bankproject.services.SQLiteManager;
import bankproject.services.SrvAccount;
import bankproject.services.SrvCustomer;
import bankproject.services.SrvOperation;

public class OperationReader extends AbstractReader {

	/********************************
	 ********** Builders ************
	 ********************************/

	public OperationReader() throws Exception {

		this.input = null;
		this.file = new File(getFileInputPath());
		readFile();

	}

	/********************************
	 ********** Methods *************
	 ********************************/

	public String getFileInputPath() {

		String dirPath = getFileInputPrimaryPath() + "operation.txt";
		// System.out.println(dirPath);
		//
		// File dir = new File(dirPath);
		//
		// System.out.println(dir.getName());
		//
		// if (!(dir.exists() && dir.isDirectory())) {
		// dir.mkdirs();
		// System.err.println("# Error : \"" + dir.getName() + "\" n'existe
		// pas.");
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
				Double amount = line_scanner.nextDouble();
				String number = line_scanner.next().trim();
				String firstname = line_scanner.next();
				String lastname = line_scanner.next();
				line_scanner.close();

				saveOperation(amount, number, firstname, lastname);
			}
			i++;
		}

		sc.close();

	}

	private void saveOperation(Double amount, String number, String firstname, String lastname) throws Exception {

		Customer customer = SrvCustomer.getInstance(SQLiteManager.getInstance()).get(firstname, lastname);
		Account account = SrvAccount.getInstance(SQLiteManager.getInstance()).getByNumber(number);

		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		try {

			if ((account.getCustomer_id() == customer.getId())) {

				account.updateSummary(amount);

				Operation operation = new Operation();

				operation.setDate(ft.format(date));
				operation.setCustomer_id(customer.getId());
				operation.setType_operation(amount > 0 ? TypeOperationEnum.CREDIT : TypeOperationEnum.DEBIT);
				operation.setAccount_id(account.getId());
				operation.setAmount(amount);

				SrvOperation srvoperation = SrvOperation.getInstance(SQLiteManager.getInstance());
				SrvOperation.getInstance(SQLiteManager.getInstance()).save(operation);

				SrvAccount srvaccount = SrvAccount.getInstance(SQLiteManager.getInstance());
				SrvAccount.getInstance(SQLiteManager.getInstance()).save(account);

			} else {
				System.out.println("The account doesn't match the good customer");
			}

		} catch (Exception e) {
			System.out.println("The account " + number + " doesn't exist.");
		}

	}

}
