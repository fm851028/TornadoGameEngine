package net.pandaorg.tornadoengine.graphics;

import net.pandaorg.tornadoengine.math.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 默认渲染器实现
 */
public class DefaultRenderer implements Renderer {
    private Graphics2D g;

    public DefaultRenderer(Graphics2D g) {
        this.g = g;
    }

    @Override
    public void clear(Color color) {
        g.setColor(color);
        g.fillRect(0, 0, g.getDeviceConfiguration().getBounds().width,
                g.getDeviceConfiguration().getBounds().height);
    }

    @Override
    public void drawRect(float x, float y, float width, float height, Color color) {
        g.setColor(color);
        g.drawRect((int)x, (int)y, (int)width, (int)height);
    }

    @Override
    public void fillRect(float x, float y, float width, float height, Color color) {
        g.setColor(color);
        g.fillRect((int)x, (int)y, (int)width, (int)height);
    }

    @Override
    public void drawImage(BufferedImage image, float x, float y) {
        if (image != null) {
            g.drawImage(image, (int)x, (int)y, null);
        }
    }

    @Override
    public void drawImage(BufferedImage image, float x, float y, float width, float height) {
        if (image != null) {
            g.drawImage(image, (int)x, (int)y, (int)width, (int)height, null);
        }
    }

    @Override
    public void drawText(String text, float x, float y, Color color, Font font) {
        g.setColor(color);
        g.setFont(font);
        g.drawString(text, x, y);
    }

    @Override
    public void drawLine(Vector2f start, Vector2f end, Color color, float thickness) {
        g.setColor(color);
        g.setStroke(new BasicStroke(thickness));
        g.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
    }

    @Override
    public Graphics2D getGraphics() {
        return g;
    }

    @Override
    public void render() {
        // 在默认实现中，绘制操作直接作用于Graphics2D，这里不需要额外操作
    }

    public void setGraphics(Graphics2D g) {
        this.g = g;
    }
}
