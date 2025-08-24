package net.pandaorg.tornadoengine.events;

/**
 * 鼠标事件类
 */
public class MouseEvent extends Event {
    private final float x;
    private final float y;
    private final int button;

    public MouseEvent(EventType type, float x, float y, int button) {
        super(type);
        this.x = x;
        this.y = y;
        this.button = button;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getButton() {
        return button;
    }
}
