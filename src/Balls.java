import java.awt.Point;

public class Balls {
    private Point[] initPos;
    private Point[] ballPos;
    final private int ballNumber = 5;

    public Balls() {
        initPos = new Point[ballNumber];
        ballPos = new Point[ballNumber];
        for (int i = 1; i <= ballNumber; i++) {
            initPos[i - 1] = new Point(100 * i, 100 * i);
            ballPos[i - 1] = new Point(100 * i, 100 * i);
        }
    }

    public void translate(int dx, int dy) {
        for (Point point : ballPos) {
            point.translate(dx, dy);
        }
    }

    public void reInit() {
        for (int i = 0; i < ballNumber; i++) {
            ballPos[i] = initPos[i];
        }
    }

    public String toString() {
        StringBuilder ballCoordiantes = new StringBuilder();
        for (int i = 0; i < ballNumber - 1; i++) {
            ballCoordiantes.append(ballPos[i].toString() + " | ");
        }
        ballCoordiantes.append(ballPos[ballNumber - 1].toString());
        return ballCoordiantes.toString();
    }
}
