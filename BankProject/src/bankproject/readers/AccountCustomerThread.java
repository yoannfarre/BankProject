package bankproject.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AccountCustomerThread extends Thread {

	int time = 60000 * 7; // 7 minutes

	public void run() {
		// 'fichier' repr�sente un fichier nomm� "unFichier.txt" situ�
		// dans le r�pertoire courant.
		File fichier = new File("unFichier.txt");
		if (fichier.exists()) {
			// Si ce fichier existe d�j�, on s'arr�te pour ne pas le modifier.
			// 'System.err' est le flux d'erreur standard, qui s'affiche
			// normalement
			// dans le terminal qui a servi � lancer l'ex�cution du programme.
			System.err.println("# Erreur : \"" + fichier.getName() + "\" existe d�j�.");
			System.exit(1);
		}

		// Pour lire un fichier texte ligne � ligne, on utilise un
		// 'BufferedReader'. Cette classe ajoute la notion de ligne � la classe
		// 'Reader' qui permet de lire des flux de caract�res.
		BufferedReader entree = null;
		try {
			// 'entree' permet de lire ligne � ligne dans un flux de caract�res
			// issu de notre fichier.
			entree = new BufferedReader(new FileReader(fichier));
		} catch (FileNotFoundException e) {
			// si le fichier n'est pas trouv�, on s'arr�te.
			System.err.println("# Erreur : impossible de lire \"" + fichier + "\".");
			System.exit(1);
		}

		// On utilise une boucle infinie pour lire le fichier, la condition de
		// sortie �tant d�tect�e dans la boucle.
		// Certains pr�f�rent �crire 'for (;;)' � la place de 'while (true)', ce
		// qui est strictement �quivalent.
		while (true) {
			String laLigne = null;
			try {
				// on lit une ligne du fichier
				laLigne = entree.readLine();
			} catch (IOException e) {
				// si cela se passe mal, on s'arr�te.
				System.err.println("# Erreur pendant la lecture de \"" + fichier + "\".");
				System.exit(1);
			}
			if (laLigne == null) {
				// si la fin du fichier est atteinte, on sort de la boucle
				break;
			}
			// On affiche la ligne sur la sortie standard, qui s'affiche
			// normalement dans le terminal qui a servi � lancer l'ex�cution du
			// programme.
			System.out.println(laLigne);
		}

		try {
			// On ferme le BufferedReader, ce qui ferme le FileReader associ�.
			// Cette op�ration peut lever une IOException qu'il faut donc
			// traiter.
			entree.close();
		} catch (IOException e) {
			System.err.println("# Erreur � la fermeture de \"" + fichier + "\".");
			System.exit(1);
		}

		// On tente de supprimer le fichier en utilisant la m�thode 'delete' de
		// la classe 'File'.
		if (!fichier.delete()) {
			// si cela �choue, il y a un probl�me...
			System.err.println("# Erreur : impossible de supprimer \"" + fichier.getName() + "\".");
			System.exit(1);
		}

	}
}
