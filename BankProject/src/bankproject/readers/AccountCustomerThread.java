package bankproject.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AccountCustomerThread extends Thread {

	int time = 60000 * 7; // 7 minutes

	public void run() {
		// 'fichier' représente un fichier nommé "unFichier.txt" situé
		// dans le répertoire courant.
		File fichier = new File("unFichier.txt");
		if (fichier.exists()) {
			// Si ce fichier existe déjà, on s'arrête pour ne pas le modifier.
			// 'System.err' est le flux d'erreur standard, qui s'affiche
			// normalement
			// dans le terminal qui a servi à lancer l'exécution du programme.
			System.err.println("# Erreur : \"" + fichier.getName() + "\" existe déjà.");
			System.exit(1);
		}

		// Pour lire un fichier texte ligne à ligne, on utilise un
		// 'BufferedReader'. Cette classe ajoute la notion de ligne à la classe
		// 'Reader' qui permet de lire des flux de caractères.
		BufferedReader entree = null;
		try {
			// 'entree' permet de lire ligne à ligne dans un flux de caractères
			// issu de notre fichier.
			entree = new BufferedReader(new FileReader(fichier));
		} catch (FileNotFoundException e) {
			// si le fichier n'est pas trouvé, on s'arrête.
			System.err.println("# Erreur : impossible de lire \"" + fichier + "\".");
			System.exit(1);
		}

		// On utilise une boucle infinie pour lire le fichier, la condition de
		// sortie étant détectée dans la boucle.
		// Certains préfèrent écrire 'for (;;)' à la place de 'while (true)', ce
		// qui est strictement équivalent.
		while (true) {
			String laLigne = null;
			try {
				// on lit une ligne du fichier
				laLigne = entree.readLine();
			} catch (IOException e) {
				// si cela se passe mal, on s'arrête.
				System.err.println("# Erreur pendant la lecture de \"" + fichier + "\".");
				System.exit(1);
			}
			if (laLigne == null) {
				// si la fin du fichier est atteinte, on sort de la boucle
				break;
			}
			// On affiche la ligne sur la sortie standard, qui s'affiche
			// normalement dans le terminal qui a servi à lancer l'exécution du
			// programme.
			System.out.println(laLigne);
		}

		try {
			// On ferme le BufferedReader, ce qui ferme le FileReader associé.
			// Cette opération peut lever une IOException qu'il faut donc
			// traiter.
			entree.close();
		} catch (IOException e) {
			System.err.println("# Erreur à la fermeture de \"" + fichier + "\".");
			System.exit(1);
		}

		// On tente de supprimer le fichier en utilisant la méthode 'delete' de
		// la classe 'File'.
		if (!fichier.delete()) {
			// si cela échoue, il y a un problème...
			System.err.println("# Erreur : impossible de supprimer \"" + fichier.getName() + "\".");
			System.exit(1);
		}

	}
}
