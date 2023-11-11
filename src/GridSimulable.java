import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;

public abstract class GridSimulable implements Simulable {
    final private GUISimulator gui;
    final private int windowSize;
    final private int GridWidth;

    /*
    cellSize must be odd and must divide windowSize
     */
    public GridSimulable(int windowSize, int width) {
        this.windowSize = windowSize;
        this.GridWidth = width;
        this.gui = new GUISimulator(this.windowSize, this.windowSize, Color.white, this);
    }

    @Override
    public abstract void next();

    @Override
    public abstract void restart();

    public void colorCell(Color color, int x, int y) {
        int cellSize = windowSize / GridWidth;
        this.gui.addGraphicalElement(new Rectangle((cellSize + 1) / 2 + x * cellSize, (cellSize + 1) / 2 + y * cellSize, Color.black, color, cellSize));
    }

    public void resetCell(int x, int y) {
        int cellSize = windowSize / GridWidth;
        this.gui.addGraphicalElement(new Rectangle((cellSize + 1) / 2 + x * cellSize, (cellSize + 1) / 2 + y * cellSize, Color.black, Color.lightGray, cellSize));
    }

    public int getGridWidth() {
        return GridWidth;
    }
}
