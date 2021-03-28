
public class Boni {
	
	private boolean existiert;
	private String bonus;
	
	public void setExistiert(boolean existiert)
	{
		this.existiert = existiert;
	}
	
	public boolean getExistiert()
	{
		return existiert;
	}
	
	public void setBoni()
	{
		int r = (int)((Math.random() * 10) + 1);
		if(r < 4)
		{
			setBonus("Bombe");
		}
		else if(r > 3 && r < 6)
		{
			setBonus("Schwelle");
		}
		else if(r > 5 && r < 8)
		{
			setBonus("Batterie");
		}
		else if(r > 7)
		{
			setBonus("Geschwindigkeit");
		}
	}
	
	public void setBonus(String bonus)
	{
		this.bonus = bonus;
	}
	
	public String getBonus()
	{
		return bonus;
	}
}
