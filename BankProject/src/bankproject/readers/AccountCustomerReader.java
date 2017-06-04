package bankproject.readers;

import java.io.File;
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

public class AccountCustomerReader extends AbstractReader { // TODO Corriger Erreur sur la méthode getFileInputPath()

	public AccountCustomerReader() {

		this.input = null;
		this.file = new File(getFileInputPath());
		readFile();
//		
	}

	public String getFileInputPath() {

		String dirPath = getFileInputPrimaryPath() + "account_customer.txt";
//		System.out.println(dirPath);

//		File dir = new File(dirPath);
//
//		System.out.println(dir.getName());
//
//		if (!(dir.exists() && dir.isDirectory())) {
//			dir.mkdirs();
//			System.err.println("# Error : \"" + dir.getName() + "\" n'existe pas. Test");
//		}

		return dirPath;

	}

	public void readSpecificFile() {
		


		LinkedHashMap<Customer, Account> customer_account_map = new LinkedHashMap<>();
		LinkedHashSet<Customer> customer_set = new LinkedHashSet<>();

		while (true) {
			String ligne = null;
			try {
				ligne = input.readLine();
			} catch (IOException e) {
				System.err.println("# Erreur pendant la lecture de \"" + file + "\".");
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

				// TODO Travailler evec l'Enum des mots de première ligne pour
				// simplifier le code

				if (field.equals("Pays") || field.equals("Nom") || field.equals("Prenom") || field.equals("Somme")) {
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

					// TODO Rajouter une condition pour vérifier si le numéro
					// existe déjà dans la base
					// Il faut récupérer tous les numéros de compte pour voir
					// Faire une boucle pour essayer de créer un numéro de
					// compte jusqu'à ce qu'il soit valide

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

	}

}
