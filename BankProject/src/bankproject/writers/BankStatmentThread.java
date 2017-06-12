package bankproject.writers;

public class BankStatmentThread extends Thread {
	
	/********************************
	 ********** Attributes **********
	 ********************************/

	private static int time = 60000 * 13; // 13 minutes en ms
	private static int delay = 2000; // delaytime 1s;
	
	/********************************
	 ********** Main test ***********
	 ********************************/

	public static void main(String[] args) {

		BankStatmentThread act = new BankStatmentThread("C");
	}
	
	/********************************
	 *********** Builders ***********
	 ********************************/

	public BankStatmentThread(String name) {
		super(name);
		this.start();
		System.out.println("statut du thread " + name + " = " + this.getState());
	}
	
	/********************************
	 *********** Methods ***********
	 ********************************/

	public void run() {

		while (true) { // TODO Ajouter une condition de sortie
			
			try { //delay time on launching
				Thread.sleep(delay);
			} catch (InterruptedException ie) {
				continue; // Recommencer en début de boucle
			}

			FileCountryWriter fcw = new FileCountryWriter();

			FileDebitorsWriter fdebw = new FileDebitorsWriter();

			FileCreditorsWriter fcrew = new FileCreditorsWriter();

			try {
				Thread.sleep(time);
			} catch (InterruptedException ie) {
				continue; // Recommencer en début de boucle
			}
		}
	}

}
