package bankproject.readers;

public class AccountCustomerThread extends Thread {

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

			AccountCustomerReader Acr = new AccountCustomerReader();

			try {
				Thread.sleep(time);
			} catch (InterruptedException ie) {
				continue; // Recommencer en début de boucle
			}
		}

	}

}
