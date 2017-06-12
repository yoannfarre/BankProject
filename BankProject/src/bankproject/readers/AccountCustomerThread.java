package bankproject.readers;

public class AccountCustomerThread extends Thread {

	/********************************
	 ********** Attributes **********
	 ********************************/

	private int time = 60000 * 7; // 7 minutes

	/********************************
	 ********** Test Main ***********
	 ********************************/

	public static void main(String[] args) {

		AccountCustomerThread act = new AccountCustomerThread("A");
	}

	/********************************
	 ********** Builders ************
	 ********************************/

	public AccountCustomerThread(String name) {
		super(name);
		this.start();
		System.out.println("statut du thread " + name + " = " + this.getState());
	}

	/********************************
	 ********** Methods *************
	 ********************************/

	public void run() {

		while (true) { // TODO Ajouter une condition de sortie

			try {
				AccountCustomerReader Acr = new AccountCustomerReader();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				Thread.sleep(time);
			} catch (InterruptedException ie) {
				continue; // Recommencer en début de boucle
			}
		}

	}

}
