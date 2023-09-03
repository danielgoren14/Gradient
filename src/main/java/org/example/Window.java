package org.example;

import javax.imageio.ImageIO;
import javax.imageio.metadata.IIOMetadataFormatImpl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import static java.awt.event.KeyEvent.VK_UP;

public class Window extends JFrame {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        new Window();
    }


    public static final int WIDTH = 256;
    public static final int HEIGHT = 256;

    public Window () {
        BufferedImage bufferedImage = randomBufferedImage();
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("Gradient");
        this.setSize(WIDTH,HEIGHT);
        JLabel label = new JLabel(new ImageIcon(bufferedImage));

        // Add the JLabel to the content pane of the JFrame
        this.getContentPane().add(label);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case VK_UP -> {
                        BufferedImage currentBufferedImage = randomBufferedImage();
                        label.setIcon(new ImageIcon(currentBufferedImage)); // Set the new image as the icon
                        label.repaint(); // Repaint the label to display the new image
                        System.out.println("up");
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.requestFocusInWindow(); // Request focus to enable key events

    }

        private BufferedImage randomBufferedImage () {
        BufferedImage bufferedImage = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < WIDTH; i++) {
                int startRed = currentStartOfIntensity();
                int startGreen = currentStartOfIntensity();
                int startBlue = currentStartOfIntensity();
                for (int j = 0; j < HEIGHT; j++) {
                    int intensityRed = range(startRed);
                    int intensityGreen = range(startGreen);
                    int intensityBlue = range(startBlue);
                    Color color = new Color(intensityRed, intensityGreen, intensityBlue);
                    int rgb = color.getRGB();
                    bufferedImage.setRGB(j,i,rgb);
                }
            }
            return bufferedImage;
        }
    private int range (int currentIntensity) {
        Random random = new Random();
        return random.nextInt(currentIntensity,currentIntensity + 120);
    }
    public int currentStartOfIntensity () {
        Random random = new Random();
        return random.nextInt(136);
    }


}
