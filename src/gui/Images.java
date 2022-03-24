package gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Images {
	private Images() {
		
	}
	
	public static ImageIcon getButtonIcon(JButton button, Color bgColor) {
		button.setFocusPainted(false);
		button.setBackground(bgColor);
		button.setForeground(ColorScheme.BACKGROUND);
		button.setBorder(null);
		button.setContentAreaFilled(false);
		button.setFont(ColorScheme.FONT);
		
		button.setHorizontalTextPosition(JButton.CENTER);
		button.setVerticalTextPosition(JButton.CENTER);
		button.setPressedIcon(new ImageIcon(Images.getButton((int)(button.getText().length() *
				ColorScheme.FONT.getSize() * 0.7), ColorScheme.FONT.getSize() * 2, ColorScheme.BUTTON_HIGHTLIGHT)));
		return new ImageIcon(Images.getButton((int)(button.getText().length() *
				ColorScheme.FONT.getSize() * 0.7), ColorScheme.FONT.getSize() * 2, ColorScheme.BUTTON));
	}
	
	public static BufferedImage getButton(int w, int h, Color color) {
		BufferedImage retVal = null;
	
		retVal = createImageWithBG(color, w, h);
		
		retVal = makeRoundedCorner(retVal, 20);
		
		return retVal;
	}
	
	private static URL getUrl(String path) {
		return new Images().getClass().getResource(path);
	}
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
	
	public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
	    int w = image.getWidth();
	    int h = image.getHeight();
	    BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2 = output.createGraphics();
	    
	    // This is what we want, but it only does hard-clipping, i.e. aliasing
	    // g2.setClip(new RoundRectangle2D ...)

	    // so instead fake soft-clipping by first drawing the desired clip shape
	    // in fully opaque white with antialiasing enabled...
	    g2.setComposite(AlphaComposite.Src);
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setColor(Color.WHITE);
	    g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
	    
	    // ... then compositing the image on top,
	    // using the white shape from above as alpha source
	    g2.setComposite(AlphaComposite.SrcAtop);
	    g2.drawImage(image, 0, 0, null);
	    
	    g2.dispose();
	    
	    return output;
	}
	
	public static BufferedImage createImageWithBG(Color color, int width, int height) {
		// Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
 
        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();
 
        // fill all the image with white
        g2d.setColor(color);
        g2d.fillRect(0, 0, width, height);
        
        return bufferedImage;
	}
}
