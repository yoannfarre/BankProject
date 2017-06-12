package bankproject.readers;

public class OperationThread extends Thread {

	/********************************
	 ********** Attributes **********
	 ********************************/
	private int delay = 2000; // Delaytime 2s
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
		this.start();
		System.out.println("statut du thread " + name + " = " + this.getState());
	}

	/********************************
	 ********** Methods *************
	 ********************************/

	public void run() {

		while (true) { // TODO Ajouter une condition de sortie

			try {
				Thread.sleep(delay); //delay on launching
				OperationReader or = new OperationReader();
				Thread.sleep(time);
			} catch (InterruptedException ie) {
				continue; // Recommencer en début de boucle
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
