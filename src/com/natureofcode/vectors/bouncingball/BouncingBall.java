package com.natureofcode.vectors.bouncingball;

import com.natureofcode.scene.Animated;
import com.natureofcode.scene.WindowDisp;
import com.natureofcode.vectors.PVector;
import java.awt.Color;
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
    public PVector acceleration;
    public float mass;

    public int diameter;
    private final float MAX_SPEED = 10;
    public final float G = 0.01f;

    public BouncingBall(PVector position, PVector speed, PVector acceleration, int diameter, float mass) {
        this.mass = mass;
        this.position = position;
        this.speed = speed;
        this.diameter = diameter;
        this.acceleration = acceleration;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval((int) position.x, (int) position.y, diameter, diameter);
        g.setColor(Color.DARK_GRAY);
        g.drawOval((int) position.x, (int) position.y, diameter, diameter);
        g.setColor(Color.BLUE);
        g.drawLine((int) this.position.x, (int) this.position.y, (int) (this.position.x + this.speed.x * 10), (int) (this.position.y + this.speed.y * 10));
    }

    @Override
    public void compute() {
        PVector sommeDesForces = new PVector(0, 0);
        //update ball position
        for (Animated current : f.getListAnimated()) {
            BouncingBall ball = (BouncingBall) current;
            PVector uAB = PVector.sub(position, ball.position);
            float AB = uAB.mag();
            float mAmB = this.mass * ball.mass;
            uAB.normalize();
            float premierterme = (AB == 0) ? 0 : -G * mAmB / AB;
            PVector graviteh = PVector.mult(uAB, premierterme);
            sommeDesForces.add(graviteh);
        }

//        PVector center = new PVector(WIDTH / 2, HEIGHT / 2);
//        float fakeMass = 50;
//        PVector uAB = PVector.sub(position, center);
//        float AB = uAB.mag();
//        float mAmB = this.mass * fakeMass;
//        uAB.normalize();
//        float premierterme = (AB == 0) ? 0 : -G * mAmB / AB;
//        PVector graviteh = PVector.mult(uAB, premierterme);
//        sommeDesForces.add(graviteh);
        applyForce(sommeDesForces);
        speed.add(acceleration);
//        speed.limit(MAX_SPEED);
        position.add(speed);

        //check for bouncing
//        if ((position.x > WIDTH - this.diameter) || (position.x < 0)) {
//            speed.x *= -1;
//        }
//        if ((position.y > HEIGHT - this.diameter) || (position.y < 0)) {
//            speed.y *= -1;
//        }
        //clearing acceleration
        acceleration.mult(0);
    }

    public void applyForce(PVector force) {
//        PVector f = PVector.div(force, this.mass);
        this.acceleration.add(force);
    }

    private static WindowDisp f;

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                f = new WindowDisp(WIDTH, HEIGHT);
                for (int i = 0; i < 100; i++) {
                    PVector position = PVector.random2D(20, 380);
                    BouncingBall ball = new BouncingBall(
                            position,
                            initialSpeed(new PVector(WIDTH / 2, HEIGHT / 2), position, 1),
                            new PVector(0, 0),
                            20,
                            10
                    );
                    f.addAnimated(ball);
                }
                f.start();
            }
        });
    }

    public static PVector initialSpeed(PVector origine, PVector ball, float k) {
        PVector OA = new PVector(ball.x - origine.x, ball.y - origine.y);
        return (OA.x == 0) ? new PVector(k, 0) : new PVector(k, -k * (OA.y / OA.x));
    }

}
