package bankproject.writers;

public class CustomerStatementThread extends Thread {

	/********************************
	 *********** Attributes ***********
	 ********************************/

	private static int time = 2000; // 2 sec in ms : 7 sec en tout
	private static int delay = 5000;// delaytime on launching

	/********************************
	 *********** Main test **********
	 ********************************/

	public static void main(String[] args) {

		CustomerStatementThread act = new CustomerStatementThread("D");
	}

	/********************************
	 *********** Builders ***********
	 ********************************/

	public CustomerStatementThread(String name) {
		super(name);
		this.start();
		System.out.println("statut du thread " + name + " = " + this.getState());
	}

	/********************************
	 *********** Methods ************
	 ********************************/

	public void run() {

		while (true) { // TODO Ajouter une condition de sortie

			try { // delay time on launching
				Thread.sleep(delay);
				FileCustomerWriter fcw = new FileCustomerWriter();
				Thread.sleep(time);
			} catch (InterruptedException ie) {
				continue; // Recommencer en début de boucle
			}
		}
	}

}
