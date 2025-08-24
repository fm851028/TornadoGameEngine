package net.pandaorg.tornadoengine.physics;

import net.pandaorg.tornadoengine.math.Vector2f;

/**
 * 物理对象接口，继承自碰撞对象接口
 */
public interface PhysicsObject extends CollisionObject {
    /**
     * 获取质量
     * @return 质量
     */
    float getMass();

    /**
     * 设置质量
     * @param mass 质量
     */
    void setMass(float mass);

    /**
     * 获取速度
     * @return 速度向量
     */
    Vector2f getVelocity();

    /**
     * 设置速度
     * @param velocity 速度向量
     */
    void setVelocity(Vector2f velocity);

    /**
     * 获取加速度
     * @return 加速度向量
     */
    Vector2f getAcceleration();

    /**
     * 设置加速度
     * @param acceleration 加速度向量
     */
    void setAcceleration(Vector2f acceleration);

    /**
     * 应用力
     * @param force 力向量
     */
    default void applyForce(Vector2f force) {
        // F = ma => a = F/m
        Vector2f acceleration = force.divide(getMass());
        setAcceleration(getAcceleration().add(acceleration));
    }

    /**
     * 更新物理状态
     * @param deltaTime 帧间隔时间
     */
    default void updatePhysics(float deltaTime) {
        // 更新速度：v = v0 + a*t
        Vector2f newVelocity = getVelocity().add(getAcceleration().multiply(deltaTime));
        setVelocity(newVelocity);
    }
}
