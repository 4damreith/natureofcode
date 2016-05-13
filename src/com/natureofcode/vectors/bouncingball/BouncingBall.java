package com.natureofcode.vectors.bouncingball;

import com.natureofcode.scene.Animated;
import com.natureofcode.scene.Fenetre;
import com.natureofcode.scene.PanelAnimation;
import java.awt.Graphics;

/**
 *
 * @author syjebrane
 */
public class BouncingBall implements Animated {

    private static final int WIDTH = 100, HEIGHT = 100;

    public float x = 100f, y = 100f, xspeed = 1f, yspeed = 3.3f, diameter;

    public BouncingBall(float x, float y, float diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
    }

    @Override
    public void draw(Graphics g) {
        g.drawOval((int) x, (int) y, 16, 16);
    }

    @Override
    public void compute() {
        //update ball position
        x += xspeed;
        y += yspeed;

        //check for bouncing
        if ((x > WIDTH) || (x < 0)) {
            xspeed *= -1;
        }
        if ((y > HEIGHT - this.diameter) || (y < 0)) {
            yspeed *= -1;
        }
    }

    public static void main(String[] args) {
        PanelAnimation p = new PanelAnimation(WIDTH, HEIGHT, 1);
        p.addAnimated(new BouncingBall(0, 0, 16));
        Fenetre f = new Fenetre(p);
        f.setVisible(true);
        p.start();
    }

}
