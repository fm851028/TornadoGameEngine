package net.pandaorg.tornadoengine.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 事件系统，管理事件的分发和监听
 */
public class EventSystem {
    private static EventSystem instance;
    private Map<Event.EventType, List<EventListener>> listeners;

    private EventSystem() {
        listeners = new HashMap<>();
        for (Event.EventType type : Event.EventType.values()) {
            listeners.put(type, new ArrayList<>());
        }
    }

    /**
     * 获取事件系统单例
     * @return 事件系统实例
     */
    public static synchronized EventSystem getInstance() {
        if (instance == null) {
            instance = new EventSystem();
        }
        return instance;
    }

    /**
     * 注册事件监听器
     * @param type 事件类型
     * @param listener 监听器
     */
    public void addListener(Event.EventType type, EventListener listener) {
        listeners.get(type).add(listener);
    }

    /**
     * 移除事件监听器
     * @param type 事件类型
     * @param listener 监听器
     */
    public void removeListener(Event.EventType type, EventListener listener) {
        listeners.get(type).remove(listener);
    }

    /**
     * 分发事件
     * @param event 事件对象
     */
    public void dispatchEvent(Event event) {
        for (EventListener listener : listeners.get(event.getType())) {
            if (!event.isHandled()) {
                listener.onEvent(event);
            } else {
                break;
            }
        }
    }
}
