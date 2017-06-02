package bankproject.writers;

import java.io.File;

public class FileDebitorsWriter extends AbstractWriter {

	public FileDebitorsWriter() {

		super();
	}

	public String getFileOutputPath() {

		String dirPath = getFileOutputPrimaryPath() + "debitors.txt";
		File dir = new File(dirPath);
		if (dir.exists()) {
			dir.mkdirs();
			System.err.println("# Warning : \"" + dir.getName() + "\" was overridden.");
		}
		return dirPath;
	}

	protected void writeInFile() {

		for (int i = 0; i < 10; i++) {
			output.println(Integer.toString(i) + ": ");

		}
	}

}
