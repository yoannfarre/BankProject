package bankproject.writers;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

import bankproject.entities.Statement;
import bankproject.enumerations.CountryEnum;
import bankproject.services.SQLiteManager;
import bankproject.services.SrvStatement;

public class FileCountryWriter extends AbstractWriter {

	public FileCountryWriter() {

		super();
	}

	public String getFileOutputPath() {

		String dirPath = getFileOutputPrimaryPath() + "country.txt";
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

		output.println("Bank statements ordered by country");

		// Operation.date, Operation.amount, Operation.type_operation,
		// Account.country, Account.number,
		// Account.summary, Customer.firstname, Customer.lastname

		for (CountryEnum country : CountryEnum.values()) {

			try {
				results = srvStatement.requestStatementsByCountry(country);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			output.println(country.toString());
			output.println(writeFirstLine());
			for (Statement statement : results) {
				output.println(statement.toString());
			}

		}
	}

}
