import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

public class Spieler {
	
	private int bombenLevel;
	private int schwellenLevel;
	private int geschwindigkeitsLevelX;
	private int geschwindigkeitsLevelY;
	private int batterienLevel;
	private boolean lebt;
	private int x;
	private int y;
	private int spielerNr;
	private int zeilen;
	private int spalten;
	private int breite;
	private int hoehe;
	private int i;
	private int j;
	private int iAbrunden;
	private int jAbrunden;
	private boolean unbesiegbar;
	private int ichWurdeVerbrantVomI;
	private int ichWurdeVerbrantVomJ;
	private int geschwindigkeitserhoehungBreite;
	private int geschwindigkeitserhoehungHoehe;
	private boolean einDrittelBatterieNochDrin;
	private int schwellenStaerkeX;
	private int schwellenStaerkeY;
	private int[] geschwindigkeitsZahlenX;
	private int[] geschwindigkeitsZahlenY;
	public int geschwindigkeitsZahlX;
	public int geschwindigkeitsZahlY;
	private Color colorSpieler;
	public BufferedImage vorne;
	private BufferedImage vorneLinks;
	private BufferedImage vorneRechts;
	Bilder bild;
	
	public Spieler(int breite, int hoehe, int zeilen, int spalten)
	{
		this.breite = breite;
		this.hoehe = hoehe;
		this.zeilen = zeilen;
		this.spalten = spalten;
		this.bild = new Bilder();

		setGeschwindigkeitserhoehungBreite();
		setGeschwindigkeitserhoehungHoehe();
		lebt = true;
		bombenLevel = 5;
		schwellenLevel = 1;
		geschwindigkeitsLevelX = geschwindigkeitserhoehungBreite;
		geschwindigkeitsLevelY = geschwindigkeitserhoehungHoehe;
		batterienLevel = 3;
		einDrittelBatterieNochDrin = false;
		setLebt(lebt);
		setBombenLevel(bombenLevel);
		setSchwellenLevel(schwellenLevel);
		setBatterienLevel(batterienLevel);
		geschwindigkeitsZahlenX = new int[getAnzahlZahlenX()];
		geschwindigkeitsZahlenY = new int[getAnzahlZahlenY()];
		setGeschwindigkeitsZahlenX();
		setGeschwindigkeitsZahlenY();
		geschwindigkeitsZahlX = 1;
		geschwindigkeitsZahlY = 1;
	}
	
	public int getAnzahlZahlenX()
	{
		int counter = 0;
		for(int i = 1; i <= breite / spalten; i ++)
		{
			if(breite / spalten % i == 0)
			{
				counter++;
			}
		}
		return counter;
	}
	
	public int getAnzahlZahlenY()
	{
		int counter = 0;
		for(int i = 1; i <= hoehe / zeilen; i++)
		{
			if((hoehe / zeilen) % i == 0)
			{
				counter++;		
			}
		}
		return counter;
	}
	
	public void setGeschwindigkeitsZahlenX()
	{
		int counter = 0;
		for (int i = breite / spalten; i >= 1; i--) {
			if((breite / spalten) % i == 0)
			{
				geschwindigkeitsZahlenX[counter++] = breite / spalten / i;
			}
		}
	}
	
	public void setGeschwindigkeitsZahlenY()
	{
		int counter = 0;
		for (int i = hoehe / zeilen; i >= 1; i--) {
			if((hoehe / zeilen) % i == 0)
			{
				geschwindigkeitsZahlenY[counter++] = hoehe / zeilen / i;
			}
		}
	}
	
	public int getGeschwindigkeitsZahlX()
	{	
		return geschwindigkeitsZahlenX[geschwindigkeitsZahlX - 1];	
	}
	
	public int getGeschwindigkeitsZahlY()
	{
		return geschwindigkeitsZahlenY[geschwindigkeitsZahlY - 1];
	}
	
	public void setGeschwindigkeitsZahlY(int geschwindigkeitsZahlY)
	{
		if(geschwindigkeitsZahlY <= geschwindigkeitsZahlenY.length)
		{
		this.geschwindigkeitsZahlY = geschwindigkeitsZahlY;
		}
		}
	
	public void setGeschwindigkeitsZahlX(int geschwindigkeitsZahlX)
	{
		if(geschwindigkeitsZahlX <= geschwindigkeitsZahlenX.length)
		{
		this.geschwindigkeitsZahlX = geschwindigkeitsZahlX;
		}
		}
	
	public void setGeschwindigkeitserhoehungBreite()
	{
		this.geschwindigkeitserhoehungBreite = breite / spalten / 10;
	}
	
	public void setGeschwindigkeitserhoehungHoehe()
	{
		this.geschwindigkeitserhoehungHoehe = hoehe / zeilen / 10;
	}
	
	public void setIchWurdeVerbrantVom(int i, int j)
	{
		this.ichWurdeVerbrantVomJ = j;
		this.ichWurdeVerbrantVomI = i;
	}
	
	public int getIchWurdeVerbrantVomI()
	{
		return ichWurdeVerbrantVomI;
	}
	
	public int getIchWurdeVerbrantVomJ()
	{
		return ichWurdeVerbrantVomJ;
	}
	
	public void setUnbesiegbar(boolean unbesigbar)
	{
		this.unbesiegbar = unbesigbar;
	}
	
	public boolean getUnbesiegbar()
	{
		return unbesiegbar;
	}
	
	public void setSpielerNr(int spielerNr)
	{
		this.spielerNr = spielerNr;
	}
	
	public int getSpielerNr()
	{
		return spielerNr;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public int getX()
	{
		return x;
	}
	
	public void setDieI(int y)
	{	
		int tmp = (int) Math.round(y * zeilen / (double)hoehe);
		if(tmp == -1)
		{
			this.i = 0;
		}
		else if(tmp == zeilen)
		{
			this.i = zeilen - 1;
		}
		else
		{
			this.i = (int) Math.round(y * zeilen / (double)hoehe);
		}
	}
	
	public void setDieJ(int x)
	{
		int tmp = (int) Math.round(x * spalten / (double)breite);
		if(tmp == -1)
		{
			this.j = 0;
		}
		else if(tmp == spalten)
		{
			this.j = spalten - 1;
		}
		else 
			this.j = (int) Math.round(x * spalten / (double)breite);
	}
	
	//  i*breite/spalten =x * spalten 
	
	public int getDieI()
	{
		return i;
	}
	
	public int getDieJ()
	{
		return j;
	}
	
	public int getDieIAbrunden()
	{
		return i;
	}
	
	public int getDieJAbrunden()
	{
		return j;
	}

	public int setGetDieI(int y)
	{
		int tmp = y * zeilen / hoehe;
		if(tmp == -1)
		{
			return 0;
		}
		else if(tmp == zeilen)
		{
			return zeilen - 1;
		}
		else
		{
			return y * zeilen / hoehe;
		}
	}

	public int setGetDieJ(int x)
	{
		int tmp = x * spalten / breite;
		if(tmp == -1)
		{
			return 0;
		}
		else if(tmp == spalten)
		{
			return spalten - 1;
		}
		else 
			return x * spalten / breite;
	}
	
	// loeschen spaeter die untere methode
	public void setDieIAbrunden(int y)
	{	
		int tmp = y * zeilen / hoehe;
		if(tmp == -1)
		{
			this.iAbrunden = 0;
		}
		else if(tmp == zeilen)
		{
			this.iAbrunden = zeilen - 1;
		}
		else
		{
			this.iAbrunden = y * zeilen / hoehe;;
		}
	}
	
	public void setDieJAbrunden(int x)
	{
		int tmp = x * spalten / breite;
		if(tmp == -1)
		{
			this.jAbrunden = 0;
		}
		else if(tmp == spalten)
		{
			this.jAbrunden = spalten - 1;
		}
		else 
			this.jAbrunden = x * spalten / breite;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setLebt(boolean lebt)
	{
		this.lebt = lebt;
	}
	
	public boolean getLebt()
	{
		return lebt;
	}
	
	public void setBombenLevel(int bombenLevel)
	{
		if(bombenLevel <= 10)
		{
			this.bombenLevel = bombenLevel;
		}
	}
	
	public void setSchwellenLevel(int schwellenLevel)
	{
		this.schwellenLevel = schwellenLevel;
		setSchwellenStaerkeX(this.schwellenLevel);
		setSchwellenStaerkeY(this.schwellenLevel);
	}
	
	public void setSchwellenStaerkeX(int schwellenLevel) {
		if(schwellenLevel * breite / spalten < breite / 2)
		{
			this.schwellenStaerkeX = schwellenLevel * breite / spalten;
		}
	}
	
	public void setSchwellenStaerkeY(int schwellenLevel) {
		if(schwellenLevel * hoehe / zeilen < hoehe / 2)
		{
			this.schwellenStaerkeY = schwellenLevel * hoehe / zeilen;
		}
	}
	
	public int getSchwellenStaerkeX()
	{
		return schwellenStaerkeX;
	}

	public int getSchwellenStaerkeY()
	{
		return schwellenStaerkeY;
	}
	
	public void setGeschwindigkeitsLevelXPlusEinz()
	{
		if(this.geschwindigkeitsLevelX <= breite / spalten)
		{
			this.geschwindigkeitsLevelX += this.geschwindigkeitserhoehungBreite;
		}
	}

	public void setGeschwindigkeitsLevelYPlusEinz()
	{
		if(this.geschwindigkeitsLevelY <= hoehe / zeilen)
		{
			this.geschwindigkeitsLevelY += this.geschwindigkeitserhoehungHoehe;
		}
	}
	
	public void setBatterienLevel(int batterienLevel)
	{
		if(batterienLevel > 0)
		{
			if(batterienLevel < 4)
			{
				this.batterienLevel = batterienLevel;
			}
			else if(!einDrittelBatterieNochDrin && batterienLevel > 3)
			{
				einDrittelBatterieNochDrin = true;
			}
		}
		else
		{
			this.batterienLevel = batterienLevel;
			if(batterienLevel == 0)
			{
				setLebt(false);
			}
		}
	}
	
	public boolean getEinDrittelBatterieNochDrin()
	{
		return einDrittelBatterieNochDrin;
	}
	
	public int getBombenLevel()
	{
		return bombenLevel;
	}
	
	public int getSchwellenLevel()
	{
		return schwellenLevel;
	}
	
	public int getGeschwindigkeitsLevelX()
	{
		return geschwindigkeitsLevelX;
	}
	
	
	public int getGeschwindigkeitsLevelY()
	{
		return geschwindigkeitsLevelY;
	}
	
	public int getBatterienLevel()
	{
		return batterienLevel;
	}

	public void setColorSpieler() {
		// TODO Auto-generated method stub
		colorSpieler = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
	}
	public Color getColorSpieler()
	{
		return colorSpieler;
	}

	public BufferedImage getVorne() {
		return vorne;
	}

	public void setVorne(BufferedImage vorne) {
		this.vorne = vorne;
	}

	public void setImages(File f,int feldX, int feldY) {
		vorne = bild.loadImages(f, feldX, feldY);
	}
}
