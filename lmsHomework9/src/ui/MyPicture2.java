package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MyPicture2 extends JFrame{
	private static final long serialVersionUID = 1L;
	private BufferedImage img = null;
	public MyPicture2() {
		this.setBounds(100,100,558,555);
//		this.setResizable(false);
		try {
			img = ImageIO.read(new File("myy.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}
