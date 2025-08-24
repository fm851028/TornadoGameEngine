package net.pandaorg.tornadoengine.sound;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 声音管理器，管理所有声音资源
 */
public class SoundManager {
    private static SoundManager instance;
    private Map<String, Sound> sounds;

    private SoundManager() {
        sounds = new HashMap<>();
    }

    /**
     * 获取声音管理器单例
     * @return 声音管理器实例
     */
    public static synchronized SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    /**
     * 加载声音
     * @param key 声音的键，用于后续访问
     * @param inputStream 声音输入流
     */
    public void loadSound(String key, InputStream inputStream) {
        try {
            Sound sound = new Sound(inputStream);
            sounds.put(key, sound);
        } catch (Exception e) {
            System.err.println("Failed to load sound: " + key);
            e.printStackTrace();
        }
    }

    /**
     * 加载声音
     * @param key 声音的键
     * @param path 声音文件路径
     */
    public void loadSound(String key, String path) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path)) {
            if (inputStream != null) {
                loadSound(key, inputStream);
            } else {
                throw new IOException("Sound file not found: " + path);
            }
        } catch (IOException e) {
            System.err.println("Failed to load sound: " + key);
            e.printStackTrace();
        }
    }

    /**
     * 获取声音
     * @param key 声音的键
     * @return 声音对象
     */
    public Sound getSound(String key) {
        return sounds.get(key);
    }

    /**
     * 播放声音
     * @param key 声音的键
     */
    public void playSound(String key) {
        Sound sound = sounds.get(key);
        if (sound != null) {
            sound.play();
        }
    }

    /**
     * 循环播放声音
     * @param key 声音的键
     */
    public void loopSound(String key) {
        Sound sound = sounds.get(key);
        if (sound != null) {
            sound.loop();
        }
    }

    /**
     * 停止播放声音
     * @param key 声音的键
     */
    public void stopSound(String key) {
        Sound sound = sounds.get(key);
        if (sound != null) {
            sound.stop();
        }
    }

    /**
     * 设置所有声音的音量
     * @param volume 音量（0.0到1.0）
     */
    public void setMasterVolume(float volume) {
        for (Sound sound : sounds.values()) {
            sound.setVolume(volume);
        }
    }

    /**
     * 释放所有声音资源
     */
    public void dispose() {
        for (Sound sound : sounds.values()) {
            sound.dispose();
        }
        sounds.clear();
    }
}
