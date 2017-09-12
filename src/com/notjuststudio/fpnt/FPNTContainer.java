package com.notjuststudio.fpnt;

import com.sun.istack.internal.NotNull;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.*;

/**
 * @author KLLdon
 */
public class FPNTContainer implements Cloneable{

    private final Map<Byte, Map<String, Object>> maps = new HashMap<>();
    private final List<FPNTExpander> expanderList = new ArrayList<>();
    private int version = 0;

    @Override
    public FPNTContainer clone() throws CloneNotSupportedException {
        final FPNTContainer container = new FPNTContainer(new ArrayList<>(this.expanderList));
        for (Map.Entry<Byte, Map<String, Object>> map : this.maps.entrySet()) {
            final Map<String, Object> hash = new HashMap<>(map.getValue());
            container.maps.put(map.getKey(), hash);
        }
        return container;
    }

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
    public FPNTContainer(@NotNull final FPNTExpander expander) {
        addExpander(expander);
    }

    /**
     * Constructor with custom ExpanderList
     * @param expanderList
     */
    public FPNTContainer(@NotNull final List<FPNTExpander> expanderList) {
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
    public void addExpander(@NotNull final FPNTExpander expander) {
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
    public FPNTContainer putValue(@NotNull final String key, @NotNull final Object value) {
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
        } else if (value instanceof Float) {
            putFloat(key, (float) value);
        } else if (value instanceof Double) {
            putDouble(key, (double) value);
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
        } else if (value instanceof float[]) {
            putFloatArray(key, (float[]) value);
        } else if (value instanceof double[]) {
            putDoubleArray(key, (double[]) value);
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
    public FPNTContainer putValue(@NotNull final byte type, @NotNull final String key, @NotNull final Object value) {
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
    public FPNTContainer putBoolean(@NotNull final String key, @NotNull final boolean value) {
        return putValue(FPNTConstants.BOOLEAN, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putByte(@NotNull final String key, @NotNull final byte value) {
        return putValue(FPNTConstants.BYTE, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putChar(@NotNull final String key, @NotNull final char value) {
        return putValue(FPNTConstants.CHAR, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putInt(@NotNull final String key, @NotNull final int value) {
        return putValue(FPNTConstants.INT, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putLong(@NotNull final String key, @NotNull final long value) {
        return putValue(FPNTConstants.LONG, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putFloat(@NotNull final String key, @NotNull final float value) {
        return putValue(FPNTConstants.FLOAT, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putDouble(@NotNull final String key, @NotNull final double value) {
        return putValue(FPNTConstants.DOUBLE, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putBooleanArray(@NotNull final String key, @NotNull final boolean[] value) {
        return putValue(FPNTConstants.BOOLEAN_ARRAY, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putByteArray(@NotNull final String key, @NotNull final byte[] value) {
        return putValue(FPNTConstants.BYTE_ARRAY, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putCharArray(@NotNull final String key, @NotNull final char[] value) {
        return putValue(FPNTConstants.CHAR_ARRAY, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putIntArray(@NotNull final String key, @NotNull final int[] value) {
        return putValue(FPNTConstants.INT_ARRAY, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putLongArray(@NotNull final String key, @NotNull final long[] value) {
        return putValue(FPNTConstants.LONG_ARRAY, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putFloatArray(@NotNull final String key, @NotNull final float[] value) {
        return putValue(FPNTConstants.FLOAT_ARRAY, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putDoubleArray(@NotNull final String key, @NotNull final double[] value) {
        return putValue(FPNTConstants.DOUBLE_ARRAY, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putString(@NotNull final String key, @NotNull final String value) {
        return putValue(FPNTConstants.STRING, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putStringArray(@NotNull final String key, @NotNull final String[] value) {
        return putValue(FPNTConstants.STRING_ARRAY, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putByteBuffer(@NotNull final String key, @NotNull final ByteBuffer value) {
        return putValue(FPNTConstants.BYTE_BUFFER, key, value);
    }

    /**
     * Put value in map by key
     * @param key
     * @param value
     * @return this
     */
    public FPNTContainer putBufferedImage(@NotNull final String key, @NotNull final BufferedImage value) {
        return putValue(FPNTConstants.BUFFERED_IMAGE, key, value);
    }

    /**
     * Get first value from maps by key
     * @param key
     * @return value
     */
    public Object getValue(@NotNull final String key) {
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
    public Object getValue(@NotNull final byte type, @NotNull final String key) {
        final Map<String, Object> map = maps.get(type);
        return map == null ? null : map.get(key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public boolean getBoolean(@NotNull final String key) {
        return (boolean)getValue(FPNTConstants.BOOLEAN, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public byte getByte(@NotNull final String key) {
        return (byte)getValue(FPNTConstants.BYTE, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public char getChar(@NotNull final String key) {
        return (char)getValue(FPNTConstants.CHAR, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public float getFloat(@NotNull final String key) {
        return (float)getValue(FPNTConstants.FLOAT, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public double getDouble(@NotNull final String key) {
        return (double)getValue(FPNTConstants.DOUBLE, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public int getInt(@NotNull final String key) {
        return (int)getValue(FPNTConstants.INT, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public long getLong(@NotNull final String key) {
        return (long)getValue(FPNTConstants.LONG, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public boolean[] getBooleanArray(@NotNull final String key) {
        return (boolean[])getValue(FPNTConstants.BOOLEAN_ARRAY, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public byte[] getByteArray(@NotNull final String key) {
        return (byte[])getValue(FPNTConstants.BYTE_ARRAY, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public char[] getCharArray(@NotNull final String key) {
        return (char[])getValue(FPNTConstants.CHAR_ARRAY, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public int[] getIntArray(@NotNull final String key) {
        return (int[])getValue(FPNTConstants.INT_ARRAY, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public long[] getLongArray(@NotNull final String key) {
        return (long[])getValue(FPNTConstants.LONG_ARRAY, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public float[] getFloatArray(@NotNull final String key) {
        return (float[])getValue(FPNTConstants.FLOAT_ARRAY, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public double[] getDoubleArray(@NotNull final String key) {
        return (double[])getValue(FPNTConstants.DOUBLE_ARRAY, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public String getString(@NotNull final String key) {
        return (String)getValue(FPNTConstants.STRING, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public String[] getStringArray(@NotNull final String key) {
        return (String[])getValue(FPNTConstants.STRING_ARRAY, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public ByteBuffer getByteBuffer(@NotNull final String key) {
        return (ByteBuffer)getValue(FPNTConstants.BYTE_BUFFER, key);
    }

    /**
     * Get value by key
     * @param key
     * @return value
     */
    public BufferedImage getBufferedImage(@NotNull final String key) {
        return (BufferedImage)getValue(FPNTConstants.BUFFERED_IMAGE, key);
    }

    /**
     * Get first value from maps by key or return default value
     * @param key
     * @param value default
     * @return value
     */
    public Object getValue(@NotNull final String key, final Object value) {
        for (Map.Entry<Byte, Map<String, Object>> map : maps.entrySet()) {
            final Object tmp = map.getValue().get(key);
            if (tmp != null)
                return tmp;
        }
        return value;
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
     * Get value by key or return default value
     * @param key
     * @param value default
     * @return value
     */
    public boolean getBoolean(@NotNull final String key, final boolean value) {
        return (boolean)getValue(FPNTConstants.BOOLEAN, key, value);
    }

    /**
     * Get value by key or return default value
     * @param key
     * @param value default
     * @return value
     */
    public byte getByte(@NotNull final String key, final byte value) {
        return (byte)getValue(FPNTConstants.BYTE, key, value);
    }

    /**
     * Get value by key or return default value
     * @param key
     * @param value default
     * @return value
     */
    public char getChar(@NotNull final String key, final char value) {
        return (char)getValue(FPNTConstants.CHAR, key, value);
    }

    /**
     * Get value by key or return default value
     * @param key
     * @param value default
     * @return value
     */
    public int getInt(@NotNull final String key, final int value) {
        return (int)getValue(FPNTConstants.INT, key, value);
    }

    /**
     * Get value by key or return default value
     * @param key
     * @param value default
     * @return value
     */
    public long getLong(@NotNull final String key, final long value) {
        return (long)getValue(FPNTConstants.LONG, key, value);
    }

    /**
     * Get value by key or return default value
     * @param key
     * @param value default
     * @return value
     */
    public float getFloat(@NotNull final String key, final float value) {
        return (float)getValue(FPNTConstants.FLOAT, key, value);
    }

    /**
     * Get value by key or return default value
     * @param key
     * @param value default
     * @return value
     */
    public double getDouble(@NotNull final String key, final double value) {
        return (double)getValue(FPNTConstants.DOUBLE, key, value);
    }

    /**
     * Get value by key or return default value
     * @param key
     * @param value default
     * @return value
     */
    public boolean[] getBooleanArray(@NotNull final String key, final boolean[] value) {
        return (boolean[])getValue(FPNTConstants.BOOLEAN_ARRAY, key, value);
    }

    /**
     * Get value by key or return default value
     * @param key
     * @param value default
     * @return value
     */
    public byte[] getByteArray(@NotNull final String key, final byte[] value) {
        return (byte[])getValue(FPNTConstants.BYTE_ARRAY, key, value);
    }

    /**
     * Get value by key or return default value
     * @param key
     * @param value default
     * @return value
     */
    public char[] getCharArray(@NotNull final String key, final char[] value) {
        return (char[])getValue(FPNTConstants.CHAR_ARRAY, key, value);
    }

    /**
     * Get value by key or return default value
     * @param key
     * @param value default
     * @return value
     */
    public int[] getIntArray(@NotNull final String key, final int[] value) {
        return (int[])getValue(FPNTConstants.INT_ARRAY, key, value);
    }

    /**
     * Get value by key or return default value
     * @param key
     * @param value default
     * @return value
     */
    public long[] getLongArray(@NotNull final String key, final long[] value) {
        return (long[])getValue(FPNTConstants.LONG_ARRAY, key, value);
    }

    /**
     * Get value by key or return default value
     * @param key
     * @param value default
     * @return value
     */
    public float[] getFloatArray(@NotNull final String key, final float[] value) {
        return (float[])getValue(FPNTConstants.FLOAT_ARRAY, key, value);
    }

    /**
     * Get value by key or return default value
     * @param key
     * @param value default
     * @return value
     */
    public double[] getDoubleArray(@NotNull final String key, final double[] value) {
        return (double[])getValue(FPNTConstants.DOUBLE_ARRAY, key, value);
    }

    /**
     * Get value by key or return default value
     * @param key
     * @param value default
     * @return value
     */
    public String getString(@NotNull final String key, final String value) {
        return (String)getValue(FPNTConstants.STRING, key, value);
    }

    /**
     * Get value by key or return default value
     * @param key
     * @param value default
     * @return value
     */
    public String[] getStringArray(@NotNull final String key, final String[] value) {
        return (String[])getValue(FPNTConstants.STRING_ARRAY, key, value);
    }

    /**
     * Get value by key or return default value
     * @param key
     * @param value default
     * @return value
     */
    public ByteBuffer getByteBuffer(@NotNull final String key, final ByteBuffer value) {
        return (ByteBuffer)getValue(FPNTConstants.BYTE_BUFFER, key, value);
    }

    /**
     * Get value by key or return default value
     * @param key
     * @param value default
     * @return value
     */
    public BufferedImage getBufferedImage(@NotNull final String key, final BufferedImage value) {
        return (BufferedImage)getValue(FPNTConstants.BUFFERED_IMAGE, key, value);
    }

    /**
     * Remove and return first value from maps by key
     * @param key
     * @return value
     */
    public Object removeValue(@NotNull final String key) {
        for (Map.Entry<Byte, Map<String, Object>> map : maps.entrySet()) {
            final Object value = map.getValue().get(key);
            if (value != null) {
                map.getValue().remove(key);
                return value;
            }
        }
        return null;
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
     * Remove and return value by key
     * @param key
     * @return value
     */
    public boolean removeBoolean(@NotNull final String key) {
        return (boolean)removeValue(FPNTConstants.BOOLEAN, key);
    }

    /**
     * Remove and return value by key
     * @param key
     * @return value
     */
    public byte removeByte(@NotNull final String key) {
        return (byte)removeValue(FPNTConstants.BYTE, key);
    }

    /**
     * Remove and return value by key
     * @param key
     * @return value
     */
    public char removeChar(@NotNull final String key) {
        return (char)removeValue(FPNTConstants.CHAR, key);
    }

    /**
     * Remove and return value by key
     * @param key
     * @return value
     */
    public int removeInt(@NotNull final String key) {
        return (int)removeValue(FPNTConstants.INT, key);
    }

    /**
     * Remove and return value by key
     * @param key
     * @return value
     */
    public long removeLong(@NotNull final String key) {
        return (long)removeValue(FPNTConstants.LONG, key);
    }

    /**
     * Remove and return value by key
     * @param key
     * @return value
     */
    public boolean[] removeBooleanArray(@NotNull final String key) {
        return (boolean[])removeValue(FPNTConstants.BOOLEAN_ARRAY, key);
    }

    /**
     * Remove and return value by key
     * @param key
     * @return value
     */
    public byte[] removeByteArray(@NotNull final String key) {
        return (byte[])removeValue(FPNTConstants.BYTE_ARRAY, key);
    }

    /**
     * Remove and return value by key
     * @param key
     * @return value
     */
    public char[] removeCharArray(@NotNull final String key) {
        return (char[])removeValue(FPNTConstants.CHAR_ARRAY, key);
    }

    /**
     * Remove and return value by key
     * @param key
     * @return value
     */
    public int[] removeIntArray(@NotNull final String key) {
        return (int[])removeValue(FPNTConstants.INT_ARRAY, key);
    }

    /**
     * Remove and return value by key
     * @param key
     * @return value
     */
    public long[] removeLongArray(@NotNull final String key) {
        return (long[])removeValue(FPNTConstants.LONG_ARRAY, key);
    }

    /**
     * Remove and return value by key
     * @param key
     * @return value
     */
    public String removeString(@NotNull final String key) {
        return (String)removeValue(FPNTConstants.STRING, key);
    }

    /**
     * Remove and return value by key
     * @param key
     * @return value
     */
    public String[] removeStringArray(@NotNull final String key) {
        return (String[])removeValue(FPNTConstants.STRING_ARRAY, key);
    }

    /**
     * Remove and return value by key
     * @param key
     * @return value
     */
    public ByteBuffer removeByteBuffer(@NotNull final String key) {
        return (ByteBuffer)removeValue(FPNTConstants.BYTE_BUFFER, key);
    }

    /**
     * Remove and return value by key
     * @param key
     * @return value
     */
    public BufferedImage removeBufferedImage(@NotNull final String key) {
        return (BufferedImage)removeValue(FPNTConstants.BUFFERED_IMAGE, key);
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
    public Set<String> getTypeKeys(@NotNull final byte type) {
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
