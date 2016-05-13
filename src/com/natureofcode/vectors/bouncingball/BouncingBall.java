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

    private static final int WIDTH = 800, HEIGHT = 600;

    float x = 100, y = 100, xspeed = 1, yspeed = 3.3f;

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
            xspeed = xspeed * -1;
        }
        if ((y > HEIGHT) || (y < 0)) {
            yspeed = yspeed * -1;
        }
    }

    public static void main(String[] args) {
        PanelAnimation p = new PanelAnimation(WIDTH, HEIGHT, 10);
        Fenetre f = new Fenetre(p);
        f.setVisible(true);
        p.start();
    }

}
