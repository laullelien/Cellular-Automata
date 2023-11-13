import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;

public abstract class GridSimulable implements Simulable {
    final private GUISimulator gui;
    final private int windowSize;
    final private int GridWidth;
    final private EventManager manager = new EventManager();

    /*
    cellSize must be odd and must divide windowSize
     */
    public GridSimulable(int windowSize, int width) {
        this.windowSize = windowSize;
        this.GridWidth = width;
        this.gui = new GUISimulator(this.windowSize, this.windowSize, Color.white, this);
    }

    @Override
    public void next() {
        manager.next();
        manager.addEvent(getCurrentEvent());
    };

    @Override
    public void restart() {
        manager.restart();
        manager.addEvent(getStartingEvent());
        next();
    };

    public abstract Event getCurrentEvent();

    public abstract Event getStartingEvent();

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

    public EventManager getManager() {
        return manager;
    }
}
