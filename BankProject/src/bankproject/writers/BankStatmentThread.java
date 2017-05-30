package bankproject.writers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BankStatmentThread extends Thread {

	int time = 60000 * 13; // 13 minutes en ms

	public void run() {

		PrintWriter output = null;
		File file = new File("test.txt");

		try {
			output = new PrintWriter(new FileWriter(file));
		} catch (IOException e) {
			System.err.println("# Erreur : impossible de créer \"" + file + "\".");
			System.exit(1);
		}

		for (int i = 0; i < 10; i++) {
			output.println(Integer.toString(i) + ": ");
		}

		output.close();
		if (output.checkError()) {
			System.err.println("# Erreur : le fichier \"" + file + "\" n'a pu être enregistré correctement.");
			System.exit(1);
		}

	}
}
