
public class Grass {
	

	private Heu[][] heu;
	private Steine[][] steine;
	private Object[][] grassHeuSteine;
	
	public Grass(Heu[][] heu, Steine[][] steine, int zeilen, int spalten)
	{
		this.heu = heu;
		this.steine = steine;
		grassHeuSteine = new Object[zeilen][spalten];
		setObjectInitialisieren();
		setGrassArr();
//		print();
	}
	
	public void setObjectInitialisieren()
	{
		for (int i = 0; i < grassHeuSteine.length; i++) {
			for (int j = 0; j < grassHeuSteine[i].length; j++) {
				grassHeuSteine[i][j] = new Object();
			}
		}
	}
	
	public void setGrassArr()
	{
		for (int i = 0; i < grassHeuSteine.length; i++) 
		{
			for (int j = 0; j < grassHeuSteine[i].length; j++) 
			{
				if(heu[i][j].getExistiert())
				{
					grassHeuSteine[i][j] = heu[i][j];	
				}
				if(steine[i][j].getExistiert())
				{
					grassHeuSteine[i][j] = steine[i][j];	
				}
			}
		}
	}
	
//	public void print()
//	{
//		for (int i = 0; i < grassHeuSteine.length; i++) 
//		{
//			for (int j = 0; j < grassHeuSteine[i].length; j++) 
//			{
//				if(grassHeuSteine[i][j] instanceof Steine)
//				{
//					if(((Steine) grassHeuSteine[i][j]).getExistiert())
//					{
//						System.out.print("X");
//					}
//				
//				}
//					else if(grassHeuSteine[i][j] instanceof Heu)
//				{
//					if(((Heu) grassHeuSteine[i][j]).getExistiert())
//					{
//						System.out.print("O");
//					}	
//				}
//			
//			}
//			System.out.println();
//		}
//	}
	
	public Object[][] getGrassArr()
	{
		return grassHeuSteine;
	}
}
