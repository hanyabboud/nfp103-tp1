package Exercice8;

public class Compteur extends Thread {

	private static int pos;

	private static Compteur[] cpts;

	public Compteur(String name) {
		super(name);
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(currentThread().getName() + " = " + i);
			interruptRandom();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {

			}
			if (i == 4) {
				synchronized (System.out) {
					System.out.printf("the thread %s has ended with %d rank\n", currentThread().getName(), ++pos);
				}
			}
		}

	}

	private void interruptRandom() {
		cpts[(int) (Math.random() * 3)].interrupt();
	}

	public static void main(String[] args) {
		pos = 0;
		cpts = new Compteur[] { new Compteur("TH1"), new Compteur("TH2"), new Compteur("TH3"), new Compteur("TH4") };
		for (Compteur c : cpts) {
			c.start();
		}

	}
}