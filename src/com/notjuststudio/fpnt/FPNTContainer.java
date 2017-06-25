package com.notjuststudio.fpnt;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by George on 25.06.2017.
 */
public class FPNTContainer {

    private final Map<Byte, Map<String, Object>> maps = new HashMap<>();

    public Map<Byte, Map<String, Object>> getMaps() {
        return maps;
    }

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
        }else if (value instanceof String) {
            putString(key, (String) value);
        }
        return this;
    }

    FPNTContainer putValue(final byte type, final String key, final Object value) {
        maps.computeIfAbsent(type, k -> new HashMap<>());
        maps.get(type).put(key, value);
        return this;
    }

    FPNTContainer putBoolean(final String key, final boolean value) {
        return putValue(FPNTConstants.BOOLEAN, key, value);
    }

    FPNTContainer putByte(final String key, final byte value) {
        return putValue(FPNTConstants.BYTE, key, value);
    }

    FPNTContainer putChar(final String key, final char value) {
        return putValue(FPNTConstants.CHAR, key, value);
    }

    FPNTContainer putInt(final String key, final int value) {
        return putValue(FPNTConstants.INT, key, value);
    }

    FPNTContainer putLong(final String key, final long value) {
        return putValue(FPNTConstants.LONG, key, value);
    }

    FPNTContainer putBooleanArray(final String key, final boolean[] value) {
        return putValue(FPNTConstants.BOOLEAN_ARRAY, key, value);
    }

    FPNTContainer putByteArray(final String key, final byte[] value) {
        return putValue(FPNTConstants.BYTE_ARRAY, key, value);
    }

    FPNTContainer putCharArray(final String key, final char[] value) {
        return putValue(FPNTConstants.CHAR_ARRAY, key, value);
    }

    FPNTContainer putIntArray(final String key, final int[] value) {
        return putValue(FPNTConstants.INT_ARRAY, key, value);
    }

    FPNTContainer putLongArray(final String key, final long[] value) {
        return putValue(FPNTConstants.LONG_ARRAY, key, value);
    }

    FPNTContainer putString(final String key, final String value) {
        return putValue(FPNTConstants.STRING, key, value);
    }

    Object getValue(final String key) {
        for (Map.Entry<Byte, Map<String, Object>> map : maps.entrySet()) {
            final Object value = map.getValue().get(key);
            if (value != null)
                return value;
        }
        return null;
    }

    Object getValue(final byte type, final String key) {
        return maps.get(type).get(key);
    }

    boolean getBoolean(final String key) {
        return (boolean)getValue(FPNTConstants.BOOLEAN, key);
    }

    byte getByte(final String key) {
        return (byte)getValue(FPNTConstants.BYTE, key);
    }

    char getChar(final String key) {
        return (char)getValue(FPNTConstants.CHAR, key);
    }

    int getInt(final String key) {
        return (int)getValue(FPNTConstants.INT, key);
    }

    long getLong(final String key) {
        return (long)getValue(FPNTConstants.LONG, key);
    }

    boolean[] getBooleanArray(final String key) {
        return (boolean[])getValue(FPNTConstants.BOOLEAN_ARRAY, key);
    }

    byte[] getByteArray(final String key) {
        return (byte[])getValue(FPNTConstants.BYTE_ARRAY, key);
    }

    char[] getCharArray(final String key) {
        return (char[])getValue(FPNTConstants.CHAR_ARRAY, key);
    }

    int[] getIntArray(final String key) {
        return (int[])getValue(FPNTConstants.INT_ARRAY, key);
    }

    long[] getLongArray(final String key) {
        return (long[])getValue(FPNTConstants.LONG_ARRAY, key);
    }

    String getString(final String key) {
        return (String)getValue(FPNTConstants.STRING, key);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("[");
        for (Map.Entry<Byte, Map<String, Object>> map : maps.entrySet()) {
            builder.append(map.getKey()).append(":[");
            final Iterator<String> iterator = map.getValue().keySet().iterator();
            while (iterator.hasNext()) {
                builder.append(iterator.next());
                if (iterator.hasNext())
                    builder.append(", ");
            }
            builder.append("]");
        }
        builder.append("]");
        return builder.toString();
    }
}
