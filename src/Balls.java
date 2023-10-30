import java.awt.*;

public class Balls {
    final private Point[] initPos;
    final private Point[] ballPos;
    final private int ballNumber = 5;

    public Balls() {
        initPos = new Point[ballNumber];
        ballPos = new Point[ballNumber];
        for (int i = 1; i <= ballNumber; i++) {
            initPos[i - 1] = new Point(100 * i, 500 - 100 * i);
            ballPos[i - 1] = new Point(100 * i, 500 - 100 * i);
        }
    }

    public Point[] getBallPos() {
        return this.ballPos;
    }

    public int getBallNumber() {
        return this.ballNumber;
    }

    public void translate(int dx, int dy) {
        for (Point point : ballPos) {
            point.translate(dx, dy);
        }
    }

    public void reInit() {
        System.arraycopy(initPos, 0, ballPos, 0, ballNumber);
    }

    public String toString() {
        StringBuilder ballCoordinates = new StringBuilder();
        for (int i = 0; i < ballNumber - 1; i++) {
            ballCoordinates.append(ballPos[i].toString()).append(" | ");
        }
        ballCoordinates.append(ballPos[ballNumber - 1].toString());
        return ballCoordinates.toString();
    }

}
