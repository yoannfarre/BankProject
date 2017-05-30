package bankproject.writers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BankStatmentThread extends Thread {
	
	int time = 60000 * 13; // 13 minutes en ms

    // Pour �crire dans un fichier texte, on utilise un PrintWriter. Cette
    // classe se charge de transformer les diff�rents types de donn�es
    // (entiers, flottants etc) en leur repr�sentation sous forme de
    // caract�res. De plus, PrintWriter supporte la notion de ligne.
	
	public void run(){
		
		
    PrintWriter output = null;
    File file = new File("test.txt");
    
    
    try {
      output = new PrintWriter(new FileWriter(file));
    } catch (IOException e) {
      System.err.println("# Erreur : impossible de cr�er \""+ file +"\".");
      System.exit(1);
    }
    
    // On �crit maintenant les lignes de texte dans le fichier.
    for (int i = 0; i < 10; i++) {
      output.println(Integer.toString(i) + ": " );
    }
    

    output.close();
    if (output.checkError()) {
      System.err.println("# Erreur : le fichier \""+ file
                         + "\" n'a pu �tre enregistr� correctement.");
      System.exit(1);
    }

}
}
