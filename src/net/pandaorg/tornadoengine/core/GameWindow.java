package net.pandaorg.tornadoengine.core;

import net.pandaorg.tornadoengine.events.Event;
import net.pandaorg.tornadoengine.events.EventSystem;
import net.pandaorg.tornadoengine.graphics.DefaultRenderer;
import net.pandaorg.tornadoengine.graphics.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

/**
 * 游戏窗口类，负责创建和管理游戏窗口
 */
public class GameWindow extends JFrame {
    private final String originalTitle;
    private final Game game;
    private Renderer renderer;
    private BufferStrategy bufferStrategy;
    private int targetFPS = 60;
    private boolean vsync = false;

    public GameWindow(int width, int height, String title, Game game) {
        super(title);
        this.originalTitle = title;
        this.game = game;

        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        // 创建双缓冲
        createBufferStrategy(2);
        bufferStrategy = getBufferStrategy();

        // 初始化渲染器
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        renderer = new DefaultRenderer(g);

        // 添加输入监听器
        addKeyListener(new KeyInputHandler());
        addMouseListener(new MouseInputHandler());
        addMouseMotionListener(new MouseMotionInputHandler());
        addWindowListener(new WindowInputHandler());
    }

    /**
     * 获取渲染器
     * @return 渲染器
     */
    public Renderer getRenderer() {
        // 更新渲染器的图形上下文
        if (renderer instanceof DefaultRenderer) {
            Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
            ((DefaultRenderer) renderer).setGraphics(g);
        }
        return renderer;
    }

    /**
     * 显示渲染内容
     */
    public void display() {
        bufferStrategy.show();
    }

    /**
     * 检查窗口是否应该关闭
     * @return 是否应该关闭
     */
    public boolean shouldClose() {

        return true;
    }

    /**
     * 设置目标帧率
     * @param targetFPS 目标帧率
     */
    public void setTargetFPS(int targetFPS) {
        this.targetFPS = Math.max(1, targetFPS);
    }

    /**
     * 获取目标帧率
     * @return 目标帧率
     */
    public int getTargetFPS() {
        return targetFPS;
    }

    /**
     * 设置垂直同步
     * @param vsync 是否启用垂直同步
     */
    public void setVSync(boolean vsync) {
        this.vsync = vsync;
        // 实际应用中可能需要更复杂的实现来真正启用垂直同步
    }

    /**
     * 检查是否启用垂直同步
     * @return 是否启用垂直同步
     */
    public boolean isVSync() {
        return vsync;
    }

    /**
     * 获取原始标题
     * @return 原始标题
     */
    public String getOriginalTitle() {
        return originalTitle;
    }

    /**
     * 释放窗口资源
     */
    public void dispose() {
        super.dispose();
    }

    /**
     * 键盘输入处理器
     */
    private class KeyInputHandler implements KeyListener {
        @Override
        public void keyPressed(java.awt.event.KeyEvent e) {
            Event event = new net.pandaorg.tornadoengine.events.KeyEvent(Event.EventType.KEY_PRESSED, e.getKeyCode(), e.getKeyChar());
            EventSystem.getInstance().dispatchEvent(event);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            Event event = new net.pandaorg.tornadoengine.events.KeyEvent(Event.EventType.KEY_RELEASED, e.getKeyCode(), e.getKeyChar());
            EventSystem.getInstance().dispatchEvent(event);
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // 不需要处理
        }
    }

    /**
     * 鼠标输入处理器
     */
    private class MouseInputHandler implements MouseListener {
        @Override
        public void mousePressed(MouseEvent e) {
            Event event = new net.pandaorg.tornadoengine.events.MouseEvent(net.pandaorg.tornadoengine.events.Event.EventType.MOUSE_PRESSED, e.getX(), e.getY(), e.getButton());
            EventSystem.getInstance().dispatchEvent(event);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            Event event = new net.pandaorg.tornadoengine.events.MouseEvent(net.pandaorg.tornadoengine.events.Event.EventType.MOUSE_RELEASED, e.getX(), e.getY(), e.getButton());
            EventSystem.getInstance().dispatchEvent(event);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // 不需要处理
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // 不需要处理
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // 不需要处理
        }
    }

    /**
     * 鼠标移动处理器
     */
    private class MouseMotionInputHandler implements MouseMotionListener {
        @Override
        public void mouseMoved(MouseEvent e) {
            Event event = new net.pandaorg.tornadoengine.events.MouseEvent(Event.EventType.MOUSE_MOVED, e.getX(), e.getY(), 0);
            EventSystem.getInstance().dispatchEvent(event);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            // 可以在这里处理鼠标拖拽事件
        }
    }

    /**
     * 窗口事件处理器
     */
    private class WindowInputHandler implements WindowListener {
        @Override
        public void windowClosing(WindowEvent e) {
            Event event = new Event(Event.EventType.WINDOW_CLOSED);
            EventSystem.getInstance().dispatchEvent(event);
            game.stop();
        }

        @Override
        public void windowOpened(WindowEvent e) {
            // 不需要处理
        }

        @Override
        public void windowClosed(WindowEvent e) {
            // 不需要处理
        }

        @Override
        public void windowIconified(WindowEvent e) {
            // 不需要处理
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            // 不需要处理
        }

        @Override
        public void windowActivated(WindowEvent e) {
            // 不需要处理
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            // 不需要处理
        }
    }
}
