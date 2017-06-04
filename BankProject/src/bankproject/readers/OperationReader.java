package bankproject.readers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

import bankproject.entities.Account;
import bankproject.entities.Customer;
import bankproject.entities.Operation;
import bankproject.enumerations.TypeOperationEnum;
import bankproject.exceptions.SrvException;
import bankproject.services.SQLiteManager;
import bankproject.services.SrvAccount;
import bankproject.services.SrvCustomer;
import bankproject.services.SrvOperation;

public class OperationReader extends AbstractReader {

	/********************************
	 ********** Builders ************
	 ********************************/

	public OperationReader() {

		this.input = null;
		this.file = new File(getFileInputPath());
		readFile();

	}

	/********************************
	 ********** Methods *************
	 ********************************/

	public String getFileInputPath() {

		String dirPath = getFileInputPrimaryPath() + "operation.txt";
		// System.out.println(dirPath);
		//
		// File dir = new File(dirPath);
		//
		// System.out.println(dir.getName());
		//
		// if (!(dir.exists() && dir.isDirectory())) {
		// dir.mkdirs();
		// System.err.println("# Error : \"" + dir.getName() + "\" n'existe
		// pas.");
		// }

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

			// TODO : il faudrait vérifier que le compte appartient eu bon
			// Customer

			boolean existaccount = false;

			if (account.getNumber() != null) {

				try {

					Account account_up = srvAccount.get(account.getNumber());
					account.setId(account_up.getId());
					account.setSummary(account_up.getSummary());
					account.setCountry(account_up.getCountry());
					account.setCustomer_id(account_up.getCustomer_id());
					existaccount = true;

				} catch (Exception e1) {
					existaccount = false;
					System.out.println("Le compte " + account.getNumber() + " n'existe pas");

					// TODO Il faut sortir de la boucle
					continue;
				}

			}

			// Mise à jour du compte

			if (existaccount == true) {

				double sum = account.getSummary();

				if (account_operation_map.get(account).getType_operation() == TypeOperationEnum.DEBIT) {
					account.setSummary(sum - account_operation_map.get(account).getAmount());
				}

				else if (account_operation_map.get(account).getType_operation() == TypeOperationEnum.CREDIT) {
					account.setSummary(sum + account_operation_map.get(account).getAmount());
				}

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

			// Création de l'opération dans DB

			if (existaccount == true) { // même condition que précédemment

				Operation operation = account_operation_map.get(account);
				Date date = new Date();

				SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

				// System.out.println("Current Date: " + ft.format(date));

				operation.setAccount_id(account.getId());
				operation.setCustomer_id(account.getCustomer_id());
				operation.setDate(ft.format(date));

				try {
					srvOperation.save(operation);
				} catch (SrvException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//
				// }

				// }
				//
				// }

			}
		}

	}
}
