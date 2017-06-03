package bankproject.writers;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bankproject.entities.Customer;
import bankproject.services.SQLiteManager;
import bankproject.services.SrvAccount;
import bankproject.services.SrvCustomer;
import bankproject.services.SrvOperation;

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
		
		
		
// Test
		for (int i = 0; i < 10; i++) {
			output.println(Integer.toString(i) + ": ");

		}
		
//		SrvCustomer srvCustomer = SrvCustomer.getInstance();
//		srvCustomer.setDbManager(SQLiteManager.getInstance());
//
//		SrvAccount srvAccount = SrvAccount.getInstance();
//		srvAccount.setDbManager(SQLiteManager.getInstance());
//
//		SrvOperation srvOperation = SrvOperation.getInstance();
//		srvOperation.setDbManager(SQLiteManager.getInstance());
		
		

	}
	
	
}
