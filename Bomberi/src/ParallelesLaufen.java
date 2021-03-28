
public class ParallelesLaufen {
	public ParallelesLaufen(Fenster f)
	{
		new Thread(()->
		{
			//w
			while(true)
			{
				while(f.boolwsp1.get())
				{
					try {
						Thread.sleep(30);
						if ((f.spieler[0].getY() - f.spieler[0].getGeschwindigkeitsZahlY()) >= 0) 
						{
							if((f.spieler[0].setGetDieI(f.spieler[0].getY())) > 0 && 
							(f.grassHeuSteine[(f.spieler[0].setGetDieI(f.spieler[0].getY()))-1][f.spieler[0].setGetDieJ(f.spieler[0].getX())] instanceof Heu || 
							f.grassHeuSteine[(f.spieler[0].setGetDieI(f.spieler[0].getY()))-1][f.spieler[0].setGetDieJ(f.spieler[0].getX())] instanceof Steine || 
							f.grassHeuSteine[(f.spieler[0].setGetDieI(f.spieler[0].getY()))-1][f.spieler[0].setGetDieJ(f.spieler[0].getX() + f.breite / f.spalten-1)] instanceof Heu 
							|| f.grassHeuSteine[(f.spieler[0].setGetDieI(f.spieler[0].getY()))-1][f.spieler[0].setGetDieJ(f.spieler[0].getX() + f.breite / f.spalten-1)] instanceof Steine
							|| f.bombe[(f.spieler[0].setGetDieI(f.spieler[0].getY()))-1][f.spieler[0].setGetDieJ(f.spieler[0].getX() + f.breite / f.spalten-1)] instanceof Bombe
							&& ((Bombe) f.bombe[(f.spieler[0].setGetDieI(f.spieler[0].getY()))-1][f.spieler[0].setGetDieJ(f.spieler[0].getX() + f.breite / f.spalten-1)]).getExistiert()))
							{
								if(!f.collision('w', 0))
								{
									//Hier kommt Kollisioncheck
									f.spieler[0].setY(f.spieler[0].getY() - f.spieler[0].getGeschwindigkeitsZahlY());
									f.spieler[0].setDieI(f.spieler[0].getY());	
								}
							}
							else
							{
								f.spieler[0].setY(f.spieler[0].getY() - f.spieler[0].getGeschwindigkeitsZahlY());
								f.spieler[0].setDieI(f.spieler[0].getY());
							}
						}
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}).start();

	}
}
