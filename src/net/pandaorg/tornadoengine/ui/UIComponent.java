package net.pandaorg.tornadoengine.ui;

import net.pandaorg.tornadoengine.core.GameObject;
import net.pandaorg.tornadoengine.events.Event;
import net.pandaorg.tornadoengine.events.EventListener;
import net.pandaorg.tornadoengine.graphics.Renderer;

/**
 * UI组件基类
 */
public abstract class UIComponent extends GameObject implements EventListener {
    protected boolean focused = false;
    protected boolean hovered = false;
    protected UIComponent parent;

    public UIComponent(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public abstract void render(Renderer renderer);

    @Override
    public void update(float deltaTime) {
        // 可以在子类中重写
    }

    /**
     * 处理事件
     * @param event 事件
     */
    @Override
    public void onEvent(Event event) {
        // 可以在子类中重写
    }

    /**
     * 设置父组件
     * @param parent 父组件
     */
    public void setParent(UIComponent parent) {
        this.parent = parent;
    }

    /**
     * 获取父组件
     * @return 父组件
     */
    public UIComponent getParent() {
        return parent;
    }

    /**
     * 检查是否获得焦点
     * @return 是否获得焦点
     */
    public boolean isFocused() {
        return focused;
    }

    /**
     * 设置焦点状态
     * @param focused 焦点状态
     */
    public void setFocused(boolean focused) {
        this.focused = focused;
    }

    /**
     * 检查是否鼠标悬停
     * @return 是否鼠标悬停
     */
    public boolean isHovered() {
        return hovered;
    }

    /**
     * 设置鼠标悬停状态
     * @param hovered 鼠标悬停状态
     */
    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }
}
