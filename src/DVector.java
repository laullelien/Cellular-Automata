public class DVector {
    private double x;
    private double y;

    public DVector(double x, double y) {
        this.x = x;
        this.y = y;
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

    public static DVector mult(DVector v, int a) {
        double newX = a*v.x;
        double newY = a*v.y;
        return new DVector(newX, newY);
    }

    public static DVector mult(DVector v, double a) {
        double newX = a*v.x;
        double newY = a*v.y;
        return new DVector(newX, newY);
    }

    public void mult(double a) {
        this.x = this.x*a;
        this.y = this.y*a;
    }

    public static DVector add(DVector a, DVector b) {
        double newX = a.x + b.getX();
        double newY = a.y + b.getY();
        return new DVector(newX, newY);
    }

    public static DVector rotatePoint(DVector point, DVector vector, double angleDegrees) {
        double angleRadians = Math.toRadians(angleDegrees);
        double cosTheta = Math.cos(angleRadians);
        double sinTheta = Math.sin(angleRadians);

        double newX = cosTheta * (point.x - vector.x) - sinTheta * (point.y - vector.y) + vector.x;
        double newY = sinTheta * (point.x - vector.x) + cosTheta * (point.y - vector.y) + vector.y;

        return new DVector(newX, newY);
    }

    public static DVector minus(DVector a, DVector b){
        double newX = a.x - b.getX();
        double newY = a.y - b.getY();
        return new DVector(newX, newY);
    }
    
    public static double dotProduct(DVector v, DVector u) {
        return (u.x * v.x) + (u.y * v.y);
    }

    public double magnitude() {
        return Math.sqrt(x*x + y*y);
    }

    public DVector normalize() {
        this.mult(1/this.magnitude());
    }

}

