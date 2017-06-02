package bankproject.readers;

import java.io.File;

public class OperationReader extends AbstractReader {
	
	public OperationReader() {

		this.file = new File(getFileInputPath());

	}

	public String getFileInputPath() {

		String dirPath = getFileInputPrimaryPath() + "operation.txt";
		File dir = new File(dirPath);
		if (!(dir.exists() && dir.isDirectory())) {
			dir.mkdirs();
			System.err.println("# Error : \"" + dir.getName() + "\" n'existe pas.");
		}

		return dirPath;

	}
}
