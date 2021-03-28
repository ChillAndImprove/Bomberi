
public class Bombe {
	
	private int timerZeit;
	private int x;
	private int y;
	private boolean bombeGesetzt;
	private int hatGesetzt;
	private int schwellenStaerkeX;
	private int schwellenStaerkeY;
	private int schwellenLevelX;
	private int schwellenLevelY;
	private int breite;
	private int hoehe;
	private int zeilen;
	private int spalten;
	
	public Bombe(int breite, int hoehe, int zeilen, int spalten)
	{
		this.breite = breite;
		this.hoehe = hoehe;
		this.zeilen = zeilen;
		this.spalten = spalten;
		timerZeit = 250;
		setTimer(timerZeit);
	}
	
	public void setHatGesetzt(int hatGesetzt)
	{
		this.hatGesetzt = hatGesetzt;
	}
	
	public int getHatGesetzt()
	{
		return hatGesetzt;
	}
	
	public void setSchwellenStaerkeX(int schwellenStaerkeX)
	{
		this.schwellenStaerkeX = schwellenStaerkeX;
		setSchwellenLevelX(this.schwellenStaerkeX);
	}
	
	public void setSchwellenStaerkeY(int schwellenStaerkeY)
	{
		this.schwellenStaerkeY = schwellenStaerkeY;
		setSchwellenLevelY(this.schwellenStaerkeY);
	}
	
	public void setSchwellenLevelX(int schwellenStaerke)
	{
		schwellenLevelX = schwellenStaerke * zeilen / hoehe;
	}
	
	public void setSchwellenLevelY(int schwellenStaerke)
	{
		schwellenLevelY = schwellenStaerke * zeilen / hoehe;
	}
	
	public int getSchwellenLevelY()
	{
		return schwellenLevelY;
	}
	
	public int getSchwellenLevelX()
	{
		return schwellenLevelX;
	}
	
	public int getSchwellenStaerkeX()
	{
		return schwellenStaerkeX;
	}
	
	public int getSchwellenStaerkeY()
	{
		return schwellenStaerkeY;
	}
	
	public void resetTimer()
	{
		timerZeit = 250;
	}
	
	public void setTimer(int timerZeit)
	{
		this.timerZeit = timerZeit;
	}
	
	public int getTimer()
	{
		return timerZeit;
	}
	
	public void setXKoordinaten(int xKoordinate)
	{
		x = xKoordinate;
	}
	
	public int getXKoordinaten()
	{
		return x;
	}
	
	public void setYKoordinaten(int yKoordinate)
	{
		y = yKoordinate;
	}
	
	public int getYKoordinaten()
	{
		return y;
	}
	
	public void setExistiert(boolean bombeGesetzt)
	{
		this.bombeGesetzt = bombeGesetzt;
	}
	
	public boolean getExistiert()
	{
		return bombeGesetzt;
	}
}
