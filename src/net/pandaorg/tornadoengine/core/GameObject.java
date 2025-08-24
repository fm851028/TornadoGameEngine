package net.pandaorg.tornadoengine.core;

import net.pandaorg.tornadoengine.graphics.Renderer;

import java.util.UUID;

/**
 * 游戏对象基类，所有游戏对象的父类
 */
public abstract class GameObject {
    protected String id;
    protected float x, y;
    protected float width, height;
    protected boolean visible = true;
    protected boolean active = true;

    public GameObject(float x, float y, float width, float height) {
        this.id = UUID.randomUUID().toString();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * 渲染游戏对象
     * @param renderer 渲染器
     */
    public abstract void render(Renderer renderer);

    /**
     * 更新游戏对象状态
     * @param deltaTime 帧间隔时间
     */
    public void update(float deltaTime) {}

    // Getters and Setters
    public String getId() {
        return id;
    }

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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
