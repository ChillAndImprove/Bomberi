import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bilder {
	BufferedImage stone;
	BufferedImage bombeGross;
	BufferedImage bombeMittel;
	BufferedImage bombeKlein;
	BufferedImage kiste;

	BufferedImage vorneSee;
	BufferedImage weiß_hinten;
	BufferedImage weiß_links;
	BufferedImage weiß_rechts;
	
	BufferedImage schwarz_vorne;
	BufferedImage schwarz_hinten;
	BufferedImage schwarz_links;
	BufferedImage schwarz_rechts;
	
	BufferedImage rot_vorne;
	BufferedImage rot_hinten;
	BufferedImage rot_links;
	BufferedImage rot_rechts;
	
	BufferedImage blau_vorne;
	BufferedImage blau_hinten;
	BufferedImage blau_links;
	BufferedImage blau_rechts;
	
	public Bilder()
	{
		
	}
	
	public BufferedImage loadImages(File f,int feldx, int feldy) {
		try {

			BufferedImage tmp;
			tmp= ImageIO.read(f);
			tmp = resize(tmp, feldx, feldy);
			return tmp;
		} catch (IOException e) {
		}
		return null;
	}

	public BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return dimg;
	}

}
