package gui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.Image;

public class Canvas extends JPanel {
    public Canvas() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }
    public Dimension getPreferredSize() {
        return new Dimension(700,500);
    }
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D g2D = (Graphics2D) graphics; 
        // try {
        //     BufferedImage image = ImageIO.read(new File("gui/about.png"));
        // } catch(IOException e) {
        // }
        Image img1 = Toolkit.getDefaultToolkit().getImage("gui/about.png");
        Color color = new Color(30);
        Color color2 = new Color(20);

        g2D.drawLine(60, 0, 60, 700);
        g2D.setColor(color);
        g2D.setColor(color2);
        g2D.drawString("Hyun ho Kim",0,600);
        g2D.drawImage(img1, 0, 0, null);
    } 
}