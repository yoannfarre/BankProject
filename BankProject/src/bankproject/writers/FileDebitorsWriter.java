package bankproject.writers;

import java.io.File;
import java.util.Collection;
import java.util.LinkedHashSet;

import bankproject.entities.Statement;
import bankproject.enumerations.TypeOperationEnum;
import bankproject.services.SQLiteManager;
import bankproject.services.SrvStatement;

public class FileDebitorsWriter extends AbstractWriter {

	/********************************
	 *********** Builders ***********
	 ********************************/

	public FileDebitorsWriter() {

		output = null;
		file = new File(getFileOutputPath());
		createFile();
	}

	/********************************
	 *********** Methods ***********
	 ********************************/

	public String getFileOutputPath() {

		String dirPath = getFileOutputPrimaryPath() + "debitors.txt";
		File dir = new File(dirPath);
		if (dir.exists()) {
			dir.mkdirs();
			System.out.println("# News : \"" + dir.getName() + "\" was overridden.");
		}
		return dirPath;
	}

	protected void writeInFile() {

		SrvStatement srvStatement = SrvStatement.getInstance();
		srvStatement.setDbManager(SQLiteManager.getInstance());

		Collection<Statement> results = new LinkedHashSet<>();

		output.println("Bank statements for debitors");

		TypeOperationEnum type = TypeOperationEnum.DEBIT;

		try {
			results = srvStatement.requestStatementsByType(type);
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
