package net.pandaorg.tornadoengine.physics;

import net.pandaorg.tornadoengine.core.GameObject;
import java.util.ArrayList;
import java.util.List;

/**
 * 物理系统，处理物理模拟和碰撞检测
 */
public class PhysicsSystem {
    private static PhysicsSystem instance;
    private List<PhysicsObject> physicsObjects;
    private List<CollisionObject> collisionObjects;
    private float gravity = 9.8f;
    private boolean gravityEnabled = true;

    private PhysicsSystem() {
        physicsObjects = new ArrayList<>();
        collisionObjects = new ArrayList<>();
    }

    /**
     * 获取物理系统单例
     * @return 物理系统实例
     */
    public static synchronized PhysicsSystem getInstance() {
        if (instance == null) {
            instance = new PhysicsSystem();
        }
        return instance;
    }

    /**
     * 添加物理对象
     * @param object 物理对象
     */
    public void addPhysicsObject(PhysicsObject object) {
        if (!physicsObjects.contains(object)) {
            physicsObjects.add(object);
            addCollisionObject(object);
        }
    }

    /**
     * 移除物理对象
     * @param object 物理对象
     */
    public void removePhysicsObject(PhysicsObject object) {
        physicsObjects.remove(object);
        removeCollisionObject(object);
    }

    /**
     * 添加碰撞对象
     * @param object 碰撞对象
     */
    public void addCollisionObject(CollisionObject object) {
        if (!collisionObjects.contains(object) && object != null) {
            collisionObjects.add(object);
        }
    }

    /**
     * 移除碰撞对象
     * @param object 碰撞对象
     */
    public void removeCollisionObject(CollisionObject object) {
        collisionObjects.remove(object);
    }

    /**
     * 更新物理系统
     * @param deltaTime 帧间隔时间
     */
    public void update(float deltaTime) {
        // 应用重力
        if (gravityEnabled) {
            applyGravity(deltaTime);
        }

        // 更新所有物理对象
        for (PhysicsObject object : physicsObjects) {
            if (object instanceof GameObject && !((GameObject) object).isActive()) {
                continue;
            }
            object.updatePhysics(deltaTime);
        }

        // 检测和处理碰撞
        detectCollisions();
    }

    /**
     * 应用重力
     * @param deltaTime 帧间隔时间
     */
    private void applyGravity(float deltaTime) {
        for (PhysicsObject object : physicsObjects) {
            if (object instanceof GameObject && !((GameObject) object).isActive()) {
                continue;
            }
            // 应用重力: F = m * g
            object.applyForce(new net.pandaorg.tornadoengine.math.Vector2f(0, object.getMass() * gravity));
        }
    }

    /**
     * 检测碰撞
     */
    private void detectCollisions() {
        for (int i = 0; i < collisionObjects.size(); i++) {
            CollisionObject a = collisionObjects.get(i);
            if (a instanceof GameObject && !((GameObject) a).isActive()) {
                continue;
            }

            for (int j = i + 1; j < collisionObjects.size(); j++) {
                CollisionObject b = collisionObjects.get(j);
                if (b instanceof GameObject && !((GameObject) b).isActive()) {
                    continue;
                }

                if (a.collidesWith(b)) {
                    a.onCollision(b);
                    b.onCollision(a);
                }
            }
        }
    }

    // Getters and Setters
    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public boolean isGravityEnabled() {
        return gravityEnabled;
    }

    public void setGravityEnabled(boolean gravityEnabled) {
        this.gravityEnabled = gravityEnabled;
    }

    public List<PhysicsObject> getPhysicsObjects() {
        return new ArrayList<>(physicsObjects);
    }

    public List<CollisionObject> getCollisionObjects() {
        return new ArrayList<>(collisionObjects);
    }
}
