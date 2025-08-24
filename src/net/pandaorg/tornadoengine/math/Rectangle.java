package net.pandaorg.tornadoengine.math;

/**
 * 矩形类，用于碰撞箱等
 */
public class Rectangle {
    private float x;
    private float y;
    private float width;
    private float height;

    public Rectangle() {
        this(0, 0, 0, 0);
    }

    public Rectangle(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * 检查点是否在矩形内
     * @param x 点的x坐标
     * @param y 点的y坐标
     * @return 是否在矩形内
     */
    public boolean contains(float x, float y) {
        return x >= this.x && x <= this.x + width &&
                y >= this.y && y <= this.y + height;
    }

    /**
     * 检查矩形是否与另一个矩形相交
     * @param other 另一个矩形
     * @return 是否相交
     */
    public boolean intersects(Rectangle other) {
        return x < other.x + other.width &&
                x + width > other.x &&
                y < other.y + other.height &&
                y + height > other.y;
    }

    // Getters and Setters
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
