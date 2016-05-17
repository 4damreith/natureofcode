package com.natureofcode.scene;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WindowDisp extends JFrame {

    private PanelAnimation panel;

    public WindowDisp(int width, int height) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        panel = new PanelAnimation(width, height);
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(4, 4, 4, 4));
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public PanelAnimation getPanel() {
        return this.panel;
    }

    public void addAnimated(Animated anim) {
        this.panel.addAnimated(anim);
    }

    public void start() {
        this.panel.start();
    }

    private class PanelAnimation extends JPanel {

        private final int width;
        private final int height;
        private int delay;
        public Dimension PANEL_SIZE;
        private List<Animated> listAnimated;
        private Timer timer;
        private TimerTask timerTask;
        private boolean stopped;

        public PanelAnimation(int width, int height) {
            this.width = width;
            this.height = height;
            this.listAnimated = new ArrayList<>();
            this.delay = 10;
            this.stopped = false;
            this.timer = new Timer();
            this.timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (!stopped) {
                        compute();
                        repaint();
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
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, this.getWidth(), this.getHeight());
            for (Animated animated : listAnimated) {
                animated.draw(g);
            }
        }

        public void start() {
            this.timer.scheduleAtFixedRate(timerTask, 100, this.delay);
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

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(width, height);
        }
    }
}
