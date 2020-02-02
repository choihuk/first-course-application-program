package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyPicture extends JPanel{
	private static final long serialVersionUID = 1L;
	private BufferedImage img = null;
	
	public MyPicture() {
		this.setPreferredSize(new Dimension(100,100));
		try {
			img = ImageIO.read(new File("my.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}
