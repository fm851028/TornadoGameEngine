package net.pandaorg.tornadoengine.events;

/**
 * 事件基类
 */
public class Event {
    private boolean handled = false;
    private final EventType type;

    public Event(EventType type) {
        this.type = type;
    }

    public EventType getType() {
        return type;
    }

    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    public enum EventType {
        KEY_PRESSED, KEY_RELEASED,
        MOUSE_PRESSED, MOUSE_RELEASED, MOUSE_MOVED,
        WINDOW_RESIZED, WINDOW_CLOSED,
        COLLISION
    }
}
