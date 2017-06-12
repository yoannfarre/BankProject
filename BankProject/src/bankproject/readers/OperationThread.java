package bankproject.readers;

public class OperationThread extends Thread {

	/********************************
	 ********** Attributes **********
	 ********************************/
	private int delay = 2000; // Delaytime 1s
	private int time = 60000 * 11; // 11 minutes

	/********************************
	 ********** Main Test ***********
	 ********************************/

	public static void main(String[] args) {

		OperationThread act = new OperationThread("B");
	}

	/********************************
	 ********** Builders ************
	 ********************************/

	public OperationThread(String name) {
		super(name);
		System.out.println("statut du thread " + name + " = " + this.getState());
		this.start();
		System.out.println("statut du thread " + name + " = " + this.getState());
	}

	/********************************
	 ********** Methods *************
	 ********************************/

	public void run() {

		while (true) { // TODO Ajouter une condition de sortie
			
			try { //delay time on launching
				Thread.sleep(delay);
			} catch (InterruptedException ie) {
				continue; // Recommencer en début de boucle
			}

			OperationReader or = new OperationReader();

			try {
				Thread.sleep(time);
			} catch (InterruptedException ie) {
				continue; // Recommencer en début de boucle
			}
		}

	}
}
