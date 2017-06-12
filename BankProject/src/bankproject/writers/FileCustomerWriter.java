package bankproject.writers;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;

import bankproject.entities.Customer;
import bankproject.entities.Statement;
import bankproject.services.SQLiteManager;
import bankproject.services.SrvCustomer;
import bankproject.services.SrvStatement;

public class FileCustomerWriter extends AbstractWriter {

	/********************************
	 ********** Attributes **********
	 ********************************/

	String firstname;
	String lastname;
	Boolean exist = false;

	/********************************
	 *********** Builders ***********
	 ********************************/

	public FileCustomerWriter() {

		scan();
		tryIfExist();
		if (exist) {
			output = null;
			file = new File(getFileOutputPath());
			createFile();
		}

	}

	/********************************
	 *********** Getters ************
	 ********************************/

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	/********************************
	 *********** Setters ************
	 ********************************/

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/********************************
	 *********** Methods ***********
	 ********************************/

	public void tryIfExist() {

		SrvCustomer srvcustomer = SrvCustomer.getInstance(SQLiteManager.getInstance());

		Customer customer = new Customer();

		customer.setFirstname(getFirstname());
		customer.setLastname(getLastname());

		exist = true;

		try {

			int id = srvcustomer.get(customer.getFirstname(), customer.getLastname()).getId();
			customer.setId(id);
		} catch (Exception e1) {
			exist = false;
			System.out.println("This customer is not registered!");
		}
	}

	public String getFileOutputPath() {

		String dirPath = getFileOutputPrimaryPath() + firstname + lastname + ".txt";
		File dir = new File(dirPath);
		if (dir.exists()) {
			dir.mkdirs();
			System.err.println("# Warning : \"" + dir.getName() + "\" was overridden.");
		}
		return dirPath;
	}

	protected void writeInFile() {

		SrvStatement srvStatement = SrvStatement.getInstance();
		srvStatement.setDbManager(SQLiteManager.getInstance());

		Collection<Statement> results = new HashSet<>();

		StringBuilder sb_sentence1 = new StringBuilder();
		sb_sentence1.append("Bank statements for " + firstname + " " + lastname);
		sb_sentence1.append(" were stored in the folder " + getFileOutputPrimaryPath());

		System.out.println(sb_sentence1.toString() + "\n");
		output.println(firstname + " " + lastname);

		try {
			results = srvStatement.requestStatementsCustomer(firstname, lastname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		output.println(writeFirstLine());
		
		for (Statement statement : results) {
			output.println(statement.toString());
		}
	}

	public void scan() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the name of a customer under the following form :");
		System.out.println("Firstname Lastname");
		String str = sc.nextLine();
		System.out.println("Full name of the customer : " + str);

		StringTokenizer st = new StringTokenizer(str, " ");
		int count = 0;

		while (st.hasMoreTokens()) {

			String field = st.nextToken();

			switch (count) {
			case 0:
				setFirstname(field);
				break;
			case 1:
				setLastname(field);
				break;
			}
			count++;
		}

	}

}