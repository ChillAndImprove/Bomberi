import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Set;
import java.util.Iterator;
public class Fenster extends JFrame {

	Object[][] grassHeuSteine;
	Boni[][] boni;
	Spieler[] spieler;
	Object[][] bombe;
	int zeilen;
	int spalten;
	int breite;
	int hoehe;
	Controller controller;
	Fenster f;
	volatile AtomicBoolean boolwsp1 = new AtomicBoolean(false);
	volatile AtomicBoolean boolasp1= new AtomicBoolean(false);
	volatile AtomicBoolean boolssp1= new AtomicBoolean(false);
	volatile AtomicBoolean booldsp1= new AtomicBoolean(false);
	volatile AtomicBoolean boolrightsp2= new AtomicBoolean(false);
	volatile AtomicBoolean boolleftsp2= new AtomicBoolean(false);
	volatile AtomicBoolean booldownsp2= new AtomicBoolean(false);
	volatile AtomicBoolean boolupsp2= new AtomicBoolean(false);
	int spielerbreite;
	int spielerhöhe;
	int threadgeschwindigkeit = 100;
	public Fenster(Controller controller, Object[][] grassHeuSteine, Boni[][] boni, Spieler[] spieler, Object[][] bombe,
			int zeilen, int spalten, int breite, int hoehe) {
		f = this;
		this.controller = controller;
		this.grassHeuSteine = grassHeuSteine;
		this.boni = boni;
		this.spieler = spieler;
		this.bombe = bombe;
		this.zeilen = zeilen;
		this.spalten = spalten;
		this.breite = breite;
		this.hoehe = hoehe;
		spielerbreite = this.breite/ this.spalten;
		spielerhöhe = this.hoehe/ this.zeilen;
		
		new Thread(()->
		{
			//w
			while(true)
			{
				while(boolwsp1.get())
				{
					try {
						Thread.sleep(threadgeschwindigkeit);
						if ((spieler[0].getY() - spieler[0].getGeschwindigkeitsZahlY()) >= 0) 
						{
							if((spieler[0].setGetDieI(spieler[0].getY())) > 0 && 
							(grassHeuSteine[(spieler[0].setGetDieI(spieler[0].getY()))-1][spieler[0].setGetDieJ(spieler[0].getX())] instanceof Heu || 
							grassHeuSteine[(spieler[0].setGetDieI(spieler[0].getY()))-1][spieler[0].setGetDieJ(spieler[0].getX())] instanceof Steine || 
							grassHeuSteine[(spieler[0].setGetDieI(spieler[0].getY()))-1][spieler[0].setGetDieJ(spieler[0].getX() + breite / spalten-1)] instanceof Heu 
							|| grassHeuSteine[(spieler[0].setGetDieI(spieler[0].getY()))-1][spieler[0].setGetDieJ(spieler[0].getX() + breite / spalten-1)] instanceof Steine
							|| bombe[(spieler[0].setGetDieI(spieler[0].getY()))-1][spieler[0].setGetDieJ(spieler[0].getX() + breite / spalten-1)] instanceof Bombe
							&& ((Bombe) bombe[(spieler[0].setGetDieI(spieler[0].getY()))-1][spieler[0].setGetDieJ(spieler[0].getX() + breite / spalten-1)]).getExistiert()))
							{
								if(!collision('w', 0))
								{
									//Hier kommt Kollisioncheck
									spieler[0].setY(spieler[0].getY() - spieler[0].getGeschwindigkeitsZahlY());
									spieler[0].setDieI(spieler[0].getY());	
								}
							}
							else
							{
								spieler[0].setY(spieler[0].getY() - spieler[0].getGeschwindigkeitsZahlY());
								spieler[0].setDieI(spieler[0].getY());
							}
						}
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}).start();
		new Thread(()->
		{
			//a
			while(true)
			{
				while(boolasp1.get())
				{
					try {
						Thread.sleep(threadgeschwindigkeit);
						if ((spieler[0].getX() - spieler[0].getGeschwindigkeitsZahlX()) >= 0) 
						{
							if((spieler[0].setGetDieJ(spieler[0].getX())) > 0 && 
							(grassHeuSteine[spieler[0].setGetDieI(spieler[0].getY())][(spieler[0].setGetDieJ(spieler[0].getX()))-1] instanceof Heu || 
							grassHeuSteine[spieler[0].setGetDieI(spieler[0].getY())][(spieler[0].setGetDieJ(spieler[0].getX()))-1] instanceof Steine || 
							grassHeuSteine[spieler[0].setGetDieI(spieler[0].getY() + hoehe / zeilen-1)][(spieler[0].setGetDieJ(spieler[0].getX()))-1] instanceof Heu 
							|| grassHeuSteine[spieler[0].setGetDieI(spieler[0].getY() + hoehe / zeilen-1)][(spieler[0].setGetDieJ(spieler[0].getX()))-1] instanceof Steine
							|| bombe[spieler[0].setGetDieI(spieler[0].getY() + hoehe / zeilen-1)][(spieler[0].setGetDieJ(spieler[0].getX()))-1] instanceof Bombe
							&& ((Bombe) bombe[spieler[0].setGetDieI(spieler[0].getY() + hoehe / zeilen-1)][(spieler[0].setGetDieJ(spieler[0].getX()))-1]).getExistiert()))
							{
								if(!collision('a', 0))
								{
									//Hier kommt Kollisioncheck
									spieler[0].setX(spieler[0].getX() - spieler[0].getGeschwindigkeitsZahlX());
									spieler[0].setDieJ(spieler[0].getX());
								}
							}
							else
							{
								spieler[0].setX(spieler[0].getX() - spieler[0].getGeschwindigkeitsZahlX());
								spieler[0].setDieJ(spieler[0].getX());
							}
						}
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}).start();
		new Thread(()->
		{
			//s
			while(true)
			{
				while(boolssp1.get())
				{
					try {
						Thread.sleep(threadgeschwindigkeit);
						if ((spieler[0].getY() + spieler[0].getGeschwindigkeitsZahlY()) <= hoehe - hoehe / zeilen) 
						{
							if((spieler[0].setGetDieI(spieler[0].getY())) < grassHeuSteine.length && 
							(grassHeuSteine[(spieler[0].setGetDieI(spieler[0].getY()))+1][spieler[0].setGetDieJ(spieler[0].getX())] instanceof Heu || 
							grassHeuSteine[(spieler[0].setGetDieI(spieler[0].getY()))+1][spieler[0].setGetDieJ(spieler[0].getX())] instanceof Steine || 
							grassHeuSteine[(spieler[0].setGetDieI(spieler[0].getY()))+1][spieler[0].setGetDieJ(spieler[0].getX() + breite / spalten-1)] instanceof Heu 
							|| grassHeuSteine[(spieler[0].setGetDieI(spieler[0].getY()))+1][spieler[0].setGetDieJ(spieler[0].getX() + breite / spalten-1)] instanceof Steine 
							|| bombe[(spieler[0].setGetDieI(spieler[0].getY()))+1][spieler[0].setGetDieJ(spieler[0].getX() + breite / spalten-1)] instanceof Bombe
							&& ((Bombe) bombe[(spieler[0].setGetDieI(spieler[0].getY()))+1][spieler[0].setGetDieJ(spieler[0].getX() + breite / spalten-1)]).getExistiert()))
							{
								if(!collision('s', 0))
								{
									//Hier kommt Kollisioncheck
									spieler[0].setY(spieler[0].getY() + spieler[0].getGeschwindigkeitsZahlY());
									spieler[0].setDieI(spieler[0].getY());
								}
							}
							else
							{
								spieler[0].setY(spieler[0].getY() + spieler[0].getGeschwindigkeitsZahlY());
								spieler[0].setDieI(spieler[0].getY());
							}
						}
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}).start();
		new Thread(()->
		{
			//d
			while(true)
			{
				while(booldsp1.get())
				{
					try {
						Thread.sleep(threadgeschwindigkeit);
						if (spieler[0].getX()+ spieler[0].getGeschwindigkeitsZahlX() <= breite - breite / spalten) 
						{
							if((spieler[0].setGetDieJ(spieler[0].getX())) < grassHeuSteine[0].length && 
							(grassHeuSteine[spieler[0].setGetDieI(spieler[0].getY())][(spieler[0].setGetDieJ(spieler[0].getX()))+1] instanceof Heu || 
							grassHeuSteine[spieler[0].setGetDieI(spieler[0].getY())][(spieler[0].setGetDieJ(spieler[0].getX()))+1] instanceof Steine || 
							grassHeuSteine[spieler[0].setGetDieI(spieler[0].getY() + hoehe / zeilen-1)][(spieler[0].setGetDieJ(spieler[0].getX()))+1] instanceof Heu 
							|| grassHeuSteine[spieler[0].setGetDieI(spieler[0].getY() + hoehe / zeilen-1)][(spieler[0].setGetDieJ(spieler[0].getX()))+1] instanceof Steine
							|| bombe[spieler[0].setGetDieI(spieler[0].getY() + hoehe / zeilen-1)][(spieler[0].setGetDieJ(spieler[0].getX()))+1] instanceof Bombe
							&& ((Bombe) bombe[spieler[0].setGetDieI(spieler[0].getY() + hoehe / zeilen-1)][(spieler[0].setGetDieJ(spieler[0].getX()))+1]).getExistiert()))
							{
								if(!collision('d', 0))
								{
									//Hier kommt Kollisioncheck
									spieler[0].setX(spieler[0].getX() + spieler[0].getGeschwindigkeitsZahlX());
									spieler[0].setDieJ(spieler[0].getX());
								}
							}
							else
							{
								spieler[0].setX(spieler[0].getX() + spieler[0].getGeschwindigkeitsZahlX());
								spieler[0].setDieJ(spieler[0].getX());
							}
						}
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}).start();
		new Thread(()->
		{
			//unten
			while(true)
			{
				while(booldownsp2.get())
				{
					try {
						Thread.sleep(threadgeschwindigkeit);
						if ((spieler[1].getY() + spieler[1].getGeschwindigkeitsZahlY()) <= hoehe - hoehe / zeilen) 
						{
							if((spieler[1].setGetDieI(spieler[1].getY())) < grassHeuSteine.length && 
							(grassHeuSteine[(spieler[1].setGetDieI(spieler[1].getY()))+1][spieler[1].setGetDieJ(spieler[1].getX())] instanceof Heu || 
							grassHeuSteine[(spieler[1].setGetDieI(spieler[1].getY()))+1][spieler[1].setGetDieJ(spieler[1].getX())] instanceof Steine || 
							grassHeuSteine[(spieler[1].setGetDieI(spieler[1].getY()))+1][spieler[1].setGetDieJ(spieler[1].getX() + breite / spalten-1)] instanceof Heu 
							|| grassHeuSteine[(spieler[1].setGetDieI(spieler[1].getY()))+1][spieler[1].setGetDieJ(spieler[1].getX() + breite / spalten-1)] instanceof Steine 
							|| bombe[(spieler[1].setGetDieI(spieler[1].getY()))+1][spieler[1].setGetDieJ(spieler[1].getX() + breite / spalten-1)] instanceof Bombe
							&& ((Bombe) bombe[(spieler[1].setGetDieI(spieler[1].getY()))+1][spieler[1].setGetDieJ(spieler[1].getX() + breite / spalten-1)]).getExistiert()))
							{
								if(!collision('s', 1))
								{
									//Hier kommt Kollisioncheck
									spieler[1].setY(spieler[1].getY() + spieler[1].getGeschwindigkeitsZahlY());
									spieler[1].setDieI(spieler[1].getY());
								}
							}
							else
							{
								spieler[1].setY(spieler[1].getY() + spieler[1].getGeschwindigkeitsZahlY());
								spieler[1].setDieI(spieler[1].getY());
							}
						}
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}).start();
		new Thread(()->
		{
			//oben
			while(true)
			{
				while(boolupsp2.get())
				{
					try {
						Thread.sleep(threadgeschwindigkeit);
						if ((spieler[1].getY() - spieler[1].getGeschwindigkeitsZahlY()) >= 0) 
						{
							if((spieler[1].setGetDieI(spieler[1].getY())) > 0 && 
							(grassHeuSteine[(spieler[1].setGetDieI(spieler[1].getY()))-1][spieler[1].setGetDieJ(spieler[1].getX())] instanceof Heu || 
							grassHeuSteine[(spieler[1].setGetDieI(spieler[1].getY()))-1][spieler[1].setGetDieJ(spieler[1].getX())] instanceof Steine || 
							grassHeuSteine[(spieler[1].setGetDieI(spieler[1].getY()))-1][spieler[1].setGetDieJ(spieler[1].getX() + breite / spalten-1)] instanceof Heu 
							|| grassHeuSteine[(spieler[1].setGetDieI(spieler[1].getY()))-1][spieler[1].setGetDieJ(spieler[1].getX() + breite / spalten-1)] instanceof Steine
							|| bombe[(spieler[1].setGetDieI(spieler[1].getY()))-1][spieler[1].setGetDieJ(spieler[1].getX() + breite / spalten-1)] instanceof Bombe
							&& ((Bombe) bombe[(spieler[1].setGetDieI(spieler[1].getY()))-1][spieler[1].setGetDieJ(spieler[1].getX() + breite / spalten-1)]).getExistiert()))
							{
								if(!collision('w', 1))
								{
									//Hier kommt Kollisioncheck
									spieler[1].setY(spieler[1].getY() - spieler[1].getGeschwindigkeitsZahlY());
									spieler[1].setDieI(spieler[1].getY());	
								}
							}
							else
							{
								spieler[1].setY(spieler[1].getY() - spieler[1].getGeschwindigkeitsZahlY());
								spieler[1].setDieI(spieler[1].getY());
							}
						}
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}).start();
		new Thread(()->
		{
			//links
			while(true)
			{
				while(boolleftsp2.get())
				{
					try {
						Thread.sleep(threadgeschwindigkeit);
						if ((spieler[1].getX() - spieler[1].getGeschwindigkeitsZahlX()) >= 0) 
						{
							if((spieler[1].setGetDieJ(spieler[1].getX())) > 0 && 
							(grassHeuSteine[spieler[1].setGetDieI(spieler[1].getY())][(spieler[1].setGetDieJ(spieler[1].getX()))-1] instanceof Heu || 
							grassHeuSteine[spieler[1].setGetDieI(spieler[1].getY())][(spieler[1].setGetDieJ(spieler[1].getX()))-1] instanceof Steine || 
							grassHeuSteine[spieler[1].setGetDieI(spieler[1].getY() + hoehe / zeilen-1)][(spieler[1].setGetDieJ(spieler[1].getX()))-1] instanceof Heu 
							|| grassHeuSteine[spieler[1].setGetDieI(spieler[1].getY() + hoehe / zeilen-1)][(spieler[1].setGetDieJ(spieler[1].getX()))-1] instanceof Steine
							|| bombe[spieler[1].setGetDieI(spieler[1].getY() + hoehe / zeilen-1)][(spieler[1].setGetDieJ(spieler[1].getX()))-1] instanceof Bombe
							&& ((Bombe) bombe[spieler[1].setGetDieI(spieler[1].getY() + hoehe / zeilen-1)][(spieler[1].setGetDieJ(spieler[1].getX()))-1]).getExistiert()))
							{
								if(!collision('a', 1))
								{
									//Hier kommt Kollisioncheck
									spieler[1].setX(spieler[1].getX() - spieler[1].getGeschwindigkeitsZahlX());
									spieler[1].setDieJ(spieler[1].getX());
								}
							}
							else
							{
								spieler[1].setX(spieler[1].getX() - spieler[1].getGeschwindigkeitsZahlX());
								spieler[1].setDieJ(spieler[1].getX());
							}
						}
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}).start();
		new Thread(()->
		{
			//rechts
			while(true)
			{
				while(boolrightsp2.get())
				{
					try {
						Thread.sleep(threadgeschwindigkeit);
						if (spieler[1].getX()+ spieler[1].getGeschwindigkeitsZahlX() <= breite - breite / spalten) 
						{
							if((spieler[1].setGetDieJ(spieler[1].getX())) < grassHeuSteine[0].length && 
							(grassHeuSteine[spieler[1].setGetDieI(spieler[1].getY())][(spieler[1].setGetDieJ(spieler[1].getX()))+1] instanceof Heu || 
							grassHeuSteine[spieler[1].setGetDieI(spieler[1].getY())][(spieler[1].setGetDieJ(spieler[1].getX()))+1] instanceof Steine || 
							grassHeuSteine[spieler[1].setGetDieI(spieler[1].getY() + hoehe / zeilen-1)][(spieler[1].setGetDieJ(spieler[1].getX()))+1] instanceof Heu 
							|| grassHeuSteine[spieler[1].setGetDieI(spieler[1].getY() + hoehe / zeilen-1)][(spieler[1].setGetDieJ(spieler[1].getX()))+1] instanceof Steine
							|| bombe[spieler[1].setGetDieI(spieler[1].getY() + hoehe / zeilen-1)][(spieler[1].setGetDieJ(spieler[1].getX()))+1] instanceof Bombe
							&& ((Bombe) bombe[spieler[1].setGetDieI(spieler[1].getY() + hoehe / zeilen-1)][(spieler[1].setGetDieJ(spieler[1].getX()))+1]).getExistiert()))
							{
								if(!collision('d', 1))
								{
									//Hier kommt Kollisioncheck
									spieler[1].setX(spieler[1].getX() + spieler[1].getGeschwindigkeitsZahlX());
									spieler[1].setDieJ(spieler[1].getX());
								}
							}
							else
							{
								spieler[1].setX(spieler[1].getX() + spieler[1].getGeschwindigkeitsZahlX());
								spieler[1].setDieJ(spieler[1].getX());
							}
						}
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}).start();
		
		// TODO hier weiter machen!!!
		// die Bomben muessen ueberprueft werden auf schwellenstäerke und
		// die Spieler, falls die zu nah stehen, wird das leben auf false
		// gesetzt
		JPanel p = new JPanel() {
			{
				setPreferredSize(new Dimension(breite, hoehe));
			}

			@Override
			public void paintComponent(Graphics g) {
				f.setTitle("Spieler1:" + spieler[0].getBatterienLevel() + 
//				" " + spieler[0].getBombenLevel() + " CYAN:"
//						+ spieler[0].getGeschwindigkeitsZahlX() + " " + spieler[0].getGeschwindigkeitsZahlY() + " "
//						+ spieler[0].getSchwellenStaerkeX() + " " + " " + spieler[0].getSchwellenStaerkeY() + " "
//						+ spieler[0].getEinDrittelBatterieNochDrin()
						" Spieler2:" + spieler[1].getBatterienLevel());
				
				// Spieler
				for(int spielerCounter = 0; spielerCounter < spieler.length; spielerCounter++)
				{
					if(spieler[spielerCounter].getLebt()) {
						
						// Spieler wird gezeichnet
						g.setColor(spieler[spielerCounter].getColorSpieler());
						g.fillOval(spieler[spielerCounter].getX(), spieler[spielerCounter].getY(), breite / spalten, hoehe / zeilen);
//						g.drawImage(spieler[spielerCounter].getVorne(),spieler[spielerCounter].getX(), spieler[spielerCounter].getY(),this);
						
						// pruefung ob der Spieler bei dem Item ist und ob ueberhaupt an der Stelle auch ein 
						// Boni Item ist
						if (grassHeuSteine[spieler[spielerCounter].getDieI()][spieler[spielerCounter].getDieJ()] instanceof Boni) {
							if (((Boni) grassHeuSteine[spieler[spielerCounter].getDieI()][spieler[spielerCounter]
									.getDieJ()]).getBonus().equals("Schwelle")) {
								spieler[spielerCounter].setSchwellenLevel(spieler[spielerCounter].getSchwellenLevel() + 1);
							} else if (((Boni) grassHeuSteine[spieler[spielerCounter].getDieI()][spieler[spielerCounter]
									.getDieJ()]).getBonus().equals("Geschwindigkeit")) {
								spieler[spielerCounter].setGeschwindigkeitsZahlX(spieler[spielerCounter].geschwindigkeitsZahlX + 1);
								spieler[spielerCounter].setGeschwindigkeitsZahlY(spieler[spielerCounter].geschwindigkeitsZahlY + 1);
								spieler[spielerCounter].setY(spieler[spielerCounter].getY());
								spieler[spielerCounter].setX(spieler[spielerCounter].getX());
								spieler[spielerCounter].setX(spieler[spielerCounter].getDieJ()*breite/spalten);
								spieler[spielerCounter].setY(spieler[spielerCounter].getDieI()*hoehe/zeilen);
								
							} else if (((Boni) grassHeuSteine[spieler[spielerCounter].getDieI()][spieler[spielerCounter]
									.getDieJ()]).getBonus().equals("Batterie")) {
								spieler[spielerCounter].setBatterienLevel(spieler[spielerCounter].getBatterienLevel() + 1);
							} else if (((Boni) grassHeuSteine[spieler[spielerCounter].getDieI()][spieler[spielerCounter]
									.getDieJ()]).getBonus().equals("Bombe")) {
								spieler[spielerCounter].setBombenLevel(spieler[spielerCounter].getBombenLevel() + 1);
							}
							grassHeuSteine[spieler[spielerCounter].getDieI()][spieler[spielerCounter].getDieJ()] = new Object();
						}
					}
				}
				// zweifache forschleife iteriert ueber grassHeuSteine Array und es werden gezeichnet
				// -Feur
				// -Bombe
				// -Boni
				// -Heu
				// -Steine
				for (int i = 0; i < grassHeuSteine.length; i++) {
					for (int j = 0; j < grassHeuSteine[i].length; j++) {
						if (bombe[i][j] instanceof Feuer) {
							if (((Feuer) bombe[i][j]).getExistiert()) {
								for (int k = 0; k < spieler.length; k++) {
									if (spieler[k].getDieI() == i && spieler[k].getDieJ() == j
											&& spieler[k].getUnbesiegbar() == false) {
										spieler[k].setBatterienLevel(spieler[k].getBatterienLevel() - 1);
										spieler[k].setUnbesiegbar(true);
										spieler[k].setIchWurdeVerbrantVom(i, j);
									}
								}
								g.setColor(Color.YELLOW);
								g.fillRect(j * breite / spalten, i * hoehe / zeilen, breite / spalten, hoehe / zeilen);
								((Feuer) bombe[i][j]).setTimer(((Feuer) bombe[i][j]).getTimer() - 1);
								if (((Feuer) bombe[i][j]).getTimer() <= 0) {
									((Feuer) bombe[i][j]).setExistiert(false);
									bombe[i][j] = new Bombe(breite, hoehe, zeilen, spalten);
									for (int l = 0; l < spieler.length; l++) {
										if (spieler[l].getIchWurdeVerbrantVomI() == i
												&& spieler[l].getIchWurdeVerbrantVomJ() == j) {
											spieler[l].setUnbesiegbar(false);
										}
									}
								}
							}
						}
				
						if (bombe[i][j] instanceof Bombe) {
							if (((Bombe) bombe[i][j]).getExistiert()) {
								g.setColor(Color.BLUE);
								g.fillOval(j * breite / spalten, i * hoehe / zeilen, breite / spalten, hoehe / zeilen);
								dieBombeExpludiert(i, j, ((Bombe) bombe[i][j]).getHatGesetzt());
							}
						}
						if (grassHeuSteine[i][j] instanceof Boni) {
							if (((Boni) grassHeuSteine[i][j]).getExistiert()) {
								if (((Boni) grassHeuSteine[i][j]).getBonus().equals("Schwelle")) {
									g.setColor(Color.MAGENTA);
								} else if (((Boni) grassHeuSteine[i][j]).getBonus().equals("Geschwindigkeit")) {
									g.setColor(Color.CYAN);
								} else if (((Boni) grassHeuSteine[i][j]).getBonus().equals("Batterie")) {
									g.setColor(Color.ORANGE);
								} else if (((Boni) grassHeuSteine[i][j]).getBonus().equals("Bombe")) {
									g.setColor(Color.PINK);
								}
								g.fillRect(j * breite / spalten, i * hoehe / zeilen, breite / spalten, hoehe / zeilen);
							}
						}
						if (grassHeuSteine[i][j] instanceof Heu) {
							if (((Heu) grassHeuSteine[i][j]).getExistiert()) {
								g.setColor(Color.GREEN);
								g.fillRect(j * breite / spalten, i * hoehe / zeilen, breite / spalten, hoehe / zeilen);
							}
						} else if (grassHeuSteine[i][j] instanceof Steine) {
							if (((Steine) grassHeuSteine[i][j]).getExistiert()) {
								g.setColor(Color.BLACK);
								g.fillRect(j * breite / spalten, i * hoehe / zeilen, breite / spalten, hoehe / zeilen);
							}
						}
					}
				}
			}

			private void dieBombeExpludiert(int i, int j, int l) {
				// TODO Auto-generated method stub
				((Bombe) bombe[i][j]).setTimer(((Bombe) bombe[i][j]).getTimer() - 1);
				if (((Bombe) bombe[i][j]).getTimer() <= 0) {
					spieler[l].setBombenLevel(spieler[l].getBombenLevel() + 1);
					((Bombe) bombe[i][j]).setExistiert(false);
					feuerLegen(i, j);
				}
			}

			private void feuerLegen(int i, int j) {
				int schwelleX = ((Bombe) bombe[i][j]).getSchwellenLevelX();
				int schwelleY = ((Bombe) bombe[i][j]).getSchwellenLevelY();
				bombe[i][j] = new Feuer();
				((Feuer) bombe[i][j]).setExistiert(true);
				int oben = i;
				int links = j;
				int rechts = j;
				int unten = i;
				boolean obenb = true;
				boolean linksb = true;
				boolean rechtsb = true;
				boolean untenb = true;
				for (int k = 0; k < schwelleY + 1; k++) {
					if (oben < 0)
						obenb = false;
					else
						obenb = check(oben, j);
					if (unten > grassHeuSteine.length - 1)
						untenb = false;
					else
						untenb = check(unten, j);

					if (obenb && oben >= 0 && oben != i) {
						if (bombe[oben][j] instanceof Bombe) {
							if (!((Bombe) bombe[oben][j]).getExistiert()) {
								bombe[oben][j] = new Feuer();
								((Feuer) bombe[oben][j]).setExistiert(true);
								oben--;
							} else if (obenb && oben >= 0)
								oben--;
						}
					} else if (obenb && oben >= 0)
						oben--;

					if (untenb && unten < grassHeuSteine.length && unten != i) {
						if (bombe[unten][j] instanceof Bombe) {
							if (!((Bombe) bombe[unten][j]).getExistiert()) {
								bombe[unten][j] = new Feuer();
								((Feuer) bombe[unten][j]).setExistiert(true);
								unten++;
							} else if (untenb && unten < grassHeuSteine.length)
								unten++;
						}
					} else if (untenb && unten < grassHeuSteine.length)
						unten++;
				}

				for (int k = 0; k < schwelleX + 1; k++) {
					if (links < 0)
						linksb = false;
					else
						linksb = check(i, links);
					if (rechts > grassHeuSteine[0].length - 1)
						rechtsb = false;
					else
						rechtsb = check(i, rechts);

					if (linksb && links >= 0 && links != j) {
						if (bombe[i][links] instanceof Bombe) {
							if (!((Bombe) bombe[i][links]).getExistiert()) {
								bombe[i][links] = new Feuer();
								((Feuer) bombe[i][links]).setExistiert(true);
								links--;
							} else if (linksb && links >= 0)
								links--;
						}
					} else if (linksb && links >= 0)
						links--;

					if (rechtsb && rechts < grassHeuSteine[0].length && rechts != j) {
						if (bombe[i][rechts] instanceof Bombe) {
							if (!((Bombe) bombe[i][rechts]).getExistiert()) {
								bombe[i][rechts] = new Feuer();
								((Feuer) bombe[i][rechts]).setExistiert(true);
								rechts++;
							} else if (rechtsb && rechts < grassHeuSteine[0].length)
								rechts++;
						}
					} else if (rechtsb && rechts < grassHeuSteine[0].length)
						rechts++;
				}
			}

			private boolean check(int i, int j) {
				boolean b = true;
				// if(bombe[i][j] instanceof Bombe)
				// {
				// if(((Bombe) bombe[i][j]).getExistiert())
				// {
				// b = false;
				// }
				// }
				if (grassHeuSteine[i][j] instanceof Steine) {
					if (((Steine) grassHeuSteine[i][j]).getExistiert())
						b = false;
				} else if (grassHeuSteine[i][j] instanceof Heu) {
					if (((Heu) grassHeuSteine[i][j]).getExistiert())
						if (boni[i][j].getExistiert()) {
							((Heu) grassHeuSteine[i][j]).setExistiert(false);
							grassHeuSteine[i][j] = boni[i][j];
							bombe[i][j] = new Feuer();
							((Feuer) bombe[i][j]).setExistiert(true);
						} else {
							((Heu) grassHeuSteine[i][j]).setExistiert(false);
							grassHeuSteine[i][j] = new Object();
						}
					b = false;
				}
				return b;
			}
		};
		this.addKeyListener(new KeyAdapter() {
			@Override
			public synchronized void keyPressed(KeyEvent e) {
				
				// 0-ter Spieler
				 if (e.getKeyChar() == 'q' || e.getKeyChar() == 'й') {
					if (spieler[0].getBombenLevel() > 0 && spieler[0].getLebt()) {
						if (!((Bombe) bombe[spieler[0].getDieI()][spieler[0].getDieJ()]).getExistiert()
								&& bombe[spieler[0].getDieI()][spieler[0].getDieJ()] instanceof Bombe) {
							((Bombe) bombe[spieler[0].getDieI()][spieler[0].getDieJ()]).resetTimer();
							((Bombe) bombe[spieler[0].getDieI()][spieler[0].getDieJ()])
									.setSchwellenStaerkeX(spieler[0].getSchwellenStaerkeX());
							((Bombe) bombe[spieler[0].getDieI()][spieler[0].getDieJ()])
									.setSchwellenStaerkeY(spieler[0].getSchwellenStaerkeY());
							((Bombe) bombe[spieler[0].getDieI()][spieler[0].getDieJ()])
									.setHatGesetzt(spieler[0].getSpielerNr());
							((Bombe) bombe[spieler[0].getDieI()][spieler[0].getDieJ()]).setExistiert(true);
							spieler[0].setBombenLevel(spieler[0].getBombenLevel() - 1);
							p.repaint();
						}
					}
				} else if (!boolwsp1.get() && e.getKeyChar() == 'w' || e.getKeyChar() == 'ц') 
				{
//					if ((spieler[0].getY() - spieler[0].getGeschwindigkeitsZahlY()) >= 0) 
//					{
//						if((spieler[0].setGetDieI(spieler[0].getY())) > 0 && 
//						(grassHeuSteine[(spieler[0].setGetDieI(spieler[0].getY()))-1][spieler[0].setGetDieJ(spieler[0].getX())] instanceof Heu || 
//						grassHeuSteine[(spieler[0].setGetDieI(spieler[0].getY()))-1][spieler[0].setGetDieJ(spieler[0].getX())] instanceof Steine || 
//						grassHeuSteine[(spieler[0].setGetDieI(spieler[0].getY()))-1][spieler[0].setGetDieJ(spieler[0].getX() + breite / spalten-1)] instanceof Heu 
//						|| grassHeuSteine[(spieler[0].setGetDieI(spieler[0].getY()))-1][spieler[0].setGetDieJ(spieler[0].getX() + breite / spalten-1)] instanceof Steine
//						|| bombe[(spieler[0].setGetDieI(spieler[0].getY()))-1][spieler[0].setGetDieJ(spieler[0].getX() + breite / spalten-1)] instanceof Bombe
//						&& ((Bombe) bombe[(spieler[0].setGetDieI(spieler[0].getY()))-1][spieler[0].setGetDieJ(spieler[0].getX() + breite / spalten-1)]).getExistiert()))
//						{
//							if(!collision('w', 0))
//							{
//								//Hier kommt Kollisioncheck
//								spieler[0].setY(spieler[0].getY() - spieler[0].getGeschwindigkeitsZahlY());
//								spieler[0].setDieI(spieler[0].getY());	
//							}
//						}
//						else
//						{
//							spieler[0].setY(spieler[0].getY() - spieler[0].getGeschwindigkeitsZahlY());
//							spieler[0].setDieI(spieler[0].getY());
//						}
						boolwsp1.set(true);
//					}
					//w thread boolwsp1
					p.repaint();
				}
				if (!booldsp1.get() && e.getKeyChar() == 'd' || e.getKeyChar() == 'в') 
				{
//					if (spieler[0].getX()+ spieler[0].getGeschwindigkeitsZahlX() <= breite - breite / spalten) 
//					{
//						if((spieler[0].setGetDieJ(spieler[0].getX())) < grassHeuSteine[0].length && 
//						(grassHeuSteine[spieler[0].setGetDieI(spieler[0].getY())][(spieler[0].setGetDieJ(spieler[0].getX()))+1] instanceof Heu || 
//						grassHeuSteine[spieler[0].setGetDieI(spieler[0].getY())][(spieler[0].setGetDieJ(spieler[0].getX()))+1] instanceof Steine || 
//						grassHeuSteine[spieler[0].setGetDieI(spieler[0].getY() + hoehe / zeilen-1)][(spieler[0].setGetDieJ(spieler[0].getX()))+1] instanceof Heu 
//						|| grassHeuSteine[spieler[0].setGetDieI(spieler[0].getY() + hoehe / zeilen-1)][(spieler[0].setGetDieJ(spieler[0].getX()))+1] instanceof Steine
//						|| bombe[spieler[0].setGetDieI(spieler[0].getY() + hoehe / zeilen-1)][(spieler[0].setGetDieJ(spieler[0].getX()))+1] instanceof Bombe
//						&& ((Bombe) bombe[spieler[0].setGetDieI(spieler[0].getY() + hoehe / zeilen-1)][(spieler[0].setGetDieJ(spieler[0].getX()))+1]).getExistiert()))
//						{
//							if(!collision('d', 0))
//							{
//								//Hier kommt Kollisioncheck
//								spieler[0].setX(spieler[0].getX() + spieler[0].getGeschwindigkeitsZahlX());
//								spieler[0].setDieJ(spieler[0].getX());
//							}
//						}
//						else
//						{
//							spieler[0].setX(spieler[0].getX() + spieler[0].getGeschwindigkeitsZahlX());
//							spieler[0].setDieJ(spieler[0].getX());
//						}
						booldsp1.set(true);
//					}
					p.repaint();
				}
				if (!boolssp1.get() && e.getKeyChar() == 's' || e.getKeyChar() == 'ы') 
				{
//					if ((spieler[0].getY() + spieler[0].getGeschwindigkeitsZahlY()) <= hoehe - hoehe / zeilen) 
//					{
//						if((spieler[0].setGetDieI(spieler[0].getY())) < grassHeuSteine.length && 
//						(grassHeuSteine[(spieler[0].setGetDieI(spieler[0].getY()))+1][spieler[0].setGetDieJ(spieler[0].getX())] instanceof Heu || 
//						grassHeuSteine[(spieler[0].setGetDieI(spieler[0].getY()))+1][spieler[0].setGetDieJ(spieler[0].getX())] instanceof Steine || 
//						grassHeuSteine[(spieler[0].setGetDieI(spieler[0].getY()))+1][spieler[0].setGetDieJ(spieler[0].getX() + breite / spalten-1)] instanceof Heu 
//						|| grassHeuSteine[(spieler[0].setGetDieI(spieler[0].getY()))+1][spieler[0].setGetDieJ(spieler[0].getX() + breite / spalten-1)] instanceof Steine 
//						|| bombe[(spieler[0].setGetDieI(spieler[0].getY()))+1][spieler[0].setGetDieJ(spieler[0].getX() + breite / spalten-1)] instanceof Bombe
//						&& ((Bombe) bombe[(spieler[0].setGetDieI(spieler[0].getY()))+1][spieler[0].setGetDieJ(spieler[0].getX() + breite / spalten-1)]).getExistiert()))
//						{
//							if(!collision('s', 0))
//							{
//								//Hier kommt Kollisioncheck
//								spieler[0].setY(spieler[0].getY() + spieler[0].getGeschwindigkeitsZahlY());
//								spieler[0].setDieI(spieler[0].getY());
//							}
//						}
//						else
//						{
//							spieler[0].setY(spieler[0].getY() + spieler[0].getGeschwindigkeitsZahlY());
//							spieler[0].setDieI(spieler[0].getY());
//						}
						boolssp1.set(true);
//					}
					p.repaint();
				}
				if (!boolasp1.get() && e.getKeyChar() == 'a' || e.getKeyChar() == 'ф') 
				{
//					if ((spieler[0].getX() - spieler[0].getGeschwindigkeitsZahlX()) >= 0) 
//					{
//						if((spieler[0].setGetDieJ(spieler[0].getX())) > 0 && 
//						(grassHeuSteine[spieler[0].setGetDieI(spieler[0].getY())][(spieler[0].setGetDieJ(spieler[0].getX()))-1] instanceof Heu || 
//						grassHeuSteine[spieler[0].setGetDieI(spieler[0].getY())][(spieler[0].setGetDieJ(spieler[0].getX()))-1] instanceof Steine || 
//						grassHeuSteine[spieler[0].setGetDieI(spieler[0].getY() + hoehe / zeilen-1)][(spieler[0].setGetDieJ(spieler[0].getX()))-1] instanceof Heu 
//						|| grassHeuSteine[spieler[0].setGetDieI(spieler[0].getY() + hoehe / zeilen-1)][(spieler[0].setGetDieJ(spieler[0].getX()))-1] instanceof Steine
//						|| bombe[spieler[0].setGetDieI(spieler[0].getY() + hoehe / zeilen-1)][(spieler[0].setGetDieJ(spieler[0].getX()))-1] instanceof Bombe
//						&& ((Bombe) bombe[spieler[0].setGetDieI(spieler[0].getY() + hoehe / zeilen-1)][(spieler[0].setGetDieJ(spieler[0].getX()))-1]).getExistiert()))
//						{
//							if(!collision('a', 0))
//							{
//								//Hier kommt Kollisioncheck
//								spieler[0].setX(spieler[0].getX() - spieler[0].getGeschwindigkeitsZahlX());
//								spieler[0].setDieJ(spieler[0].getX());
//							}
//						}
//						else
//						{
//							spieler[0].setX(spieler[0].getX() - spieler[0].getGeschwindigkeitsZahlX());
//							spieler[0].setDieJ(spieler[0].getX());
//						}
						boolasp1.set(true);
//					}
					p.repaint();
				}
				// 1-ter Spieler
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (spieler[0].getBombenLevel() > 0 && spieler[1].getLebt()) {
						if (!((Bombe) bombe[spieler[1].getDieI()][spieler[1].getDieJ()]).getExistiert()
								&& bombe[spieler[1].getDieI()][spieler[1].getDieJ()] instanceof Bombe) {
							((Bombe) bombe[spieler[1].getDieI()][spieler[1].getDieJ()]).resetTimer();
							((Bombe) bombe[spieler[1].getDieI()][spieler[1].getDieJ()])
									.setSchwellenStaerkeX(spieler[1].getSchwellenStaerkeX());
							((Bombe) bombe[spieler[1].getDieI()][spieler[1].getDieJ()])
									.setSchwellenStaerkeY(spieler[1].getSchwellenStaerkeY());
							((Bombe) bombe[spieler[1].getDieI()][spieler[1].getDieJ()])
									.setHatGesetzt(spieler[1].getSpielerNr());
							((Bombe) bombe[spieler[1].getDieI()][spieler[1].getDieJ()]).setExistiert(true);
							spieler[1].setBombenLevel(spieler[1].getBombenLevel() - 1);
							p.repaint();
						}
					}
				} else if (!boolupsp2.get() && e.getKeyCode() == KeyEvent.VK_UP ) 
				{
//					if ((spieler[1].getY() - spieler[1].getGeschwindigkeitsZahlY()) >= 0) 
//					{
//						if((spieler[1].setGetDieI(spieler[1].getY())) > 0 && 
//						(grassHeuSteine[(spieler[1].setGetDieI(spieler[1].getY()))-1][spieler[1].setGetDieJ(spieler[1].getX())] instanceof Heu || 
//						grassHeuSteine[(spieler[1].setGetDieI(spieler[1].getY()))-1][spieler[1].setGetDieJ(spieler[1].getX())] instanceof Steine || 
//						grassHeuSteine[(spieler[1].setGetDieI(spieler[1].getY()))-1][spieler[1].setGetDieJ(spieler[1].getX() + breite / spalten-1)] instanceof Heu 
//						|| grassHeuSteine[(spieler[1].setGetDieI(spieler[1].getY()))-1][spieler[1].setGetDieJ(spieler[1].getX() + breite / spalten-1)] instanceof Steine
//						|| bombe[(spieler[1].setGetDieI(spieler[1].getY()))-1][spieler[1].setGetDieJ(spieler[1].getX() + breite / spalten-1)] instanceof Bombe
//						&& ((Bombe) bombe[(spieler[1].setGetDieI(spieler[1].getY()))-1][spieler[1].setGetDieJ(spieler[1].getX() + breite / spalten-1)]).getExistiert()))
//						{
//							if(!collision('w', 1))
//							{
//								//Hier kommt Kollisioncheck
//								spieler[1].setY(spieler[1].getY() - spieler[1].getGeschwindigkeitsZahlY());
//								spieler[1].setDieI(spieler[1].getY());	
//							}
//						}
//						else
//						{
//							spieler[1].setY(spieler[1].getY() - spieler[1].getGeschwindigkeitsZahlY());
//							spieler[1].setDieI(spieler[1].getY());
//						}
						boolupsp2.set(true);
//					}
					
					p.repaint();
				}
				if (!boolrightsp2.get() && e.getKeyCode() == KeyEvent.VK_RIGHT ) 
				{
//					if (spieler[1].getX()+ spieler[1].getGeschwindigkeitsZahlX() <= breite - breite / spalten) 
//					{
//						if((spieler[1].setGetDieJ(spieler[1].getX())) < grassHeuSteine[0].length && 
//						(grassHeuSteine[spieler[1].setGetDieI(spieler[1].getY())][(spieler[1].setGetDieJ(spieler[1].getX()))+1] instanceof Heu || 
//						grassHeuSteine[spieler[1].setGetDieI(spieler[1].getY())][(spieler[1].setGetDieJ(spieler[1].getX()))+1] instanceof Steine || 
//						grassHeuSteine[spieler[1].setGetDieI(spieler[1].getY() + hoehe / zeilen-1)][(spieler[1].setGetDieJ(spieler[1].getX()))+1] instanceof Heu 
//						|| grassHeuSteine[spieler[1].setGetDieI(spieler[1].getY() + hoehe / zeilen-1)][(spieler[1].setGetDieJ(spieler[1].getX()))+1] instanceof Steine
//						|| bombe[spieler[1].setGetDieI(spieler[1].getY() + hoehe / zeilen-1)][(spieler[1].setGetDieJ(spieler[1].getX()))+1] instanceof Bombe
//						&& ((Bombe) bombe[spieler[1].setGetDieI(spieler[1].getY() + hoehe / zeilen-1)][(spieler[1].setGetDieJ(spieler[1].getX()))+1]).getExistiert()))
//						{
//							if(!collision('d', 1))
//							{
//								//Hier kommt Kollisioncheck
//								spieler[1].setX(spieler[1].getX() + spieler[1].getGeschwindigkeitsZahlX());
//								spieler[1].setDieJ(spieler[1].getX());
//							}
//						}
//						else
//						{
//							spieler[1].setX(spieler[1].getX() + spieler[1].getGeschwindigkeitsZahlX());
//							spieler[1].setDieJ(spieler[1].getX());
//						}
						boolrightsp2.set(true);
//					}
					p.repaint();
				}
				if (!booldownsp2.get() && e.getKeyCode() == KeyEvent.VK_DOWN) 
				{
//					if ((spieler[1].getY() + spieler[1].getGeschwindigkeitsZahlY()) <= hoehe - hoehe / zeilen) 
//					{
//						if((spieler[1].setGetDieI(spieler[1].getY())) < grassHeuSteine.length && 
//						(grassHeuSteine[(spieler[1].setGetDieI(spieler[1].getY()))+1][spieler[1].setGetDieJ(spieler[1].getX())] instanceof Heu || 
//						grassHeuSteine[(spieler[1].setGetDieI(spieler[1].getY()))+1][spieler[1].setGetDieJ(spieler[1].getX())] instanceof Steine || 
//						grassHeuSteine[(spieler[1].setGetDieI(spieler[1].getY()))+1][spieler[1].setGetDieJ(spieler[1].getX() + breite / spalten-1)] instanceof Heu 
//						|| grassHeuSteine[(spieler[1].setGetDieI(spieler[1].getY()))+1][spieler[1].setGetDieJ(spieler[1].getX() + breite / spalten-1)] instanceof Steine 
//						|| bombe[(spieler[1].setGetDieI(spieler[1].getY()))+1][spieler[1].setGetDieJ(spieler[1].getX() + breite / spalten-1)] instanceof Bombe
//						&& ((Bombe) bombe[(spieler[1].setGetDieI(spieler[1].getY()))+1][spieler[1].setGetDieJ(spieler[1].getX() + breite / spalten-1)]).getExistiert()))
//						{
//							if(!collision('s', 1))
//							{
//								//Hier kommt Kollisioncheck
//								spieler[1].setY(spieler[1].getY() + spieler[1].getGeschwindigkeitsZahlY());
//								spieler[1].setDieI(spieler[1].getY());
//							}
//						}
//						else
//						{
//							spieler[1].setY(spieler[1].getY() + spieler[1].getGeschwindigkeitsZahlY());
//							spieler[1].setDieI(spieler[1].getY());
//						}
						booldownsp2.set(true);
					}
//					p.repaint();
//				}
				if (!boolleftsp2.get() && e.getKeyCode() == KeyEvent.VK_LEFT) 
				{
//					if ((spieler[1].getX() - spieler[1].getGeschwindigkeitsZahlX()) >= 0) 
//					{
//						if((spieler[1].setGetDieJ(spieler[1].getX())) > 0 && 
//						(grassHeuSteine[spieler[1].setGetDieI(spieler[1].getY())][(spieler[1].setGetDieJ(spieler[1].getX()))-1] instanceof Heu || 
//						grassHeuSteine[spieler[1].setGetDieI(spieler[1].getY())][(spieler[1].setGetDieJ(spieler[1].getX()))-1] instanceof Steine || 
//						grassHeuSteine[spieler[1].setGetDieI(spieler[1].getY() + hoehe / zeilen-1)][(spieler[1].setGetDieJ(spieler[1].getX()))-1] instanceof Heu 
//						|| grassHeuSteine[spieler[1].setGetDieI(spieler[1].getY() + hoehe / zeilen-1)][(spieler[1].setGetDieJ(spieler[1].getX()))-1] instanceof Steine
//						|| bombe[spieler[1].setGetDieI(spieler[1].getY() + hoehe / zeilen-1)][(spieler[1].setGetDieJ(spieler[1].getX()))-1] instanceof Bombe
//						&& ((Bombe) bombe[spieler[1].setGetDieI(spieler[1].getY() + hoehe / zeilen-1)][(spieler[1].setGetDieJ(spieler[1].getX()))-1]).getExistiert()))
//						{
//							if(!collision('a', 1))
//							{
//								//Hier kommt Kollisioncheck
//								spieler[1].setX(spieler[1].getX() - spieler[1].getGeschwindigkeitsZahlX());
//								spieler[1].setDieJ(spieler[1].getX());
//							}
//						}
//						else
//						{
//							spieler[1].setX(spieler[1].getX() - spieler[1].getGeschwindigkeitsZahlX());
//							spieler[1].setDieJ(spieler[1].getX());
//						}
						boolleftsp2.set(true);
//					}
				}
				else if (e.getKeyCode() == KeyEvent.VK_HOME) 
				{
					System.out.println("Check for key codes: " + e.getKeyCode());
				}
				}
				
			@Override
			public synchronized void keyReleased(KeyEvent e)
			{
				if(e.getKeyChar() == 'w')
				{
					boolwsp1.set(false);
				}
				else if(e.getKeyChar() == 'a')
				{
					boolasp1.set(false);
				}
				else if(e.getKeyChar() == 's')
				{
					boolssp1.set(false);
				}
				else if(e.getKeyChar() == 'd')
				{
					booldsp1.set(false);
				}
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
				{
					boolrightsp2.set(false);
				}
				else if(e.getKeyCode() == KeyEvent.VK_LEFT)
				{
					boolleftsp2.set(false);
				}
				else if(e.getKeyCode() == KeyEvent.VK_DOWN)
				{
					booldownsp2.set(false);
				}
				else if(e.getKeyCode() == KeyEvent.VK_UP)
				{
					boolupsp2.set(false);
				}

			}
		});
		this.add(p, BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	

	public boolean collision(char richtung, int spielerNr)
	{
		if(richtung == 'w')
		{
			for (int k = spieler[spielerNr].getX(); k < spieler[spielerNr].getX() + spielerbreite; k++) 
			{
				int gY1 = ((spieler[spielerNr].getY() * zeilen / hoehe) * hoehe / zeilen) - spieler[spielerNr].getGeschwindigkeitsZahlY();
				int gX1 = ((k * spalten / breite) * breite / spalten);
				int gX2 = ((k * spalten / breite) * breite / spalten) + breite / spalten;
				int gY2 = gY1;
				if((k == gX1 && spieler[spielerNr].getY() - spieler[spielerNr].getGeschwindigkeitsZahlY() == gY1) || 
						(k == gX2 && spieler[spielerNr].getY() - spieler[spielerNr].getGeschwindigkeitsZahlY() == gY2))
				{
					return true;
				}
			}
		}
		else if(richtung == 's')
		{
			for (int k = spieler[spielerNr].getX(); k < spieler[spielerNr].getX() + spielerbreite; k++) 
			{
				int gY1 = (((spieler[spielerNr].getY() * zeilen / hoehe) * hoehe / zeilen) + (hoehe / zeilen - 1)) + spieler[spielerNr].getGeschwindigkeitsZahlY();
				int gX1 = ((k * spalten / breite) * breite / spalten);
				int gX2 = ((k * spalten / breite) * breite / spalten) + breite / spalten;
				int gY2 = gY1;
				if((k == gX1 && (spieler[spielerNr].getY() + (hoehe / zeilen - 1)) + spieler[spielerNr].getGeschwindigkeitsZahlY() == gY1) || 
						(k == gX2 && (spieler[spielerNr].getY() + (hoehe / zeilen - 1)) + spieler[spielerNr].getGeschwindigkeitsZahlY() == gY2))
				{
					return true;
				}
			}
		}
		else if(richtung == 'd')
		{
			for (int k = spieler[spielerNr].getY(); k < spieler[spielerNr].getY() + spielerhöhe; k++) 
			{
				int gY1 = ((k * zeilen / hoehe) * hoehe / zeilen);
				int gX1 = (((spieler[spielerNr].getX() * spalten / breite) * breite / spalten) + (breite / spalten - 1)) + spieler[spielerNr].getGeschwindigkeitsZahlX();
				int gX2 = gX1;
				int gY2 = ((k * zeilen / hoehe) * hoehe / zeilen) + hoehe / zeilen;
				if((k == gY1 && (spieler[spielerNr].getX() + (breite / spalten - 1)) + spieler[spielerNr].getGeschwindigkeitsZahlX() == gX1) || 
						(k == gY2 && (spieler[spielerNr].getX() + (breite / spalten - 1)) + spieler[spielerNr].getGeschwindigkeitsZahlX() == gX2))
				{
					return true;
				}
			}
		}
		else if(richtung == 'a')
		{
			for (int k = spieler[spielerNr].getY(); k < spieler[spielerNr].getY() + spielerhöhe; k++) 
			{
				int gY1 = ((k * zeilen / hoehe) * hoehe / zeilen);
				int gX1 = ((spieler[spielerNr].getX() * spalten / breite) * breite / spalten) - spieler[spielerNr].getGeschwindigkeitsZahlX();
				int gX2 = gX1;
				int gY2 = ((k * zeilen / hoehe) * hoehe / zeilen) + hoehe / zeilen;
				if((k == gY1 && spieler[spielerNr].getX() - spieler[spielerNr].getGeschwindigkeitsZahlX() == gX1) || 
						(k == gY2 && spieler[spielerNr].getX() - spieler[spielerNr].getGeschwindigkeitsZahlX() == gX2))
				{
					return true;
				}
			}
		}
		return false;
	}	
}
