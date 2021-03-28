import java.io.File;

public class Controller 
{
	/**
	 * TODO:
	 * Aufgabe 1:
	 * Jeder Spieler kriegt nur seine 4 Bilder(Vorne, links, rechts, hinten) am Anfang (Controller)
	 * bevor das Spiel gestartet wird eingeladen. 
	 * 
	 * Optional: 
	 * Für Geschwindigkeit,Batterien etc. Bilder einladen.
	 * 
	 * Aufgabe 2:
	 * Wenn n-Spieler eine Taste wie x-drückem soll das passende Bild gemalt werden.
	 * Bsp. Spieler1 drückt w(Keylistener) -> Spieler1[seinBildVorne] zeichnen (in paintComponent)
	 * 
	 * Aufgabe 3:
	 * Füge 2 zusätzliche Booleans(1 für jeden Spieler) ein, die es verhindern diagonal zu laufen.
	 * Wenn w gedrückt wird, und dann s, soll nur der w Thread ausgeführt werden.
	 * 
	 */
	private int anzahlZeilen;
	private int anzahlSpalten;
	private int breite;
	private int hoehe;
	private boolean offlineOnline;
	private int anzahlSpieler;
	private boolean felderZufaelligSetzen;
	private Steine[][] steine;
	private Heu[][] heu;
	private Boni[][] boni;
	private Spieler[] spieler;
	private Object[][] bombe;
	private Grass grass;
	// Bombe wird gedrueckt
	//ExtraFeld[][] extraFeld;
	
	public Controller(int geradeAnzahlZeilen, int geradeAnzahlSpalten, int breite, 
			int hoehe, int anzahlSpieler, boolean offlineOnline, boolean felderZufaelligSetzen)
	{
		if(geradeAnzahlZeilen % 2 != 0)
		{
			anzahlZeilen = geradeAnzahlZeilen + 1; 
		}
		else
		{
			anzahlZeilen = geradeAnzahlZeilen;
		}
		if(geradeAnzahlSpalten % 2 != 0)
		{
			anzahlSpalten = geradeAnzahlSpalten + 1;
		}
		else
		{
			anzahlSpalten = geradeAnzahlSpalten;
		}
		if(breite / this.anzahlSpalten % 10 == 0)
		{
			this.breite = breite;
		}
		else
		{
			System.out.println("Bitte wählen sie die Breite und die Spalten aus, so das bei der Teilung von");
			System.out.println("der Breite durch die Spalten, Modulo 10 dies eine 0 ergibt. z.B.: 800 / 40 modulo 10 = 0");
		}
		if(hoehe / this.anzahlZeilen % 10 == 0)
		{
			this.hoehe = hoehe;	
		}
		else
		{
			System.out.println("Bitte wählen sie die Hoehe und die Zeilen aus, so das bei der Teilung von");
			System.out.println("der Hoehe durch die Zeilen, Modulo 10 dies eine 0 ergibt. z.B.: 600 / 30 modulo 10 = 0");
		}
		this.offlineOnline = offlineOnline;
		if(anzahlSpieler > 4)
		{
			this.anzahlSpieler = 4;
		}
		else
		{
			this.anzahlSpieler = anzahlSpieler;
		}
		this.felderZufaelligSetzen = felderZufaelligSetzen;
		steine = new Steine[this.anzahlZeilen][this.anzahlSpalten];
		heu = new Heu[this.anzahlZeilen][this.anzahlSpalten];
		setKonstruktionen(steine);
		setKonstruktionen(heu);
		synchronisierenKonstruktionen();
		grass = new Grass(heu, steine, this.anzahlZeilen, this.anzahlSpalten);
		boni = new Boni[this.anzahlZeilen][this.anzahlSpalten];
		setKonstruktionen(boni);
		setBonis();
		spieler = new Spieler[this.anzahlSpieler];
		setSpieler();
		bombe = new Object[this.anzahlZeilen][this.anzahlSpalten];
		setBombe();
		Fenster f = new Fenster(this, grass.getGrassArr(), boni, spieler, bombe, this.anzahlZeilen, this.anzahlSpalten, this.breite, this.hoehe);
		while(true)
		{
			try {
				Thread.sleep(10);
				f.repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		printKonstruktionen(steine);
//		printKonstruktionen(heu);
//		printKonstruktionen(boni);
	}
	
	public void setBombe()
	{
		for (int i = 0; i < bombe.length; i++) {
			for (int j = 0; j < bombe[i].length; j++) {
				bombe[i][j] = new Bombe(breite, hoehe, anzahlZeilen, anzahlSpalten);
			}
		}
	}
	
	public void setSpieler()
	{
		int spielerCounter = spieler.length-1;
		for (int i = 0; i < spieler.length; i++) {
			spieler[i] = new Spieler(breite, hoehe, anzahlZeilen, anzahlSpalten);
		}
		for (int i = 0; i < grass.getGrassArr().length; i++) {
			for (int j = 0; j < grass.getGrassArr()[i].length; j++) {
				if(spielerCounter >= 0 && (i == 0 && j == 0 || i == grass.getGrassArr().length - 1 && j == grass.getGrassArr()[0].length - 1 || 
					i == 0 && j == grass.getGrassArr()[0].length - 1 || i == grass.getGrassArr().length - 1 && j == 0))
				{
				spieler[spielerCounter].setX(j * breite / anzahlSpalten);
				spieler[spielerCounter].setY(i * hoehe / anzahlZeilen);
				spieler[spielerCounter].setSpielerNr(spielerCounter);
				spieler[spielerCounter].setDieI(spieler[spielerCounter].getY());
				spieler[spielerCounter].setDieJ(spieler[spielerCounter].getX());
				spieler[spielerCounter].setColorSpieler();
				spieler[spielerCounter].setImages(new File("/home/vikseba/workspace/Bomberi/src/Bilder/Spieler/vorneSee.png"),breite / anzahlSpalten,hoehe / anzahlZeilen);
				spielerCounter--;	
				}
			}
		}
	}
	
	public void setBonis()
	{
		for (int i = 0; i < boni.length; i++) {
			for (int j = 0; j < boni[i].length; j++) {
				if(boni[i][j].getExistiert())
				{
					boni[i][j].setBoni();
				}
			}
		}
	}
	
	public void synchronisierenKonstruktionen()
	{
		new SynchronisationDerKonstruktionen(heu, steine);
	}
	
	public void setKonstruktionen(Object object)
	{
		if(object instanceof Steine[][])
		{
			new Konstruktor(steine, anzahlZeilen, anzahlSpalten);
		}
		else if(object instanceof Heu[][])
		{
			new Konstruktor(heu, anzahlZeilen, anzahlSpalten);
		}
		else if(object instanceof Boni[][])
		{
			new Konstruktor(boni, heu, anzahlZeilen, anzahlSpalten);
		}
	}
	
//	public void printKonstruktionen(Object[][] object)
//	{
//		for (int i = 0; i < object.length; i++) 
//		{
//			for (int j = 0; j < object[i].length; j++) 
//			{
//				if(object instanceof Steine[][])
//				{
//					if(((Steine) object[i][j]).getExistiert())
//					{
//						System.out.print("X");
//					}
//					else
//						System.out.print(" ");
//				}
//				else if(object instanceof Heu[][])
//				{
//					if(((Heu) object[i][j]).getExistiert())
//					{
//						System.out.print("X");
//					}
//					else
//						System.out.print(" ");
//				}
//				else if(object instanceof Boni[][])
//				{
//					if(((Boni) object[i][j]).getExistiert())
//					{
//						System.out.print("X");
//					}
//					else
//						System.out.print(" ");
//				}
//			}
//			System.out.println();
//		}
//	}
	
	public static void main(String[] args) 
	{
		
		
//		while(true)
//		{
		//          zeilen
		//                spalten
		//				       breite
		//                          hoehe
		new Controller(60, 50, 1000, 600, 2, false, true);
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		}
		}
	
}
