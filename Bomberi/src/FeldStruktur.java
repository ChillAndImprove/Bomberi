
public class FeldStruktur {

	private int anzahlZeilen;
	private int anzahlSpalten;
	private boolean[][] feldStruktur;
	private int anzahlMoeglicherFelder;
	private int maximalMoeglicheZusammensetzungDerSteine;
	private boolean[][] willKeineWiederholungswerte;
	private boolean stein;
	private boolean heu;
	private boolean boni;
	private Heu[][] heuArr;

	public FeldStruktur(int anzahlZeilen, int anzahlSpalten, int anzahlMoeglicherFelder, boolean heu) {
		this.anzahlZeilen = anzahlZeilen;
		this.anzahlSpalten = anzahlSpalten;
		this.heu = heu;
		this.anzahlMoeglicherFelder = anzahlMoeglicherFelder;
		setFeldStruktur();
	}

	public FeldStruktur(int anzahlZeilen, int anzahlSpalten, int anzahlMoeglicherFelder, boolean boni, Heu[][] heu) {
		this.heuArr = heu;
		this.anzahlZeilen = anzahlZeilen;
		this.anzahlSpalten = anzahlSpalten;
		this.boni = boni;
		this.anzahlMoeglicherFelder = anzahlMoeglicherFelder;
		setFeldStruktur();
	}

	public FeldStruktur(int anzahlZeilen, int anzahlSpalten, int anzahlMoeglicherFelder,
			int maximalMoeglicheZusammensetzungDerSteine, boolean stein) {
		this.anzahlZeilen = anzahlZeilen;
		this.anzahlSpalten = anzahlSpalten;
		this.anzahlMoeglicherFelder = anzahlMoeglicherFelder;
		this.maximalMoeglicheZusammensetzungDerSteine = maximalMoeglicheZusammensetzungDerSteine;
		this.stein = stein;
		setFeldStruktur();
	}

	public void setFeldStrukturManuel() {
		feldStruktur = new boolean[anzahlZeilen / 2][anzahlSpalten / 2];
		for (int i = 0; i < feldStruktur.length; i++) {
			for (int j = 0; j < feldStruktur[i].length; j++) {
				if (stein) {
					if (pruefeAufMaximalMoeglicheZusammensetzungDerSteine(i, j)) {
						if (i == 0 || j == 0) {
							feldStruktur[i][j] = true;
						} else {
							feldStruktur[i][j] = false;
						}
					} else {
						feldStruktur[i][j] = false;
					}
				}
			}
		}
	}

	public void setFeldStruktur() {
		feldStruktur = new boolean[anzahlZeilen / 2][anzahlSpalten / 2];
		for (int i = 0; i < feldStruktur.length; i++) {
			for (int j = 0; j < feldStruktur[i].length; j++) {
				// wenn maximalMoeglicheZusammensetzungDerSteine ueberhaupt
				// initialisiert wird,
				// heißt es das Array der Steine abgearbeitet wird
				if (stein) {
					if (i == 0 || j == 0 /* || i == feldStruktur.length - 1 */) {
						feldStruktur[i][j] = false;
					} else if (pruefeAufMaximalMoeglicheZusammensetzungDerSteine(i, j)) {
						if (anzahlMoeglicherFelder > 0) {
							int r = (int) ((Math.random() * 10) + 1);
							if (r <= 5) {
								feldStruktur[i][j] = true;
								anzahlMoeglicherFelder--;
							} else {
								feldStruktur[i][j] = false;
							}
						}
					} else {
						feldStruktur[i][j] = false;
					}
				} else if (boni) {
					if (heuArr[i][j].getExistiert() == true) {
						if (anzahlMoeglicherFelder > 0) {
							int r = (int) ((Math.random() * 10) + 1);
							if (r <= 5) {
								feldStruktur[i][j] = true;
								anzahlMoeglicherFelder--;
							} else {
								feldStruktur[i][j] = false;
							}
						}
					} else {
						feldStruktur[i][j] = false;
					}
				} else if (heu) {
					if(i == 0 && j == 0 || i == 0 && j == 1 || i == 1 && j == 0)
					{
						feldStruktur[i][j] = false;
					}
					else if (anzahlMoeglicherFelder > 0) {
						int r = (int) ((Math.random() * 10) + 1);
						if (r <= 5) {
							feldStruktur[i][j] = true;
							anzahlMoeglicherFelder--;
						} else {
							feldStruktur[i][j] = false;
						}
					}
				}
			}
		}
	}

	public boolean pruefeAufMaximalMoeglicheZusammensetzungDerSteine(int i, int j) {
		boolean gehtNoch = true;
		willKeineWiederholungswerte = new boolean[feldStruktur.length][feldStruktur[0].length];
		if (rekursiverCounter(i, j) > maximalMoeglicheZusammensetzungDerSteine - 1) {
			gehtNoch = false;
		}
		return gehtNoch;
	}

	public int rekursiverCounter(int i, int j) {
		willKeineWiederholungswerte[i][j] = true;
		// oben
		if (i != 0 && !willKeineWiederholungswerte[i - 1][j] && feldStruktur[i - 1][j] == true) {
			return rekursiverCounter(i - 1, j) + 1 + rekursiverCounter(i, j);
		}
		// diagonal links oben
		if (i != 0 && j != 0 && !willKeineWiederholungswerte[i - 1][j - 1] && feldStruktur[i - 1][j - 1] == true) {
			return rekursiverCounter(i - 1, j - 1) + 1 + rekursiverCounter(i, j);
		}
		// links
		if (j != 0 && !willKeineWiederholungswerte[i][j - 1] && feldStruktur[i][j - 1] == true) {
			return rekursiverCounter(i, j - 1) + 1 + rekursiverCounter(i, j);
		}
		// rechts
		if (j != feldStruktur[0].length - 1 && !willKeineWiederholungswerte[i][j + 1]
				&& feldStruktur[i][j + 1] == true) {
			return rekursiverCounter(i, j + 1) + 1 + rekursiverCounter(i, j);
		}
		// diagonal rechts unten
		if (i != feldStruktur.length - 1 && j != feldStruktur[0].length - 1
				&& !willKeineWiederholungswerte[i + 1][j + 1] && feldStruktur[i + 1][j + 1]) {
			return rekursiverCounter(i + 1, j + 1) + 1 + rekursiverCounter(i, j);
		}
		// diagonal links unten
		if (i != feldStruktur.length - 1 && j != 0 && !willKeineWiederholungswerte[i + 1][j - 1]
				&& feldStruktur[i + 1][j - 1] == true) {
			return rekursiverCounter(i + 1, j - 1) + 1 + rekursiverCounter(i, j);
		}
		// unten
		if (i != feldStruktur.length - 1 && !willKeineWiederholungswerte[i + 1][j] && feldStruktur[i + 1][j] == true) {
			return rekursiverCounter(i + 1, j) + 1 + rekursiverCounter(i, j);
		}
		// diagonal rechts oben
		if (i != 0 && j != feldStruktur[0].length - 1 && !willKeineWiederholungswerte[i - 1][j + 1]
				&& feldStruktur[i - 1][j + 1] == true) {
			return rekursiverCounter(i - 1, j + 1) + 1 + rekursiverCounter(i, j);
		}
		return 0;

	}

	public boolean[][] getFeldStruktur() {
		return feldStruktur;
	}

	public boolean[][] getFeldStrukturSpiegelbild() {
		boolean[][] feldStrukturSpiegelbild = new boolean[feldStruktur.length][feldStruktur[0].length];
		for (int i = 0; i < feldStruktur.length; i++) {
			int l = feldStrukturSpiegelbild[0].length - 1;
			for (int j = 0; j < feldStruktur[i].length; j++) {
				feldStrukturSpiegelbild[i][l] = feldStruktur[i][j];
				--l;
			}
		}
		return feldStrukturSpiegelbild;
	}

	public boolean[][] getFeldStrukturVerdreht() {
		boolean[][] feldStrukturVerdreht = new boolean[feldStruktur.length][feldStruktur[0].length];
		int k = feldStrukturVerdreht.length - 1;
		for (int i = 0; i < feldStruktur.length; i++) {
			for (int j = 0; j < feldStruktur[i].length; j++) {
				feldStrukturVerdreht[k][j] = feldStruktur[i][j];
			}
			--k;
		}
		return feldStrukturVerdreht;
	}

	public boolean[][] getFeldStrukturSpiegelbildVerdreht() {
		boolean[][] feldStrukturSpiegelbildVerdreht = new boolean[feldStruktur.length][feldStruktur[0].length];
		int k = feldStrukturSpiegelbildVerdreht.length - 1;
		for (int i = 0; i < feldStruktur.length; i++) {
			int l = feldStrukturSpiegelbildVerdreht[0].length - 1;
			for (int j = 0; j < feldStruktur[0].length; j++) {
				feldStrukturSpiegelbildVerdreht[k][l] = feldStruktur[i][j];
				--l;
			}
			--k;
		}
		return feldStrukturSpiegelbildVerdreht;
	}
}