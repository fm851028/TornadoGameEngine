package net.pandaorg.tornadoengine.sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * 声音类，封装声音资源
 */
public class Sound {
    private Clip clip;
    private float volume = 1.0f;

    public Sound(InputStream inputStream) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Failed to load sound");
            e.printStackTrace();
        }
    }

    /**
     * 播放声音
     */
    public void play() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    /**
     * 循环播放声音
     */
    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * 停止播放声音
     */
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    /**
     * 设置音量
     * @param volume 音量（0.0到1.0）
     */
    public void setVolume(float volume) {
        this.volume = Math.max(0.0f, Math.min(1.0f, volume));

        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (range * this.volume) + gainControl.getMinimum();
            gainControl.setValue(gain);
        }
    }

    /**
     * 获取音量
     * @return 音量（0.0到1.0）
     */
    public float getVolume() {
        return volume;
    }

    /**
     * 释放资源
     */
    public void dispose() {
        if (clip != null) {
            clip.close();
        }
    }
}
