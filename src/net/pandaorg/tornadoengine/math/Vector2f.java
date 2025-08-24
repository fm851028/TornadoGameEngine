package net.pandaorg.tornadoengine.math;

/**
 * 2D向量类，用于位置、速度等计算
 */
public class Vector2f {
    private float x;
    private float y;

    public Vector2f() {
        this(0, 0);
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 向量加法
     * @param other 另一个向量
     * @return 新的向量
     */
    public Vector2f add(Vector2f other) {
        return new Vector2f(this.x + other.x, this.y + other.y);
    }

    /**
     * 向量减法
     * @param other 另一个向量
     * @return 新的向量
     */
    public Vector2f subtract(Vector2f other) {
        return new Vector2f(this.x - other.x, this.y - other.y);
    }

    /**
     * 向量乘法（标量）
     * @param scalar 标量
     * @return 新的向量
     */
    public Vector2f multiply(float scalar) {
        return new Vector2f(this.x * scalar, this.y * scalar);
    }

    /**
     * 向量除法（标量）
     * @param scalar 标量
     * @return 新的向量
     */
    public Vector2f divide(float scalar) {
        if (scalar != 0) {
            return new Vector2f(this.x / scalar, this.y / scalar);
        }
        return new Vector2f();
    }

    /**
     * 计算向量长度
     * @return 向量长度
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * 归一化向量
     * @return 归一化后的向量
     */
    public Vector2f normalize() {
        float length = length();
        if (length != 0) {
            return divide(length);
        }
        return new Vector2f();
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

    @Override
    public String toString() {
        return "Vector2f{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
