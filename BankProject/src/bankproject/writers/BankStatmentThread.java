package bankproject.writers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;

public class BankStatmentThread extends Thread {

	int time = 60000 * 13; // 13 minutes en ms

	public static void main(String[] args) {

		BankStatmentThread act = new BankStatmentThread("A");
	}

	public BankStatmentThread(String name) {
		super(name);
		System.out.println("statut du thread " + name + " = " + this.getState());
		this.start();
		System.out.println("statut du thread " + name + " = " + this.getState());
	}

	public void run() {

		while (true) { // TODO Ajouter une condition de sortie

			PrintWriter output = null;

			HashSet<File> file_set = new HashSet<File>();

			File file_creditors = new File(getFileOutputPath("creditors.txt"));
			file_set.add(file_creditors);
			File file_debitors = new File(getFileOutputPath("debitors.txt"));
			file_set.add(file_debitors);
			File file_country = new File(getFileOutputPath("country.txt"));
			file_set.add(file_country);

			Iterator<File> it = file_set.iterator();

			while (it.hasNext()) {

				try {
					output = new PrintWriter(new FileWriter(it.next()));
				} catch (IOException e) {
					System.err.println("# Error on creating \"" + it.next() + "\".");
					System.exit(1);
				}

				for (int i = 0; i < 10; i++) {
					output.println(Integer.toString(i) + ": ");
				}

				output.close();
				if (output.checkError()) {
					System.err.println("# Error : file \"" + it.next() + "\" incorreclty registered.");
					System.exit(1);
				}

			}

			try {
				Thread.sleep(time);
			} catch (InterruptedException ie) {
				continue; // Recommencer en début de boucle
			}
		}
	}

	public static String getFileOutputPath(String fileName) {
		String fs = System.getProperty("file.separator");
		String dirPath = System.getProperty("user.dir") + fs + "tmp" + fs + "bank" + fs + "output" + fs + fileName;
		File dir = new File(dirPath);
		if (dir.exists()) {
			dir.mkdirs();
			System.err.println("# Warning : \"" + dir.getName() + "\" was overridden.");
		}

		return dirPath;
	}
}
