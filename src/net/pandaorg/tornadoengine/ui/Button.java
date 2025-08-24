package net.pandaorg.tornadoengine.ui;

import net.pandaorg.tornadoengine.events.Event;
import net.pandaorg.tornadoengine.events.*;
import net.pandaorg.tornadoengine.graphics.Renderer;

import java.awt.*;
import java.util.function.Consumer;

/**
 * 按钮UI组件
 */
public class Button extends UIComponent {
    private String text;
    private Color normalColor = new Color(100, 100, 100);
    private Color hoverColor = new Color(150, 150, 150);
    private Color pressedColor = new Color(50, 50, 50);
    private Color textColor = Color.WHITE;
    private Font font = new Font("Arial", Font.PLAIN, 12);
    private Consumer<Button> onClick;
    private boolean pressed = false;

    public Button(float x, float y, float width, float height, String text) {
        super(x, y, width, height);
        this.text = text;
        EventSystem.getInstance().addListener(Event.EventType.MOUSE_PRESSED, this);
        EventSystem.getInstance().addListener(Event.EventType.MOUSE_RELEASED, this);
        EventSystem.getInstance().addListener(Event.EventType.MOUSE_MOVED, this);
    }

    @Override
    public void render(Renderer renderer) {
        if (!visible) return;

        // 选择按钮颜色
        Color currentColor = normalColor;
        if (pressed) {
            currentColor = pressedColor;
        } else if (hovered) {
            currentColor = hoverColor;
        }

        // 绘制按钮背景
        renderer.fillRect(x, y, width, height, currentColor);
        renderer.drawRect(x, y, width, height, Color.BLACK);

        // 绘制按钮文本
        FontMetrics metrics = renderer.getGraphics().getFontMetrics(font);
        float textX = x + (width - metrics.stringWidth(text)) / 2;
        float textY = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        renderer.drawText(text, textX, textY, textColor, font);
    }

    @Override
    public void onEvent(Event event) {
        if (!active) return;

        // 处理鼠标事件
        if (event instanceof MouseEvent) {
            MouseEvent mouseEvent = (MouseEvent) event;
            float mouseX = mouseEvent.getX();
            float mouseY = mouseEvent.getY();

            // 检查鼠标是否在按钮上
            boolean isMouseOver = mouseX >= x && mouseX <= x + width &&
                    mouseY >= y && mouseY <= y + height;

            switch (event.getType()) {
                case MOUSE_MOVED:
                    hovered = isMouseOver;
                    break;
                case MOUSE_PRESSED:
                    if (isMouseOver) {
                        pressed = true;
                        event.setHandled(true);
                    }
                    break;
                case MOUSE_RELEASED:
                    if (pressed && isMouseOver && onClick != null) {
                        onClick.accept(this);
                    }
                    pressed = false;
                    break;
            }
        }
    }

    // Getters and Setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getNormalColor() {
        return normalColor;
    }

    public void setNormalColor(Color normalColor) {
        this.normalColor = normalColor;
    }

    public Color getHoverColor() {
        return hoverColor;
    }

    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    public Color getPressedColor() {
        return pressedColor;
    }

    public void setPressedColor(Color pressedColor) {
        this.pressedColor = pressedColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setOnClick(Consumer<Button> onClick) {
        this.onClick = onClick;
    }
}
