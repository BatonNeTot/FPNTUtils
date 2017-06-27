package com.notjuststudio.fpnt;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.*;

/**
 * @author KLLdon
 */
public class FPNTContainer {

    private final Map<Byte, Map<String, Object>> maps = new HashMap<>();
    private final List<FPNTExpander> expanderList = new ArrayList<>();

    /**
     * Get map of map
     * @return
     */
    public Map<Byte, Map<String, Object>> getMaps() {
        return maps;
    }

    /**
     * Empty constructor
     */
    public FPNTContainer() {}

    /**
     * Constructor with custom Expander
     * @param expander
     */
    public FPNTContainer(FPNTExpander expander) {
        addExpander(expander);
    }

    /**
     * Constructor with custom ExpanderList
     * @param expanderList
     */
    public FPNTContainer(List<FPNTExpander> expanderList) {
        this.expanderList.addAll(expanderList);
    }

    /**
     * Get ExpanderList
     * @return
     */
    public List<FPNTExpander> getExpanderList() {
        return new ArrayList<>(expanderList);
    }

    /**
     * Add custom Expander
     * @param expander
     */
    public void addExpander(FPNTExpander expander) {
        this.expanderList.add(expander);
    }

    /**
     * Remove all Expander
     */
    public void clearExpanderList() {
        expanderList.clear();
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putValue(final String key, final Object value) {
        if (value instanceof Boolean) {
            putBoolean(key, (boolean) value);
        } else if (value instanceof Byte) {
            putByte(key, (byte) value);
        } else if (value instanceof Character) {
            putChar(key, (char) value);
        } else if (value instanceof Integer) {
            putInt(key, (int) value);
        } else if (value instanceof Long) {
            putLong(key, (long) value);
        } else if (value instanceof boolean[]) {
            putBooleanArray(key, (boolean[]) value);
        } else if (value instanceof byte[]) {
            putByteArray(key, (byte[]) value);
        } else if (value instanceof char[]) {
            putCharArray(key, (char[]) value);
        } else if (value instanceof int[]) {
            putIntArray(key, (int[]) value);
        } else if (value instanceof long[]) {
            putLongArray(key, (long[]) value);
        } else if (value instanceof String) {
            putString(key, (String) value);
        } else if (value instanceof String[]) {
            putStringArray(key, (String[]) value);
        } else if (value instanceof ByteBuffer) {
            putByteBuffer(key, (ByteBuffer) value);
        } else if (value instanceof BufferedImage) {
            putBufferedImage(key, (BufferedImage) value);
        }
        return this;
    }

    /**
     * Put value in type map by key
     * @param type byte key for map
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putValue(final byte type, final String key, final Object value) {
        maps.computeIfAbsent(type, k -> new HashMap<>());
        maps.get(type).put(key, value);
        return this;
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putBoolean(final String key, final boolean value) {
        return putValue(FPNTConstants.BOOLEAN, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putByte(final String key, final byte value) {
        return putValue(FPNTConstants.BYTE, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putChar(final String key, final char value) {
        return putValue(FPNTConstants.CHAR, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putInt(final String key, final int value) {
        return putValue(FPNTConstants.INT, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putLong(final String key, final long value) {
        return putValue(FPNTConstants.LONG, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putBooleanArray(final String key, final boolean[] value) {
        return putValue(FPNTConstants.BOOLEAN_ARRAY, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putByteArray(final String key, final byte[] value) {
        return putValue(FPNTConstants.BYTE_ARRAY, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putCharArray(final String key, final char[] value) {
        return putValue(FPNTConstants.CHAR_ARRAY, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putIntArray(final String key, final int[] value) {
        return putValue(FPNTConstants.INT_ARRAY, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putLongArray(final String key, final long[] value) {
        return putValue(FPNTConstants.LONG_ARRAY, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putString(final String key, final String value) {
        return putValue(FPNTConstants.STRING, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putStringArray(final String key, final String[] value) {
        return putValue(FPNTConstants.STRING_ARRAY, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putByteBuffer(final String key, final ByteBuffer value) {
        return putValue(FPNTConstants.BYTE_BUFFER, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putBufferedImage(final String key, final BufferedImage value) {
        return putValue(FPNTConstants.BUFFERED_IMAGE, key, value);
    }

    /**
     * Get first value from maps by key
     * @param key
     * @return value
     */
    public Object getValue(final String key) {
        for (Map.Entry<Byte, Map<String, Object>> map : maps.entrySet()) {
            final Object value = map.getValue().get(key);
            if (value != null)
                return value;
        }
        return null;
    }

    /**
     * Get value by key
     * @param type byte key
     * @param key
     * @return value
     */
    public Object getValue(final byte type, final String key) {
        return maps.get(type).get(key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public boolean getBoolean(final String key) {
        return (boolean)getValue(FPNTConstants.BOOLEAN, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public byte getByte(final String key) {
        return (byte)getValue(FPNTConstants.BYTE, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public char getChar(final String key) {
        return (char)getValue(FPNTConstants.CHAR, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public int getInt(final String key) {
        return (int)getValue(FPNTConstants.INT, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public long getLong(final String key) {
        return (long)getValue(FPNTConstants.LONG, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public boolean[] getBooleanArray(final String key) {
        return (boolean[])getValue(FPNTConstants.BOOLEAN_ARRAY, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public byte[] getByteArray(final String key) {
        return (byte[])getValue(FPNTConstants.BYTE_ARRAY, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public char[] getCharArray(final String key) {
        return (char[])getValue(FPNTConstants.CHAR_ARRAY, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public int[] getIntArray(final String key) {
        return (int[])getValue(FPNTConstants.INT_ARRAY, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public long[] getLongArray(final String key) {
        return (long[])getValue(FPNTConstants.LONG_ARRAY, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public String getString(final String key) {
        return (String)getValue(FPNTConstants.STRING, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public String[] getStringArray(final String key) {
        return (String[])getValue(FPNTConstants.STRING_ARRAY, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public String[] getByteBuffer(final String key) {
        return (String[])getValue(FPNTConstants.BYTE_BUFFER, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public String[] getBufferedImage(final String key) {
        return (String[])getValue(FPNTConstants.BUFFERED_IMAGE, key);
    }

    /**
     * Get available types
     * @return
     */
    public Set<Byte> getTypes() {
        return maps.keySet();
    }

    /**
     * Get available keys of type
     * @param type byte key
     * @return
     */
    public Set<String> getTypeKeys(final byte type) {
        return maps.get(type).keySet();
    }

    /**
     * Get available keys
     * @return
     */
    public Set<String> getBooleanKeys() {
        return getTypeKeys(FPNTConstants.BOOLEAN);
    }

    /**
     * Get available keys
     * @return
     */
    public Set<String> getByteKeys() {
        return getTypeKeys(FPNTConstants.BYTE);
    }

    /**
     * Get available keys
     * @return
     */
    public Set<String> getCharKeys() {
        return getTypeKeys(FPNTConstants.CHAR);
    }

    /**
     * Get available keys
     * @return
     */
    public Set<String> getIntKeys() {
        return getTypeKeys(FPNTConstants.INT);
    }

    /**
     * Get available keys
     * @return
     */
    public Set<String> getLongKeys() {
        return getTypeKeys(FPNTConstants.LONG);
    }

    /**
     * Get available keys
     * @return
     */
    public Set<String> getBooleaArraynKeys() {
        return getTypeKeys(FPNTConstants.BOOLEAN_ARRAY);
    }

    /**
     * Get available keys
     * @return
     */
    public Set<String> getByteArrayKeys() {
        return getTypeKeys(FPNTConstants.BYTE_ARRAY);
    }

    /**
     * Get available keys
     * @return
     */
    public Set<String> getCharArrayKeys() {
        return getTypeKeys(FPNTConstants.CHAR_ARRAY);
    }

    /**
     * Get available keys
     * @return
     */
    public Set<String> getIntArrayKeys() {
        return getTypeKeys(FPNTConstants.INT_ARRAY);
    }

    /**
     * Get available keys
     * @return
     */
    public Set<String> getLongArrayKeys() {
        return getTypeKeys(FPNTConstants.LONG_ARRAY);
    }

    /**
     * Get available keys
     * @return
     */
    public Set<String> getStringKeys() {
        return getTypeKeys(FPNTConstants.STRING);
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
