package bankproject.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class AbstractReader extends Thread {
	
	File file;
	BufferedReader input = null;

	public AbstractReader() {
		
		
		this.input = null;
		this.file = new File(getFileInputPath());
		this.readFile();

	}

	public String getFileInputPath() {
		return null;
	}
	
	
	public  String getFileInputPrimaryPath() {
		
		String fs = System.getProperty("file.separator");
		String dirPath = System.getProperty("user.dir") + fs + "tmp" + fs + "bank" + fs + "input" + fs;
		return dirPath;
		
	}
	
	
	protected void readFile() {
		
		if (!file.exists()) {
			System.err.println("# Error : \"" + getFileInputPath() + "\" n'existe pas.");
			System.exit(1);
		}
		
		try {
			input = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.err.println("# Error : impossible to read \"" + file + "\".");
			System.exit(1);
		}
		
		readSpecificFile();
		
		try {
			input.close();
		} catch (IOException e) {
			System.err.println("# Error on closing \"" + file + "\".");
			System.exit(1);
		}
		
		// Suppression du fichier
		// TODO Réactiver la suppression quand les tests seront terminés

		// deleteFile(file);

	}

	public void readSpecificFile() {
		
	}
	
	public void deleteFile(File file) {

		if (!file.delete()) {
			System.err.println("# Error on deleting \"" + file.getName() + "\".");
			System.exit(1);
		}
	}
	
	
	
	
	
	
	
	
	





}
