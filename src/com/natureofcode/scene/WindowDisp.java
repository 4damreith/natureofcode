package com.natureofcode.scene;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WindowDisp extends JFrame {

    private final int width;
    private final int height;
    private int delay;
    public Dimension PANEL_SIZE;
    private List<Animated> listAnimated;
    private Timer timer;
    private TimerTask timerTask;
    private boolean stopped;
    private PanelAnimation panel;

    public WindowDisp(int width, int height) {
        this.listAnimated = new ArrayList<>();
        this.delay = 10;
        this.stopped = false;
        this.timer = new Timer();
        this.width = width;
        this.height = height;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.panel = new PanelAnimation(width, height);
        this.panel.setLayout(new BorderLayout());
        this.panel.setBorder(new EmptyBorder(4, 4, 4, 4));
        this.add(this.panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public PanelAnimation getPanel() {
        return this.panel;
    }

    public void start() {
        this.panel.repaint();
        this.timer.scheduleAtFixedRate(timerTask, 1500, this.delay);
    }

    public void stop() {
        this.stopped = true;
    }

    public void addAnimated(Animated anim) {
        this.listAnimated.add(anim);
    }

    public void clearListAnimated() {
        this.listAnimated.clear();
    }

    public List<Animated> getListAnimated() {
        return listAnimated;
    }

    private class PanelAnimation extends JPanel {

        public PanelAnimation(int width, int height) {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (!stopped) {
                        PanelAnimation.this.compute();
                        PanelAnimation.this.repaint();
                    }
                }
            };
        }

        public void compute() {
            for (Animated animated : listAnimated) {
                animated.compute();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            for (Animated animated : listAnimated) {
                animated.draw(g2);
            }
            g2.drawOval(this.getWidth() / 2, this.getHeight() / 2, 20, 20);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(width, height);
        }
    }
}
