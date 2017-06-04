package bankproject.writers;

public class CustomerStatementThread extends Thread {

	static int time = 2000; // 2 sec in ms

	public static void main(String[] args) {

		CustomerStatementThread act = new CustomerStatementThread("D");
	}

	public CustomerStatementThread(String name) {
		super(name);
		System.out.println("statut du thread " + name + " = " + this.getState());
		this.start();
		System.out.println("statut du thread " + name + " = " + this.getState());
	}

	public void run() {

		while (true) { // TODO Ajouter une condition de sortie
			
			FileCustomerWriter fcw = new FileCustomerWriter();

			try {
				Thread.sleep(time);
			} catch (InterruptedException ie) {
				continue; // Recommencer en début de boucle
			}
		}
	}



}
