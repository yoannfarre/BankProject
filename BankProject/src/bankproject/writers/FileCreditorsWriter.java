package bankproject.writers;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

import bankproject.entities.Statement;
import bankproject.enumerations.TypeOperationEnum;
import bankproject.services.SQLiteManager;
import bankproject.services.SrvStatement;

public class FileCreditorsWriter extends AbstractWriter {

	/********************************
	 *********** Builders ***********
	 ********************************/

	public FileCreditorsWriter() {

		output = null;
		file = new File(getFileOutputPath());
		createFile();
	}

	/********************************
	 *********** Methods ***********
	 ********************************/

	public String getFileOutputPath() {

		String dirPath = getFileOutputPrimaryPath() + "creditors.txt";
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

		output.println("Bank statements for creditors");

		// Operation.date, Operation.amount, Operation.type_operation,
		// Account.country, Account.number,
		// Account.summary, Customer.firstname, Customer.lastname

		TypeOperationEnum type = TypeOperationEnum.CREDIT;

		try {
			results = srvStatement.requestStatementsCreditors(type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		output.println(writeFirstLine());
		for (Statement statement : results) {
			output.println(statement.toString());
		}

	}
}
