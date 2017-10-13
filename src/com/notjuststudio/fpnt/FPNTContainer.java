package com.notjuststudio.fpnt;

import com.notjuststudio.thread.ConcurrentHashSet;
import com.sun.istack.internal.NotNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author KLLdon
 */
public class FPNTContainer {

    private final Map<Byte, Map<String, Object>> maps = new ConcurrentHashMap<>();
    private final Set<FPNTExpander> expanderList = new ConcurrentHashSet<>();
    private int version = 0;

    /**
     * Get version
     * @return
     */
    public int getVersion() {
        return version;
    }

    /**
     * Set version
     * @param version
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Get map of maps
     * @return
     */
    public Map<Byte, Map<String, Object>> getMaps() {
        return new HashMap<>(maps);
    }

    /**
     * Empty constructor
     */
    public FPNTContainer() {}

    /**
     * Constructor with custom Expander
     * @param expander
     */
    public FPNTContainer(@NotNull final FPNTExpander expander) {
        addExpander(expander);
    }

    /**
     * Constructor with custom ExpanderList
     * @param expanderList
     */
    public FPNTContainer(@NotNull final Set<FPNTExpander> expanderList) {
        this.expanderList.addAll(expanderList);
    }

    /**
     * Get ExpanderList
     * @return
     */
    public Set<FPNTExpander> getExpanderList() {
        return new HashSet<>(expanderList);
    }

    /**
     * Add custom Expander
     * @param expander
     */
    public void addExpander(@NotNull final FPNTExpander expander) {
        this.expanderList.add(expander);
    }

    /**
     * Remove custom Expander
     * @param expander
     */
    public void removeExpander(@NotNull final FPNTExpander expander) {
        this.expanderList.remove(expander);
    }

    /**
     * Remove all Expander
     */
    public void clearExpanderList() {
        expanderList.clear();
    }

    /**
     * Put value in type map by key
     * @param type byte key for map
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putValue(@NotNull final byte type, @NotNull final String key, @NotNull final Object value) {
        maps.computeIfAbsent(type, k -> new ConcurrentHashMap<>());
        maps.get(type).put(key, value);
        return this;
    }

    /**
     * Get value by key
     * @param type byte key
     * @param key
     * @return value
     */
    public Object getValue(@NotNull final byte type, @NotNull final String key) {
        final Map<String, Object> map = maps.get(type);
        return map == null ? null : map.get(key);
    }

    /**
     * Get value by key or return default value
     * @param type byte key
     * @param key
     * @param value default
     * @return value
     */
    public Object getValue(@NotNull final byte type, @NotNull final String key, final Object value) {
        final Map<String, Object> map = maps.get(type);
        if (map == null)
            return value;
        final Object tmp = map.get(key);
        return tmp == null ? value : tmp;
    }

    /**
     * Remove and return value by key
     * @param type byte key
     * @param key
     * @return value
     */
    public Object removeValue(@NotNull final byte type, @NotNull final String key) {
        final Map<String, Object> map = maps.get(type);
        return map == null ? null : map.remove(key);
    }

    /**
     * Get available types
     * @return
     */
    public Set<Byte> getTypes() {
        return maps.keySet();
    }

    /**
     * Get map of type
     * @param type byte key
     * @return
     */
    public Map<String, Object> getTypeMap(@NotNull final byte type) {
        final Map<String, Object> map = maps.get(maps);
        final Map<String, Object> result = new HashMap<>();
        if (map != null)
            result.putAll(map);
        return result;
    }

    /**
     * Get available keys of type
     * @param type byte key
     * @return
     */
    public Set<String> getTypeKeys(@NotNull final byte type) {
        final Map<String, Object> map = maps.get(maps);
        final Set<String> keys = new HashSet<>();
        if (map != null)
            keys.addAll(map.keySet());
        return keys;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("[");
        final Iterator<Map.Entry<Byte, Map<String, Object>>> iterator = maps.entrySet().iterator();
        while(iterator.hasNext()) {
            final Map.Entry<Byte, Map<String, Object>> entry = iterator.next();
            final String name;
            switch (entry.getKey()) {
                case FPNTConstants.BOOLEAN: {
                    name = "Boolean";
                    break;
                }
                case FPNTConstants.BYTE: {
                    name = "Byte";
                    break;
                }
                case FPNTConstants.CHAR: {
                    name = "Character";
                    break;
                }
                case FPNTConstants.INT: {
                    name = "Integer";
                    break;
                }
                case FPNTConstants.LONG: {
                    name = "Long";
                    break;
                }
                case FPNTConstants.BOOLEAN_ARRAY: {
                    name = "Boolean[]";
                    break;
                }
                case FPNTConstants.BYTE_ARRAY: {
                    name = "Byte[]";
                    break;
                }
                case FPNTConstants.CHAR_ARRAY: {
                    name = "Character[]";
                    break;
                }
                case FPNTConstants.INT_ARRAY: {
                    name = "Integer[]";
                    break;
                }
                case FPNTConstants.LONG_ARRAY: {
                    name = "Long[]";
                    break;
                }
                default: {
                    name = Byte.toString(entry.getKey());
                }
            }
            builder.append(name).append(":").append(entry.getValue().size());
            if (iterator.hasNext())
                builder.append(",");
        }
        builder.append("]");
        return builder.toString();
    }
}
