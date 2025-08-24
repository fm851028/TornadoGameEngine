package net.pandaorg.tornadoengine.graphics;

import net.pandaorg.tornadoengine.math.Vector2f;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 渲染器接口，定义渲染相关方法
 */
public interface Renderer {
    /**
     * 清除屏幕
     * @param color 清除颜色
     */
    void clear(Color color);

    /**
     * 绘制矩形
     * @param x x坐标
     * @param y y坐标
     * @param width 宽度
     * @param height 高度
     * @param color 颜色
     */
    void drawRect(float x, float y, float width, float height, Color color);

    /**
     * 填充矩形
     * @param x x坐标
     * @param y y坐标
     * @param width 宽度
     * @param height 高度
     * @param color 颜色
     */
    void fillRect(float x, float y, float width, float height, Color color);

    /**
     * 绘制图像
     * @param image 图像
     * @param x x坐标
     * @param y y坐标
     */
    void drawImage(BufferedImage image, float x, float y);

    /**
     * 绘制图像（带缩放）
     * @param image 图像
     * @param x x坐标
     * @param y y坐标
     * @param width 宽度
     * @param height 高度
     */
    void drawImage(BufferedImage image, float x, float y, float width, float height);

    /**
     * 绘制文本
     * @param text 文本内容
     * @param x x坐标
     * @param y y坐标
     * @param color 颜色
     * @param font 字体
     */
    void drawText(String text, float x, float y, Color color, Font font);

    /**
     * 绘制线段
     * @param start 起点
     * @param end 终点
     * @param color 颜色
     * @param thickness 线宽
     */
    void drawLine(Vector2f start, Vector2f end, Color color, float thickness);

    /**
     * 获取图形上下文
     * @return 图形上下文
     */
    Graphics2D getGraphics();

    /**
     * 提交渲染指令，实际执行渲染
     */
    void render();
}
