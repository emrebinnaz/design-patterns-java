package Structural.Flyweight;

import java.awt.*;

public final class TreeType {
   /* Flyweight objects must be immutable because our factory can only guarantee that
   it has remembered the correct object if it can also guarantee that the object it originally created has not been modified.*/

    private final String name;
    private final Color color;
    private final String otherTreeData;

    public TreeType(String name, Color color, String otherTreeData) {
        this.name = name;
        this.color = color;
        this.otherTreeData = otherTreeData;
    }

    public void draw(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillRect(x - 1, y, 3, 5);
        g.setColor(color);
        g.fillOval(x - 5, y - 10, 10, 10);
    }
}
