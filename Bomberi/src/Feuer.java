
public class Feuer 
{
	
	private int timerZeit; 
	private boolean existiert;
	
	public Feuer()
	{
		timerZeit = 100;
		setTimer(timerZeit);
	}
	
	public void setTimer(int timerZeit)
	{
		this.timerZeit = timerZeit;
	}
	
	public int getTimer()
	{
		return timerZeit;
	}
	
	public void setExistiert(boolean existiert)
	{
		this.existiert = existiert;
	}
	
	public boolean getExistiert()
	{
		return existiert;
	}
}
