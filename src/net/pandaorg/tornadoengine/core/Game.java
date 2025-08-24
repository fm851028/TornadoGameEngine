package net.pandaorg.tornadoengine.core;

import net.pandaorg.tornadoengine.events.EventSystem;
import net.pandaorg.tornadoengine.graphics.Renderer;
import net.pandaorg.tornadoengine.physics.PhysicsSystem;
import net.pandaorg.tornadoengine.sound.SoundManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 游戏基类，游戏主逻辑的入口
 */
public abstract class Game {
    protected GameWindow window;
    protected List<GameObject> gameObjects;
    protected boolean running;
    protected float fixedTimeStep = 1.0f / 60.0f; // 60 FPS 固定时间步长

    public Game(int width, int height, String title) {
        this.window = new GameWindow(width, height, title, this);
        this.gameObjects = new ArrayList<>();
        this.running = false;
    }

    /**
     * 初始化游戏
     */
    public abstract void init();

    /**
     * 游戏主循环
     */
    public void start() {
        if (running) return;
        running = true;

        init();

        long lastTime = System.nanoTime();
        double delta = 0.0;
        double nsPerFrame = 1000000000.0 / 60.0; // 60 FPS
        long timer = 0;
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerFrame;
            timer += now - lastTime;
            lastTime = now;

            // 固定时间步长更新物理
            while (delta >= 1) {
                update(fixedTimeStep);
                delta--;
            }

            // 渲染
            render(window.getRenderer());
            frames++;

            // 计算FPS
            if (timer >= 1000000000) {
                window.setTitle(window.getOriginalTitle() + " | FPS: " + frames);
                frames = 0;
                timer = 0;
            }

            // 处理窗口事件
            if (window.shouldClose()) {
                running = false;
            }
        }

        dispose();
    }

    /**
     * 更新游戏状态
     * @param deltaTime 帧间隔时间
     */
    public void update(float deltaTime) {
        // 更新物理系统
        PhysicsSystem.getInstance().update(deltaTime);

        // 更新所有游戏对象
        for (GameObject object : new ArrayList<>(gameObjects)) {
            if (object.isActive()) {
                object.update(deltaTime);
            }
        }
    }

    /**
     * 渲染游戏
     * @param renderer 渲染器
     */
    public abstract void render(Renderer renderer);

    /**
     * 添加游戏对象
     * @param object 游戏对象
     */
    public void addGameObject(GameObject object) {
        if (!gameObjects.contains(object)) {
            gameObjects.add(object);
        }
    }

    /**
     * 移除游戏对象
     * @param object 游戏对象
     */
    public void removeGameObject(GameObject object) {
        gameObjects.remove(object);
    }

    /**
     * 释放资源
     */
    public void dispose() {
        // 释放声音资源
        SoundManager.getInstance().dispose();

        // 关闭窗口
        window.dispose();
    }

    /**
     * 停止游戏
     */
    public void stop() {
        running = false;
    }

    // Getters and Setters
    public GameWindow getWindow() {
        return window;
    }

    public List<GameObject> getGameObjects() {
        return new ArrayList<>(gameObjects);
    }

    public float getFixedTimeStep() {
        return fixedTimeStep;
    }

    public void setFixedTimeStep(float fixedTimeStep) {
        this.fixedTimeStep = fixedTimeStep;
    }
}
