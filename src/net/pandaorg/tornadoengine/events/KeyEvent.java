package net.pandaorg.tornadoengine.events;

/**
 * 键盘事件类
 */
public class KeyEvent extends Event {
    private final int keyCode;
    private final char keyChar;

    public KeyEvent(EventType type, int keyCode, char keyChar) {
        super(type);
        this.keyCode = keyCode;
        this.keyChar = keyChar;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public char getKeyChar() {
        return keyChar;
    }
}
