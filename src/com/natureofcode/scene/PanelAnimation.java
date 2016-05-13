package com.natureofcode.scene;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 *
 * @author syjebrane
 */
public class PanelAnimation extends JPanel {

    private int width, height, delay;

    private List<Animated> listAnimated;

    private Timer timer;

    private TimerTask timerTask;

    private boolean stopped;

    public PanelAnimation(int width, int height, int delay) {
        this.setPreferredSize(new Dimension(width, height));
        this.listAnimated = new ArrayList<>();
        this.delay = delay;
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
        System.out.println("compute");
        for (Animated animated : listAnimated) {
            animated.compute();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("repaint");
        super.paintComponent(g);
        for (Animated animated : listAnimated) {
            animated.draw(g);
        }
    }

    public void start() {
        this.timer.scheduleAtFixedRate(timerTask, this.delay, 100);
    }

    public void stop() {
        this.stopped = true;
    }

    public void addAnimated(Animated newAnimated) {
        this.listAnimated.add(newAnimated);
    }

    public void clearListAnimated() {
        this.listAnimated.clear();
    }

}
