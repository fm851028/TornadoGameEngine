package net.pandaorg.tornadoengine.config;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 配置管理器，用于保存和加载游戏数据
 */
public class ConfigManager {
    private static ConfigManager instance;
    private Map<String, Properties> configs;
    private String configDirectory;

    private ConfigManager() {
        configs = new HashMap<>();
        configDirectory = "config/";

        // 创建配置目录（如果不存在）
        File dir = new File(configDirectory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * 获取配置管理器单例
     * @return 配置管理器实例
     */
    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    /**
     * 加载配置文件
     * @param configName 配置文件名（不含路径和扩展名）
     * @return 是否加载成功
     */
    public boolean loadConfig(String configName) {
        Properties properties = new Properties();
        String fileName = configDirectory + configName + ".properties";

        try (InputStream input = new FileInputStream(fileName)) {
            properties.load(input);
            configs.put(configName, properties);
            return true;
        } catch (IOException e) {
            // 如果文件不存在，创建一个新的配置
            configs.put(configName, new Properties());
            return false;
        }
    }

    /**
     * 保存配置文件
     * @param configName 配置文件名（不含路径和扩展名）
     * @return 是否保存成功
     */
    public boolean saveConfig(String configName) {
        Properties properties = configs.get(configName);
        if (properties == null) {
            return false;
        }

        String fileName = configDirectory + configName + ".properties";

        try (OutputStream output = new FileOutputStream(fileName)) {
            properties.store(output, configName + " configuration");
            return true;
        } catch (IOException e) {
            System.err.println("Failed to save config: " + configName);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取配置值
     * @param configName 配置文件名
     * @param key 键
     * @param defaultValue 默认值
     * @return 配置值或默认值
     */
    public String getString(String configName, String key, String defaultValue) {
        Properties properties = configs.get(configName);
        if (properties == null) {
            loadConfig(configName);
            properties = configs.get(configName);
        }
        return properties.getProperty(key, defaultValue);
    }

    /**
     * 设置配置值
     * @param configName 配置文件名
     * @param key 键
     * @param value 值
     */
    public void setString(String configName, String key, String value) {
        Properties properties = configs.get(configName);
        if (properties == null) {
            properties = new Properties();
            configs.put(configName, properties);
        }
        properties.setProperty(key, value);
    }

    /**
     * 获取整数配置值
     * @param configName 配置文件名
     * @param key 键
     * @param defaultValue 默认值
     * @return 整数配置值或默认值
     */
    public int getInt(String configName, String key, int defaultValue) {
        try {
            String value = getString(configName, key, String.valueOf(defaultValue));
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 设置整数配置值
     * @param configName 配置文件名
     * @param key 键
     * @param value 值
     */
    public void setInt(String configName, String key, int value) {
        setString(configName, key, String.valueOf(value));
    }

    /**
     * 获取浮点数配置值
     * @param configName 配置文件名
     * @param key 键
     * @param defaultValue 默认值
     * @return 浮点数配置值或默认值
     */
    public float getFloat(String configName, String key, float defaultValue) {
        try {
            String value = getString(configName, key, String.valueOf(defaultValue));
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 设置浮点数配置值
     * @param configName 配置文件名
     * @param key 键
     * @param value 值
     */
    public void setFloat(String configName, String key, float value) {
        setString(configName, key, String.valueOf(value));
    }

    /**
     * 获取布尔值配置值
     * @param configName 配置文件名
     * @param key 键
     * @param defaultValue 默认值
     * @return 布尔值配置值或默认值
     */
    public boolean getBoolean(String configName, String key, boolean defaultValue) {
        String value = getString(configName, key, String.valueOf(defaultValue));
        return Boolean.parseBoolean(value);
    }

    /**
     * 设置布尔值配置值
     * @param configName 配置文件名
     * @param key 键
     * @param value 值
     */
    public void setBoolean(String configName, String key, boolean value) {
        setString(configName, key, String.valueOf(value));
    }

    /**
     * 设置配置文件目录
     * @param directory 目录路径
     */
    public void setConfigDirectory(String directory) {
        this.configDirectory = directory;
        if (!this.configDirectory.endsWith("/")) {
            this.configDirectory += "/";
        }

        File dir = new File(this.configDirectory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
