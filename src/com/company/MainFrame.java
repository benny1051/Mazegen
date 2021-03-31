package com.company;

import com.sun.tools.javac.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MainFrame {

    private int nbrOfBtns = 0;
    private int x = 0;
    private int y = 0;

    private JFrame frame;
    private JButton buttons[];
    private JButton lastBtnPressed;
    private JPanel panel = new JPanel();
    MouseListener m1 = null;

    public MainFrame(int nbrOfBtns, int x, int y) throws IOException {
        this.nbrOfBtns = nbrOfBtns;
        this.x = x;
        this.y = y;

        frame = new JFrame();
        frame.setLocation(700,300);
        frame.setSize(x * 60, y * 60);
        panel.setLayout(new FlowLayout(0,1,1));
        frame.add(panel);
        createButtonsInPanel();
        JButton startGame = new JButton("Start");
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                m1 = new MouseHandler();

                for (int i = 0; i < buttons.length; i++) {
                    if (buttons[i].getMouseListeners().length != 0 && !"Win".equals(buttons[i].getName()) && !"Floor".equals(buttons[i].getName())){
                        buttons[i].addMouseListener(m1);
                    }
                }
            }
        });
        panel.add(startGame);
        frame.setVisible(true);


    }
    private void createButtonsInPanel() throws IOException {
        buttons = new JButton[nbrOfBtns];

        for (int i= 0; i < buttons.length; i++) {
            buttons[i] = new JButton(new ImageIcon("files/wall.jpg"));
            buttons[i].setBorderPainted(false);
            buttons[i].setFocusPainted(false);
            buttons[i].setContentAreaFilled(false);
            buttons[i].addActionListener(new Listener());
            buttons[i].setPreferredSize(new Dimension(50,50));
            buttons[i].addMouseListener(m1);
            panel.add(buttons[i]);

        }
    }

    public class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            JButton button = (JButton) e.getSource();

            if (lastBtnPressed == button) {
                lastBtnPressed.setIcon(null);
                lastBtnPressed.setIcon(new ImageIcon("files/red.jpg"));
                lastBtnPressed.removeMouseListener(m1);
                lastBtnPressed.addMouseListener(new MouseHandler2());
                lastBtnPressed.setName("Win");
            }
            else {
                for (int i= 0; i < buttons.length; i++) {

                    if (buttons[i] == (button)) {
                        buttons[i].setIcon(new ImageIcon("files/floor.png"));
                        buttons[i].setName("Floor");
                        buttons[i].removeMouseListener(m1);
                    }
                }

            }
            lastBtnPressed = button;

        }
    }
    public class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {
            JOptionPane.showMessageDialog(null, "You lost!");
            frame.dispose();
            new Menu();
        }
        @Override
        public void mouseExited(MouseEvent e) {}
    }
    public class MouseHandler2 implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {
            JOptionPane.showMessageDialog(null, "You won!");
            frame.dispose();
            new Menu();
        }
        @Override
        public void mouseExited(MouseEvent e) {}
    }


}