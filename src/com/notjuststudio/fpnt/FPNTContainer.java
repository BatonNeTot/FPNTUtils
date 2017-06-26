package com.notjuststudio.fpnt;

import java.util.*;

/**
 * Created by George on 25.06.2017.
 */
public class FPNTContainer {

    private final Map<Byte, Map<String, Object>> maps = new HashMap<>();
    private final List<FPNTExpander> expander = new ArrayList<>();

    public Map<Byte, Map<String, Object>> getMaps() {
        return maps;
    }

    public List<FPNTExpander> getExpander() {
        return new ArrayList<>(expander);
    }

    public void addExpander(FPNTExpander expander) {
        this.expander.add(expander);
    }

    public void removeExpander() {
        expander.clear();
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

    public FPNTContainer putValue(final byte type, final String key, final Object value) {
        maps.computeIfAbsent(type, k -> new HashMap<>());
        maps.get(type).put(key, value);
        return this;
    }

    public FPNTContainer putBoolean(final String key, final boolean value) {
        return putValue(FPNTConstants.BOOLEAN, key, value);
    }

    public FPNTContainer putByte(final String key, final byte value) {
        return putValue(FPNTConstants.BYTE, key, value);
    }

    public FPNTContainer putChar(final String key, final char value) {
        return putValue(FPNTConstants.CHAR, key, value);
    }

    public FPNTContainer putInt(final String key, final int value) {
        return putValue(FPNTConstants.INT, key, value);
    }

    public FPNTContainer putLong(final String key, final long value) {
        return putValue(FPNTConstants.LONG, key, value);
    }

    public FPNTContainer putBooleanArray(final String key, final boolean[] value) {
        return putValue(FPNTConstants.BOOLEAN_ARRAY, key, value);
    }

    public FPNTContainer putByteArray(final String key, final byte[] value) {
        return putValue(FPNTConstants.BYTE_ARRAY, key, value);
    }

    public FPNTContainer putCharArray(final String key, final char[] value) {
        return putValue(FPNTConstants.CHAR_ARRAY, key, value);
    }

    public FPNTContainer putIntArray(final String key, final int[] value) {
        return putValue(FPNTConstants.INT_ARRAY, key, value);
    }

    public FPNTContainer putLongArray(final String key, final long[] value) {
        return putValue(FPNTConstants.LONG_ARRAY, key, value);
    }

    public FPNTContainer putString(final String key, final String value) {
        return putValue(FPNTConstants.STRING, key, value);
    }

    public Object getValue(final String key) {
        for (Map.Entry<Byte, Map<String, Object>> map : maps.entrySet()) {
            final Object value = map.getValue().get(key);
            if (value != null)
                return value;
        }
        return null;
    }

    public Object getValue(final byte type, final String key) {
        return maps.get(type).get(key);
    }

    public boolean getBoolean(final String key) {
        return (boolean)getValue(FPNTConstants.BOOLEAN, key);
    }

    public byte getByte(final String key) {
        return (byte)getValue(FPNTConstants.BYTE, key);
    }

    public char getChar(final String key) {
        return (char)getValue(FPNTConstants.CHAR, key);
    }

    public int getInt(final String key) {
        return (int)getValue(FPNTConstants.INT, key);
    }

    public long getLong(final String key) {
        return (long)getValue(FPNTConstants.LONG, key);
    }

    public boolean[] getBooleanArray(final String key) {
        return (boolean[])getValue(FPNTConstants.BOOLEAN_ARRAY, key);
    }

    public byte[] getByteArray(final String key) {
        return (byte[])getValue(FPNTConstants.BYTE_ARRAY, key);
    }

    public char[] getCharArray(final String key) {
        return (char[])getValue(FPNTConstants.CHAR_ARRAY, key);
    }

    public int[] getIntArray(final String key) {
        return (int[])getValue(FPNTConstants.INT_ARRAY, key);
    }

    public long[] getLongArray(final String key) {
        return (long[])getValue(FPNTConstants.LONG_ARRAY, key);
    }

    public String getString(final String key) {
        return (String)getValue(FPNTConstants.STRING, key);
    }

    public Set<Byte> getTypes() {
        return maps.keySet();
    }

    public Set<String> getTypeKeys(final byte type) {
        return maps.get(type).keySet();
    }

    public Set<String> getBooleanKeys() {
        return getTypeKeys(FPNTConstants.BOOLEAN);
    }

    public Set<String> getByteKeys() {
        return getTypeKeys(FPNTConstants.BYTE);
    }

    public Set<String> getCharKeys() {
        return getTypeKeys(FPNTConstants.CHAR);
    }

    public Set<String> getIntKeys() {
        return getTypeKeys(FPNTConstants.INT);
    }

    public Set<String> getLongKeys() {
        return getTypeKeys(FPNTConstants.LONG);
    }

    public Set<String> getBooleaArraynKeys() {
        return getTypeKeys(FPNTConstants.BOOLEAN_ARRAY);
    }

    public Set<String> getByteArrayKeys() {
        return getTypeKeys(FPNTConstants.BYTE_ARRAY);
    }

    public Set<String> getCharArrayKeys() {
        return getTypeKeys(FPNTConstants.CHAR_ARRAY);
    }

    public Set<String> getIntArrayKeys() {
        return getTypeKeys(FPNTConstants.INT_ARRAY);
    }

    public Set<String> getLongArrayKeys() {
        return getTypeKeys(FPNTConstants.LONG_ARRAY);
    }

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
                    name = "None";
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
