package com.natureofcode.vectors.bouncingball;

import com.natureofcode.scene.Animated;
import com.natureofcode.scene.WindowDisp;
import com.natureofcode.vectors.PVector;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author syjebrane
 */
public class BouncingBall implements Animated {

    private static final int WIDTH = 400, HEIGHT = 400;

    public PVector position;
    public PVector speed;
    public int diameter;

    public BouncingBall(PVector position, PVector speed, int diameter) {
        this.position = position;
        this.speed = speed;
        this.diameter = diameter;
    }

    @Override
    public void draw(Graphics g) {
        g.drawOval((int) position.x, (int) position.y, diameter, diameter);
    }

    @Override
    public void compute() {
        //update ball position
        position.x += speed.x;
        position.y += speed.y;

        //check for bouncing
        if ((position.x > WIDTH - this.diameter) || (position.x < 0)) {
            speed.x *= -1;
        }
        if ((position.y > HEIGHT - this.diameter) || (position.y < 0)) {
            speed.y *= -1;
        }
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                WindowDisp f = new WindowDisp(WIDTH, HEIGHT);
                f.addAnimated(new BouncingBall(new PVector(0, 0), new PVector(1.3f, 3.3f), 16));
                f.start();
            }
        });
    }

}
