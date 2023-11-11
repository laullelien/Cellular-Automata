import gui.GraphicalElement;

import java.awt.*;

public class Triangle implements GraphicalElement {
    int[] abscissa;
    int[] ordinate;
    Color color;

    public Triangle(Point top, Point right, Point left, Color color) {
        abscissa = new int[3];
        ordinate = new int[3];
        abscissa[0] = top.x;
        abscissa[1] = right.x;
        abscissa[2] = left.x;
        ordinate[0] = top.y;
        ordinate[1] = right.y;
        ordinate[2] = left.y;
        this.color = color;
    }

    public void paint(Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.fillPolygon(abscissa, ordinate, 3);
    }
}
