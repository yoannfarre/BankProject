package bankproject.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class OperationThread extends Thread {

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

			OperationReader or = new OperationReader();

			try {
				Thread.sleep(time);
			} catch (InterruptedException ie) {
				continue; // Recommencer en début de boucle
			}
		}

	}
}
