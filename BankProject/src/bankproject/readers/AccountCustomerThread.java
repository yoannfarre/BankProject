package bankproject.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

import bankproject.entities.Account;
import bankproject.entities.Customer;
import bankproject.exceptions.SrvException;
import bankproject.services.SQLiteManager;
import bankproject.services.SrvAccount;
import bankproject.services.SrvCustomer;

public class AccountCustomerThread extends AbstractReaderThread {

	int time = 60000 * 7; // 7 minutes

	public static void main(String[] args) {

		AccountCustomerThread act = new AccountCustomerThread("A");
	}

	public AccountCustomerThread(String name) {
		super(name);
		System.out.println("statut du thread " + name + " = " + this.getState());
		this.start();
		System.out.println("statut du thread " + name + " = " + this.getState());
	}

	public void run() {

		while (true) { // TODO Ajouter une condition de sortie

			File file_account_customer = new File(getFileAPath());

			LinkedHashMap<Customer, Account> customer_account_map = new LinkedHashMap<>();
			LinkedHashSet<Customer> customer_set = new LinkedHashSet<>();

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

			// Partie lecture du fichier

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

				// Interprétation des infos de chaque ligne du fichier

				StringTokenizer st = new StringTokenizer(ligne, "\t\t\t");
				Customer customer = new Customer();
				Account account = new Account();
				int count = 0;
				boolean firstlineboolean = false;

				while (st.hasMoreTokens()) {

					String field = st.nextToken();

					// Saute la première ligne
					
					//  TODO Travailler evec l'Enum des mots de première ligne pour simplifier le code
					
					if (field.equals("Pays") || field.equals("Nom") || field.equals("Prenom")
							|| field.equals("Somme")) {
						firstlineboolean = true;
						continue;
					} else {

						switch (count) {
						case 0:
							account.setCountry(field.toUpperCase());
							break;
						case 1:
							customer.setLastname(field);
							break;
						case 2:
							customer.setFirstname(field);
							break;
						case 3:
							account.setSummary(Double.parseDouble(field));
							break;

						}

					}

					count++;
				}

				if (firstlineboolean == false) {

					customer_set.add(customer);
					customer_account_map.put(customer, account);
				}

			}

			try {
				input.close();
			} catch (IOException e) {
				System.err.println("# Error on closing \"" + file_account_customer + "\".");
				System.exit(1);
			}

			// Partie remplissage de base

			// Remplissage BDD Table Customer

			SrvCustomer srvCustomer = SrvCustomer.getInstance();
			srvCustomer.setDbManager(SQLiteManager.getInstance());

			SrvAccount srvAccount = SrvAccount.getInstance();
			srvAccount.setDbManager(SQLiteManager.getInstance());

			for (Customer customer : customer_set) {

				if (customer.getFirstname() != null && customer.getLastname() != null) {

					// Vérification de l'existence du Customer en BDD
					boolean existcustomer = true;

					try {

						int id = srvCustomer.get(customer.getFirstname(), customer.getLastname()).getId();
						customer.setId(id);
						// TODO Rajouter une condition pour vérifier si int est
						// null ou non
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						existcustomer = false;
						System.out.println("Super, un nouveau client!");
					}

					// Sauvegarde du customer

					try {
						srvCustomer.save(customer);
					} catch (SrvException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// Récupération de la nouvelle id créée si besoin

					if (existcustomer == false) {

						try {

							customer.setId(srvCustomer.get(customer.getFirstname(), customer.getLastname()).getId());

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

					// Remplissage BDD Table Account avec id correspondant

					Account account = customer_account_map.get(customer);

					if (account.getCountry() != null) {

						String number_ = account.buildNumber(account.getCountry());
						
						// TODO Rajouter une condition pour vérifier si le numéro existe déjà dans la base
						// Il faut récupérer tous les numéros de compte pour voir
						// Faire une boucle pour essayer de créer un numéro de compte jusqu'à ce qu'il soit valide
						
						account.setNumber(number_);

						account.setCustomer_id(customer.getId());

						try {
							srvAccount.save(account);
						} catch (SrvException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}

			}

			// Suppression du fichier
			// TODO Réactiver la suppression quand les tests seront terminés

			// deleteFile(file_account_customer);

			try {
				Thread.sleep(time);
			} catch (InterruptedException ie) {
				continue; // Recommencer en début de boucle
			}
		}

	}

	public static String getFileAPath() {
		String fs = System.getProperty("file.separator");
		String dirPath = System.getProperty("user.dir") + fs + "tmp" + fs + "bank" + fs + "input";
		File dir = new File(dirPath);
		if (!(dir.exists() && dir.isDirectory())) {
			dir.mkdirs();
			System.err.println("# Error : \"" + dir.getName() + "\" n'existe pas.");
		}

		return dirPath + fs + "account_customer.txt";
	}

	public void deleteFile(File file) {

		if (!file.delete()) {
			System.err.println("# Error on deleting \"" + file.getName() + "\".");
			System.exit(1);
		}
	}

}
