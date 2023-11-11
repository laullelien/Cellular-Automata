import java.awt.*;
import java.util.Random;

class DVectorTest {
    public static void main(String[] args) {
        System.out.print(DVector.rotate(new DVector(10, 10), 90));
    }
}

public class DVector {
    private double x;
    private double y;

    public DVector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static DVector mult(DVector v, int a) {
        double newX = a * v.x;
        double newY = a * v.y;
        return new DVector(newX, newY);
    }

    public static DVector mult(DVector v, double a) {
        double newX = a * v.x;
        double newY = a * v.y;
        return new DVector(newX, newY);
    }

    public static DVector add(DVector a, DVector b) {
        double newX = a.x + b.getX();
        double newY = a.y + b.getY();
        return new DVector(newX, newY);
    }

    public static DVector rotate(DVector vector, double angleDegrees) {
        double angleRadians = Math.toRadians(angleDegrees);
        double cos = Math.cos(angleRadians);
        double sin = Math.sin(angleRadians);

        double newX = vector.getX() * Math.cos(angleRadians) - vector.getY() * Math.sin(angleRadians);
        double newY = vector.getX() * Math.sin(angleRadians) + vector.getY() * Math.cos(angleRadians);

        return new DVector(newX, newY);
    }

    public static DVector minus(DVector a, DVector b) {
        double newX = a.x - b.getX();
        double newY = a.y - b.getY();
        return new DVector(newX, newY);
    }

    public static double dotProduct(DVector v, DVector u) {
        return (u.x * v.x) + (u.y * v.y);
    }

    public static Point toPoint(DVector vector) {
        return new Point((int) Math.round(vector.getX()), (int) Math.round(vector.getY()));
    }

    public static Point getCoordinates(DVector vector, int cellSize) {
        return new Point((int) Math.floor(vector.getX() / cellSize), (int) Math.floor(vector.getY() / cellSize));
    }

    public void add(DVector other) {
        this.x += other.getX();
        this.y += other.getY();
    }

    public void add(int scalar) {
        this.x += scalar;
        this.y += scalar;
    }
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void mult(double a) {
        this.x = this.x * a;
        this.y = this.y * a;
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public void selfNormalize() {
        this.mult(1 / this.magnitude());
    }

    public DVector normalize() {
        DVector normalized = new DVector(this.x, this.y);
        normalized.mult(1 / this.magnitude());
        return normalized;
    }

    public void randomize(int max) {
        Random r = new Random();

        x = max * r.nextDouble();
        y = max * r.nextDouble();
    }

    public void nullify() {
        this.x = 0;
        this.y = 0;
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y + "\n";
    }
}

