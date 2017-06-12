package bankproject.writers;

public class CustomerStatementThread extends Thread {

	/********************************
	 *********** Attributes ***********
	 ********************************/

	static int time = 2000; // 2 sec in ms
	static int delay = 2000;// delaytime on launching

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
		System.out.println("statut du thread " + name + " = " + this.getState());
		this.start();
		System.out.println("statut du thread " + name + " = " + this.getState());
	}

	/********************************
	 *********** Methods ************
	 ********************************/

	public void run() {

		while (true) { // TODO Ajouter une condition de sortie
			
			try { //delay time on launching
				Thread.sleep(delay);
			} catch (InterruptedException ie) {
				continue; // Recommencer en début de boucle
			}

			FileCustomerWriter fcw = new FileCustomerWriter();

			try {
				Thread.sleep(time);
			} catch (InterruptedException ie) {
				continue; // Recommencer en début de boucle
			}
		}
	}

}
