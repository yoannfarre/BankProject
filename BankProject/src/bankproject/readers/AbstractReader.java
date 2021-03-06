package bankproject.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class AbstractReader extends Thread {

	/********************************
	 ********** Attributes **********
	 ********************************/

	protected File file;
	protected BufferedReader input = null;

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
		String os = System.getProperty("os.name");
		String dirPath;
		if (os.substring(0, 7).toLowerCase().equals("windows")) {
			dirPath = "C:" + fs + "tmp" + fs + "bank" + fs + "input" + fs;
		} else {
			dirPath = fs + "tmp" + fs + "bank" + fs + "input" + fs;
		}
		return dirPath;

	}

	protected void readFile() throws Exception {

		if (file.exists()) {

			try {
				input = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e) {

			}

			readSpecificFile();

			try {
				input.close();
			} catch (IOException e) {
				System.err.println("# Error on closing \"" + file + "\".");
				System.exit(1);
			}

			deleteFile(file);

		} else {
			Date date = new Date();
			String sDate = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.FRANCE).format(date);
			System.out.println(sDate + " : No File " + file.getPath() + " to read");
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
