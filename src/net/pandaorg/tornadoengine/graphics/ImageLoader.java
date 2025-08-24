package net.pandaorg.tornadoengine.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 图像加载器，用于加载图像资源
 */
public class ImageLoader {
    /**
     * 从文件加载图像
     * @param path 文件路径
     * @return 加载的图像
     */
    public static BufferedImage loadImage(String path) {
        try {
            // 尝试从文件系统加载
            File file = new File(path);
            if (file.exists()) {
                return ImageIO.read(file);
            }

            // 尝试从类路径加载
            URL url = ImageLoader.class.getClassLoader().getResource(path);
            if (url != null) {
                return ImageIO.read(url);
            }

            throw new IOException("Image not found: " + path);
        } catch (IOException e) {
            System.err.println("Failed to load image: " + path);
            e.printStackTrace();
            return null;
        }
    }
}
