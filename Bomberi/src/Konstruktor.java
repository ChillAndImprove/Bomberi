
public class Konstruktor {
	
	private Object[][] object;
	private int anzahlZeilen;
	private int anzahlSpalten;
	private int prozentenBesetztesFeld;
	private FeldStruktur feldStruktur;
	private boolean[][] konstruktion;
	private Heu[][] heu;
	
	public Konstruktor(Object[][] object, int anzahlZeilen, int anzahlSpalten)
	{
		this.object = object;
		this.anzahlZeilen = anzahlZeilen;
		this.anzahlSpalten = anzahlSpalten;
		setNeueObjecte();
		setKonstruktion();
	}
	
	public Konstruktor(Object[][] boni, Heu[][] heu, int anzahlZeilen, int anzahlSpalten)
	{
		this.object = boni;
		this.heu = heu;
		this.anzahlZeilen = anzahlZeilen;
		this.anzahlSpalten = anzahlSpalten;
		setNeueObjecte();
		setKonstruktion();
	}
	
	public void setNeueObjecte()
	{
		for (int i = 0; i < object.length; i++) 
		{
			for(int j = 0; j < object[i].length; j++)
			{
				if(object instanceof Steine[][])
				{
					object[i][j] = new Steine();
				}
				else if(object instanceof Heu[][])
				{
					object[i][j] = new Heu();
				}
				else if(object instanceof Boni[][])
				{
					object[i][j]  = new Boni();
				}
			}
		}
	}
	
	public void setKonstruktion()
	{
		// ist für alle unterschiedliche Objecte noetig
		int anzahlMoeglicherFelder = 0;
		konstruktion = new boolean[anzahlZeilen][anzahlSpalten];
		if(object instanceof Steine[][])
		{
			boolean stein = true;
			// Standart ist die 7
			int maximalMoeglicheZusammensetzungDerSteine = 3;
			prozentenBesetztesFeld = 20;
			anzahlMoeglicherFelder = anzahlZeilen * anzahlSpalten * prozentenBesetztesFeld / 100;
			feldStruktur = new FeldStruktur(anzahlZeilen, anzahlSpalten, anzahlMoeglicherFelder, maximalMoeglicheZusammensetzungDerSteine, stein);
		}
		
		else if(object instanceof Heu[][])
		{
			boolean heu = true;
			prozentenBesetztesFeld = 42;
			anzahlMoeglicherFelder = (anzahlZeilen * anzahlSpalten * prozentenBesetztesFeld / 100) / 4;
			feldStruktur = new FeldStruktur(anzahlZeilen, anzahlSpalten, anzahlMoeglicherFelder, heu);
		}
		
		else if(object instanceof Boni[][])
		{
			boolean boni = true;
			prozentenBesetztesFeld = 18;
			anzahlMoeglicherFelder = (anzahlZeilen * anzahlSpalten * prozentenBesetztesFeld / 100) / 4;
			feldStruktur = new FeldStruktur(anzahlZeilen, anzahlSpalten, anzahlMoeglicherFelder, boni, heu);
		}
		
		for (int i = 0; i < feldStruktur.getFeldStruktur().length; i++) 
		{
			for (int j = 0; j < feldStruktur.getFeldStruktur()[i].length; j++) 
			{
				konstruktion[i][j] = feldStruktur.getFeldStruktur()[i][j];
			}
		}
		for (int i = 0; i < feldStruktur.getFeldStruktur().length; i++) 
		{
			for (int j = 0; j < feldStruktur.getFeldStruktur()[i].length; j++) 
			{
				konstruktion[i][j + (anzahlSpalten / 2)] = feldStruktur.getFeldStrukturSpiegelbild()[i][j];
			}
		}
		for (int i = 0; i < feldStruktur.getFeldStruktur().length; i++) 
		{
			for (int j = 0; j < feldStruktur.getFeldStruktur()[i].length; j++) 
			{
				konstruktion[i + (anzahlZeilen / 2)][j] = feldStruktur.getFeldStrukturVerdreht()[i][j];
			}
		}
		for (int i = 0; i < feldStruktur.getFeldStruktur().length; i++) 
		{
			for (int j = 0; j < feldStruktur.getFeldStruktur()[i].length; j++) 
			{
				konstruktion[i + (anzahlZeilen / 2)][j + (anzahlSpalten / 2)] = feldStruktur.getFeldStrukturSpiegelbildVerdreht()[i][j];
			}
		}
		
//		drueckeKonstruktionAus();
		
		if(object instanceof Steine[][])
		{
			for (int i = 0; i < konstruktion.length; i++) 
			{
				for (int j = 0; j < konstruktion[i].length; j++) 
				{
					((Steine) object[i][j]).setExistiert(konstruktion[i][j]);
				}
			}
		}
		
		else if(object instanceof Heu[][])
		{
			for (int i = 0; i < konstruktion.length; i++) 
			{
				for (int j = 0; j < konstruktion[i].length; j++) 
				{
					((Heu) object[i][j]).setExistiert(konstruktion[i][j]);
				}
			}
		}
		
		else if(object instanceof Boni[][])
		{
			for (int i = 0; i < konstruktion.length; i++) {
				for (int j = 0; j < konstruktion[i].length; j++) {
					((Boni) object[i][j]).setExistiert(konstruktion[i][j]);
				}
			}
		}
	}
	
//	public void drueckeKonstruktionAus()
//	{
//		for (int i = 0; i < konstruktion.length; i++) 
//		{
//			for (int j = 0; j < konstruktion[i].length; j++) 
//			{
//				if(konstruktion[i][j] == true)
//				{
//					System.out.print("O");
//				}
//				else
//				{
//					System.out.print(" ");
//				}
//			}
//			System.out.println();
//		}
//	}
}
