package net.pandaorg.tornadoengine.math;

/**
 * 2x2矩阵类，用于旋转等变换
 */
public class Matrix2f {
    private float m00, m01;
    private float m10, m11;

    public Matrix2f() {
        this(1, 0, 0, 1); // 单位矩阵
    }

    public Matrix2f(float m00, float m01, float m10, float m11) {
        this.m00 = m00;
        this.m01 = m01;
        this.m10 = m10;
        this.m11 = m11;
    }

    /**
     * 创建旋转矩阵
     * @param angle 角度（弧度）
     * @return 旋转矩阵
     */
    public static Matrix2f createRotationMatrix(float angle) {
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);
        return new Matrix2f(cos, -sin, sin, cos);
    }

    /**
     * 矩阵与向量相乘
     * @param vector 向量
     * @return 新的向量
     */
    public Vector2f multiply(Vector2f vector) {
        float x = m00 * vector.getX() + m01 * vector.getY();
        float y = m10 * vector.getX() + m11 * vector.getY();
        return new Vector2f(x, y);
    }

    /**
     * 矩阵与矩阵相乘
     * @param other 另一个矩阵
     * @return 新的矩阵
     */
    public Matrix2f multiply(Matrix2f other) {
        float newM00 = m00 * other.m00 + m01 * other.m10;
        float newM01 = m00 * other.m01 + m01 * other.m11;
        float newM10 = m10 * other.m00 + m11 * other.m10;
        float newM11 = m10 * other.m01 + m11 * other.m11;
        return new Matrix2f(newM00, newM01, newM10, newM11);
    }

    // Getters and Setters
    public float getM00() {
        return m00;
    }

    public void setM00(float m00) {
        this.m00 = m00;
    }

    public float getM01() {
        return m01;
    }

    public void setM01(float m01) {
        this.m01 = m01;
    }

    public float getM10() {
        return m10;
    }

    public void setM10(float m10) {
        this.m10 = m10;
    }

    public float getM11() {
        return m11;
    }

    public void setM11(float m11) {
        this.m11 = m11;
    }
}
