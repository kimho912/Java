package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.InputMismatchException; 
import java.util.NoSuchElementException;

import java.io.File;
import java.io.IOException;


class Line {
    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    public int x1, y1, x2, y2;
}

public class Canvas extends JPanel {
    public Canvas(String imageFile) {
        setBorder(new EmptyBorder(0,0,0,0));

    // Load image
    try {
        image = ImageIO.read(new File(imageFile));
    } catch (IOException e) {
        System.err.println("Abort in About screen: Unable to load " + imageFile + "\n" + e);
    }

    // Load background vector image
    minX = Integer.MAX_VALUE;   // Determines minimum point in X direction
    minY = Integer.MAX_VALUE;   // Determines minimum point in Y direction
    maxX = Integer.MIN_VALUE;
    maxY = Integer.MIN_VALUE;
    lines = new ArrayList<>();
    
    panelSize = new Dimension(Math.max(image.getWidth(),  maxX-minX),
                              Math.max(image.getHeight(), maxY-minY));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int) panelSize.getWidth(), (int) panelSize.getHeight());
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2D = (Graphics2D) graphics; 
        
        int offsetX = Math.max(getWidth()  - (int) panelSize.getWidth(),  0) / 2;
        int offsetY = Math.max(getHeight() - (int) panelSize.getHeight(), 0) / 2;
        
        Color[] colors = {Color.RED,  Color.ORANGE,  Color.PINK, Color.GREEN,
                          Color.BLUE, Color.MAGENTA};

        int lineCounter = 0;
        for(Line line : lines) {
            g2D.setColor(colors[lineCounter++ % colors.length]);
            g2D.drawLine(line.x1 - minX + offsetX, line.y1 - minY + offsetY, 
                       line.x2 - minX + offsetX, line.y2 - minY + offsetY);
        }

        // add image in center
        g2D.drawImage(image, (int) ((panelSize.getWidth()  - image.getWidth())  * 0.4) + offsetX, 
                           (int) ((panelSize.getHeight() - image.getHeight()) * 0.4) + offsetY, this);
                           
        // Add "artist's signature"
        g2D.setColor(Color.RED);
        g2D.drawString("Hyun Ho Kim", (int) (panelSize.getWidth()*0.5), 
                                        (int) (panelSize.getHeight()*0.9));
    }

    Dimension panelSize;
    private BufferedImage image;
    private ArrayList<Line> lines;
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
}