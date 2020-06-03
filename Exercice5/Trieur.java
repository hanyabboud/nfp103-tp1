package Exercice5;

public class Trieur extends Thread {

	private int[] t;
	private int debut, fin;

	private Trieur(int[] t, int debut, int fin) {
		this.t = t;
		this.debut = debut;
		this.fin = fin;
	}

	public void run() {
		System.out.println("Thread created debut " + debut + " fin " + fin);
		Trieur tableau = new Trieur(t, debut, fin);
		tableau.trier();
	}

	private void trier() {
		if (fin - debut < 2) {
			if (t[debut] > t[fin]) {
				echanger(debut, fin);
			}

			synchronized (this) {
				System.out.println("potential exchange ");
				this.notify();
				System.out.println("Notified ");
			}
		} else {
			int milieu = debut + (fin - debut) / 2;
			Trieur t1 = new Trieur(t, debut, milieu);
			t1.start();

			Trieur t2 = new Trieur(t, milieu + 1, fin);
			t2.start();
			try {
				synchronized (t1) {
					synchronized (t2) {
						System.out.println("wait debut " + debut + " milieu " + milieu + " fin " + fin);

						t1.wait();
						t2.wait();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			triFusion(debut, milieu, fin);
			synchronized (this) {
				this.notify();
			}
		}
	}

	private void echanger(int i, int j) {
		int valeur = t[i];
		t[i] = t[j];
		t[j] = valeur;
	}

	private synchronized void triFusion(int debut, int milieu, int fin) {
		// tableau où va aller la fusion
		int[] tFusion = new int[fin - debut + 1];
		// int milieu = (debut + fin) / 2;
		assert (debut + fin) / 2 == milieu;
		// Indices des éléments à comparer
		int i1 = debut, i2 = milieu + 1;
		// indice de la prochaine case du tableau tFusion à remplir
		int iFusion = 0;
		while (i1 <= milieu && i2 <= fin) {
			if (t[i1] < t[i2]) {
				tFusion[iFusion++] = t[i1++];
			} else {
				tFusion[iFusion++] = t[i2++];
			}
		}
		if (i1 > milieu) {
			// la 1ère tranche est épuisée
			for (int i = i2; i <= fin;) {
				tFusion[iFusion++] = t[i++];
			}
		} else {
			// la 2ème tranche est épuisée
			for (int i = i1; i <= milieu;) {
				tFusion[iFusion++] = t[i++];
			}
		}
		// Copie tFusion dans t
		for (int i = 0, j = debut; i <= fin - debut;) {
			t[j++] = tFusion[i++];
		}
	}

	public static void main(String[] args) {
		int[] t = { 13, 7, 10, 12, 67, 120, 41, 63, 91};
		Trieur tr = new Trieur(t, 0, t.length - 1);
		tr.start();
		try {
			synchronized (tr) {
				System.out.println("wait -1");
				tr.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < t.length; i++) {
			System.out.print(t[i] + " ; ");
		}
		System.out.println();
	}
}
