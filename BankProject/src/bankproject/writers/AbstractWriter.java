package bankproject.writers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class AbstractWriter {

	PrintWriter output = null;
	File file;

	public AbstractWriter() {

		this.output = null;
		this.file = new File(getFileOutputPath());
		this.createFile();

	}

	public void createFile() {

		try {
			output = new PrintWriter(new FileWriter(file));
		} catch (IOException e) {
			System.err.println("# Error on creating \"" + getFileOutputPath() + "\".");
			System.exit(1);
		}

		writeInFile();

		output.close();
		if (output.checkError()) {
			System.err.println("# Error : file \"" + getFileOutputPath() + "\" incorreclty registered.");
			System.exit(1);
		}

	}

	protected void writeInFile() {

	}

	public String getFileOutputPath() {
		return null;
	}
	
	public String getFileOutputPrimaryPath() {
		
		String fs = System.getProperty("file.separator");
		String dirPath = System.getProperty("user.dir") + fs + "tmp" + fs + "bank" + fs + "output" + fs;
		return dirPath;
		
	}
	
	public String writeFirstLine(){
		
		StringBuilder firstline = new StringBuilder();
		firstline.append("Date				");
		firstline.append("Amount		");
		firstline.append("Type_operation		");
		firstline.append("Country		");
		firstline.append("Number		");
		firstline.append("Summary	");
		firstline.append("FirstName	");
		firstline.append("LastName");
		
		return firstline.toString();
	}
}
