package mah.se.algorithms;
        import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import mah.se.mvc.model.Array7x7;
import roffe.Color.Color;

/**
 * @author Gustaf Bohlin, Sebastian J Börebäck
 * Skapar en 7x7 array med 1 och 0 som representerar en bokstav
 */
public class Alphabet {

	private Font font;
	
	/**
	 * Konstruktor
	 */
	public Alphabet() {
		try {
			font = initFont();
		} catch (FontFormatException | IOException | URISyntaxException  e) {
			e.printStackTrace();
        }
    }
	
	/**
	 * 
	 * @return Ett importerat typsnitt för att bokstäverna ska se bra ut i 7x7
	 * @throws FontFormatException
	 * @throws IOException
	 */
	public Font initFont() throws FontFormatException, IOException, URISyntaxException {
        URL fontpath = this.getClass().getClassLoader().getResource("assets/00TT.ttf");
		return Font.createFont(Font.TRUETYPE_FONT, new File(fontpath.toURI())).deriveFont(12f);
	}
	
	/**
	 * 
	 * @param letter
	 * Bokstaven som man vill ha i 7x7 format
	 * @return En 2d int array som är 7x7 och representerar en bokstav
	 * @exception Om bokstaven inte accepteras
	 */
	public Array7x7 getLetter(char letter) {
		int[][] array = new int[7][7];
		//Skapar en ny bild av typen RGB
        BufferedImage image = new BufferedImage(array.length, array.length, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(font);
        Graphics2D graphics = (Graphics2D) g;
        //Ritar ut bokstaven på graphics objektet av image
        graphics.drawString(Character.toString(letter), 0, array.length);
        //En boolean för att se om det är en bokstav som returneras eller om bokstaven inte accepteras
        boolean isChar = false;
        //skriver bilden i arrayen genom att loppa igenom den.
        //-16777216 är blank ruta
        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < 7; x++) {
            	if(image.getRGB(x, y) == -16777216) {
            		array[y][x] = Color.TRANSPARENT;
            	}
            	else {
            		array[y][x] = Color.BLACK;
            		isChar = true;
            	}
            }
        }
        //Om det inte är någon bokstav som returneras, kasta ett exception
        if(!isChar)
        	throw new RuntimeException();
        Array7x7 array7x7 = new Array7x7(array);
        return array7x7;
    }
}
