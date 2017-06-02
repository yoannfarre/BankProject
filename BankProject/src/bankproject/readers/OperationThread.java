package bankproject.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class OperationThread extends AbstractReaderThread {

	int time = 60000 * 11; // 11 minutes

	public static void main(String[] args) {

		OperationThread act = new OperationThread("B");
	}

	public OperationThread(String name) {
		super(name);
		System.out.println("statut du thread " + name + " = " + this.getState());
		this.start();
		System.out.println("statut du thread " + name + " = " + this.getState());
	}

	public void run() {
		while (true) { // TODO Ajouter une condition de sortie

			File file_account_customer = new File(getFileBPath());
			if (!file_account_customer.exists()) {
				System.err.println("# Error : \"" + file_account_customer.getName() + "\" n'existe pas.");
				System.exit(1);
			}

			BufferedReader input = null;
			try {
				input = new BufferedReader(new FileReader(file_account_customer));
			} catch (FileNotFoundException e) {
				System.err.println("# Error : impossible to read \"" + file_account_customer + "\".");
				System.exit(1);
			}

			while (true) {
				String ligne = null;
				try {
					ligne = input.readLine();
				} catch (IOException e) {
					System.err.println("# Erreur pendant la lecture de \"" + file_account_customer + "\".");
					System.exit(1);
				}
				if (ligne == null) {
					// condition de sortie de boucle infinie
					break;
				}
				System.out.println(ligne);
			}

			try {
				input.close();
			} catch (IOException e) {
				System.err.println("# Error on closing \"" + file_account_customer + "\".");
				System.exit(1);
			}
			
			

			// Suppression du fichier
			if (!file_account_customer.delete()) {
				System.err.println("# Error on deleting \"" + file_account_customer.getName() + "\".");
				System.exit(1);
			}

			try {
				Thread.sleep(time);
			} catch (InterruptedException ie) {
				continue; // Recommencer en début de boucle
			}
		}

	}

	public static String getFileBPath() {
		String fs = System.getProperty("file.separator");
		String dirPath = System.getProperty("user.dir") + fs + "tmp" + fs + "bank" + fs + "input";
		File dir = new File(dirPath);
		if (!(dir.exists() && dir.isDirectory())) {
			dir.mkdirs();
			System.err.println("# Error : \"" + dir.getName() + "\" n'existe pas.");
		}

		return dirPath + fs + "operation.txt";
	}
}
