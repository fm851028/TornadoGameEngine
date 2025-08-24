package net.pandaorg.tornadoengine.physics;

import net.pandaorg.tornadoengine.math.Rectangle;
import java.util.List;

/**
 * 碰撞对象接口，定义碰撞相关方法
 */
public interface CollisionObject {
    /**
     * 获取所有碰撞箱
     * @return 碰撞箱列表
     */
    List<Rectangle> getCollisionBoxes();

    /**
     * 处理碰撞事件
     * @param other 碰撞的另一个对象
     */
    void onCollision(CollisionObject other);

    /**
     * 检查是否与另一个碰撞对象发生碰撞
     * @param other 另一个碰撞对象
     * @return 是否发生碰撞
     */
    default boolean collidesWith(CollisionObject other) {
        List<Rectangle> thisBoxes = getCollisionBoxes();
        List<Rectangle> otherBoxes = other.getCollisionBoxes();

        for (Rectangle thisBox : thisBoxes) {
            for (Rectangle otherBox : otherBoxes) {
                if (checkAABBCollision(thisBox, otherBox)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * AABB碰撞检测
     * @param a 第一个矩形
     * @param b 第二个矩形
     * @return 是否碰撞
     */
    default boolean checkAABBCollision(Rectangle a, Rectangle b) {
        return a.getX() < b.getX() + b.getWidth() &&
                a.getX() + a.getWidth() > b.getX() &&
                a.getY() < b.getY() + b.getHeight() &&
                a.getY() + a.getHeight() > b.getY();
    }
}
