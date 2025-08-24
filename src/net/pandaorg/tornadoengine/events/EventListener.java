package net.pandaorg.tornadoengine.events;

/**
 * 事件监听器接口
 */
public interface EventListener {
    /**
     * 处理事件
     * @param event 事件对象
     */
    void onEvent(Event event);
}
