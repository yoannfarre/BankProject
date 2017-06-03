package bankproject.readers;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

import bankproject.entities.Account;
import bankproject.entities.Customer;
import bankproject.entities.Operation;
import bankproject.enumerations.TypeOperationEnum;
import bankproject.services.SQLiteManager;
import bankproject.services.SrvAccount;
import bankproject.services.SrvCustomer;
import bankproject.services.SrvOperation;

public class OperationReader extends AbstractReader {

	public OperationReader() {

		this.input = null;
		this.file = new File(getFileInputPath());
		readFile();

	}

	public String getFileInputPath() {

		String dirPath = getFileInputPrimaryPath() + "operation.txt";
		System.out.println(dirPath);

		File dir = new File(dirPath);

		System.out.println(dir.getName());

		if (!(dir.exists() && dir.isDirectory())) {
			dir.mkdirs();
			System.err.println("# Error : \"" + dir.getName() + "\" n'existe pas.");
		}

		return dirPath;

	}

	public void readSpecificFile() {

		// LinkedHashMap<Customer, LinkedHashMap<Account, Operation>>
		// customer_accop_map = new LinkedHashMap<>();
		LinkedHashMap<Account, Operation> account_operation_map = new LinkedHashMap<>();
		LinkedHashSet<Account> account_set = new LinkedHashSet<>();

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
			Operation operation = new Operation();
			int count = 0;
			boolean firstlineboolean = false;

			while (st.hasMoreTokens()) {

				String field = st.nextToken();

				// Saute la première ligne

				// TODO Travailler evec l'Enum des mots de première ligne pour
				// simplifier le code

				if (field.equals("Amount") || field.equals("Account") || field.equals("Customer")) {
					firstlineboolean = true;
					continue;
				} else {

					switch (count) {
					case 0:
						StringBuilder amount_sb = new StringBuilder();
						// Prise du premier caractère pour déterminer le type
						// d'opération
						if (field.charAt(0) == '+') {
							operation.setType_operation(TypeOperationEnum.CREDIT);

						} else if (field.charAt(0) == '-') {
							operation.setType_operation(TypeOperationEnum.DEBIT);

						}
						// Parcours de la chaine à l'exception du premier
						// caractère
						for (int i = 1; i < field.length(); i++) {
							amount_sb.append(field.charAt(i));
						}

						operation.setAmount(Double.parseDouble(amount_sb.toString()));

						break;

					case 1:
						account.setNumber(field);
						break;
					case 2:

						StringTokenizer name_st = new StringTokenizer(field, " ");
						int count2 = 0;

						while (name_st.hasMoreTokens()) {

							String inname = name_st.nextToken();

							if (count2 == 0) {

								customer.setFirstname(inname);

							} else if (count2 == 1) {

								customer.setLastname(inname);

							}

							count2++;

						}
						break;

					}

				}

				count++;
			}

			if (firstlineboolean == false) {

				account_set.add(account);
				account_operation_map.put(account, operation);

			}

		}

		// Partie remplissage de base

		// Remplissage BDD Table Customer

		SrvCustomer srvCustomer = SrvCustomer.getInstance();
		srvCustomer.setDbManager(SQLiteManager.getInstance());

		SrvAccount srvAccount = SrvAccount.getInstance();
		srvAccount.setDbManager(SQLiteManager.getInstance());

		SrvOperation srvOperation = SrvOperation.getInstance();
		srvOperation.setDbManager(SQLiteManager.getInstance());

		for (Account account : account_set) {

			// pour info
			// LinkedHashMap<Customer, LinkedHashMap<Account, Operation>>
			// customer_accop_map = new LinkedHashMap<>();
			// LinkedHashMap<Account, Operation> account_operation_map = new
			// LinkedHashMap<>();
			// LinkedHashSet<Customer> customer_set = new LinkedHashSet<>();
			
			// Verifier si le compte existe et récuperation ID

			if (account.getNumber() != null) {

				boolean existaccount = true;

				try {
					
					Account account_up = srvAccount.get(account.getNumber());
//					account.setCustomer_id(customer_);
//					int id = srvAccount.get(account.getNumber()).getId();
//					account.setId(id);
					

				} catch (Exception e1) {
					existaccount = false;
					System.out.println("Le compte" + account.getNumber() + "n'existe pas"); 
					
					// TODO Il faut sortir de la boucle
				}

			}

			// Sauvegarde du customer

			// try {
			// srvCustomer.save(customer);
			// } catch (SrvException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// } catch (SQLException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

			// Récupération de la nouvelle id créée si besoin

			// if (existcustomer == false) {
			//
			// try {
			//
			// customer.setId(srvCustomer.get(customer.getFirstname(),
			// customer.getLastname()).getId());
			//
			// } catch (Exception e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
			// }

			// Remplissage BDD Table Account avec id correspondant

			// Account account = customer_account_map.get(customer);

			// if (account.getCountry() != null) {
			//
			// String number_ = account.buildNumber(account.getCountry());
			//
			// // TODO Rajouter une condition pour vérifier si le numéro
			// // existe déjà dans la base
			// // Il faut récupérer tous les numéros de compte pour voir
			// // Faire une boucle pour essayer de créer un numéro de
			// // compte jusqu'à ce qu'il soit valide
			//
			// account.setNumber(number_);
			//
			// account.setCustomer_id(customer.getId());
			//
			// try {
			// srvAccount.save(account);
			// } catch (SrvException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// } catch (SQLException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			//
			// }

			// }
			//
			// }

		}
	}

}
