package com.natureofcode.vectors;

import java.util.Random;

/**
 *
 * @author syjebrane
 */
public class PVector {

    private static Random random;

    static {
        random = new Random();
    }

    public float x, y;

    public PVector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void add(PVector v) {
        this.x += v.x;
        this.y += v.y;
    }

    public static PVector add(PVector v1, PVector v2) {
        return new PVector(v1.x + v2.x, v1.y + v2.y);
    }

    public void sub(PVector v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    public static PVector sub(PVector v1, PVector v2) {
        return new PVector(v1.x - v2.x, v1.y - v2.y);
    }

    public void mult(float scale) {
        this.x *= scale;
        this.y *= scale;
    }

    public static PVector mult(PVector v, float scale) {
        return new PVector(v.x * scale, v.y * scale);
    }

    public void div(float scale) {
        if (scale != 0) {
            this.x /= scale;
            this.y /= scale;
        }
    }

    public static PVector div(PVector v, float scale) {
        return (scale == 0) ? v : new PVector(v.x / scale, v.y / scale);
    }

    public float mag() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public void normalize() {
        div(mag());
    }

    public void limit(float max) {
        if (mag() > max) {
            normalize();
            mult(max);
        }
    }

    public static PVector random2D(int min, int max) {
        PVector p = new PVector(
                random.nextInt(max + 1 - min) + min,
                random.nextInt(max + 1 - min) + min
        );
        return p;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Float.floatToIntBits(this.x);
        hash = 31 * hash + Float.floatToIntBits(this.y);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PVector other = (PVector) obj;
        if (Float.floatToIntBits(this.x) != Float.floatToIntBits(other.x)) {
            return false;
        }
        if (Float.floatToIntBits(this.y) != Float.floatToIntBits(other.y)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PVector{" + "x=" + x + ", y=" + y + '}';
    }

}
