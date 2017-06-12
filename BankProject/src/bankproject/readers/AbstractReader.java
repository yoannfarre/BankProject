package bankproject.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class AbstractReader extends Thread {
	
	/********************************
	 ********** Attributes **********
	 ********************************/

	protected File file;
	protected BufferedReader input = null;
	protected Boolean fileexist = false;
	
	/********************************
	 ********** Builders ************
	 ********************************/

	public AbstractReader() {

	}
	
	/********************************
	 ********** Methods *************
	 ********************************/

	public String getFileInputPath() {
		return null;
	}

	public String getFileInputPrimaryPath() {

		String fs = System.getProperty("file.separator");
		String dirPath = System.getProperty("user.dir") + fs + "tmp" + fs + "bank" + fs + "input" + fs;
		return dirPath;

	}

	protected void readFile() throws Exception {

		if (!file.exists()) {
			System.err.println("# Error : \"" + getFileInputPath() + "\" n'existe pas.");
			fileexist = false;
			// System.exit(1);
		} else {
			fileexist = true;
		}

		if (fileexist) {

			try {
				input = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e) {
				System.err.println("# Error : impossible to read \"" + file + "\".");
				// System.exit(1);
			}

			readSpecificFile();

			try {
				input.close();
			} catch (IOException e) {
				System.err.println("# Error on closing \"" + file + "\".");
				System.exit(1);
			}

			deleteFile(file);
		}

	}

	public void readSpecificFile() throws Exception {

	}

	public void deleteFile(File file) {

		if (!file.delete()) {
			System.err.println("# Error on deleting \"" + file.getName() + "\".");
			System.exit(1);
		}
	}

}
