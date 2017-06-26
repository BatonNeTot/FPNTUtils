package com.notjuststudio.fpnt;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * FPNTParser have methods to parse values to bytes and
 * @author KLLdon
 */
public class FPNTParser {

    public static byte[] parse(final Object value) {
        if (value instanceof Character) {
            return parse((char)value);
        } else if (value instanceof Integer) {
            return parse((int)value);
        } else if (value instanceof Long) {
            return parse((long)value);
        } else if (value instanceof boolean[]) {
            return parse((boolean[])value);
        }else if (value instanceof char[]) {
            return parse((char[])value);
        } else if (value instanceof int[]) {
            return parse((int[])value);
        } else if (value instanceof long[]) {
            return parse((long[])value);
        } else if (value instanceof String) {
            return parse((String)value);
        } else if (value instanceof String[]) {
            return parse((String[])value);
        }
        return null;
    }

    public static byte parse(final boolean value) {
        return (byte)(value ? 1 : 0);
    }

    public static byte[] parse(final char value) {
        final byte[] bytes = new byte[2];
        bytes[0] = (byte)(value << 8);
        bytes[1] = (byte) value;
        return bytes;
    }

    public static byte[] parse(final int value) {
        final byte[] bytes = new byte[4];
        bytes[0] = (byte)(value << 24);
        bytes[1] = (byte)(value << 16);
        bytes[2] = (byte)(value << 8);
        bytes[3] = (byte) value;
        return bytes;
    }

    public static byte[] parse(final long value) {
        final byte[] bytes = new byte[8];
        bytes[0] = (byte)(value << 56);
        bytes[1] = (byte)(value << 48);
        bytes[2] = (byte)(value << 40);
        bytes[3] = (byte)(value << 32);
        bytes[4] = (byte)(value << 24);
        bytes[5] = (byte)(value << 16);
        bytes[6] = (byte)(value << 8);
        bytes[7] = (byte) value;
        return bytes;
    }

    public static byte[] parse(final boolean[] value) {
        final int length = value.length / 8 + ((value.length % 8) > 0 ? 1 : 0);
        final byte[] bytes = new byte[length];
        for (int i = 0; i < value.length; i++) {
            bytes[i / 8] = (byte)((bytes[i / 8]) | (parse(value[i]) << (7 - (i % 8))));
        }
        return bytes;
    }

    public static byte[] parse(final char[] value) {
        final int length = value.length * 2;
        final byte[] bytes = new byte[length];
        for (int i = 0; i < value.length; i++) {
            final byte[] tmp = parse(value[i]);
            final int offset = i * 2;
            bytes[offset] = tmp[0];
            bytes[offset + 1] = tmp[1];
        }
        return bytes;
    }

    public static byte[] parse(final int[] value) {
        final int length = value.length * 4;
        final byte[] bytes = new byte[length];
        for (int i = 0; i < value.length; i++) {
            final byte[] tmp = parse(value[i]);
            final int offset = i * 4;
            bytes[offset] = tmp[0];
            bytes[offset + 1] = tmp[1];
            bytes[offset + 2] = tmp[2];
            bytes[offset + 3] = tmp[3];
        }
        return bytes;
    }

    public static byte[] parse(final long[] value) {
        final int length = value.length * 8;
        final byte[] bytes = new byte[length];
        for (int i = 0; i < value.length; i++) {
            final byte[] tmp = parse(value[i]);
            final int offset = i * 8;
            bytes[offset] = tmp[0];
            bytes[offset + 1] = tmp[1];
            bytes[offset + 2] = tmp[2];
            bytes[offset + 3] = tmp[3];
            bytes[offset + 4] = tmp[4];
            bytes[offset + 5] = tmp[5];
            bytes[offset + 6] = tmp[6];
            bytes[offset + 7] = tmp[7];
        }
        return bytes;
    }

    public static byte[] parse(final String value) {
        return value.getBytes(StandardCharsets.UTF_8);
    }

    public static byte[] parse(final String[] value) {
        int sum = 0;
        List<byte[]> bytes = new ArrayList<>();
        for (String str : value) {
            final byte[] string = parse(str);
            sum += string.length + 4;
            bytes.add(string);
        }
        final byte[] result = new byte[sum];
        int count = 0;
        for (byte[] array : bytes) {
            for (byte values : parse(array.length))
                result[count++] = values;
            for (byte values : array)
                result[count++] = values;
        }
        return result;
    }

    public static boolean parseBoolean(final byte source) {
        return (source & 1) == 1;
    }

    public static char parseChar(final byte[] source) {
        return (char)(((char)source[0] << 8) | ((char)source[1]));
    }

    public static int parseInt(final byte[] source) {
        return (source[0] << 24) | (source[1] << 16) | (source[2] << 8) | (source[3]);
    }

    public static long parseLong(final byte[] source) {
        return ((long)source[0] << 56) | ((long)source[1] << 48) | ((long)source[2] << 40) | ((long)source[3] << 32) | ((long)source[4] << 24) | ((long)source[5] << 16) | ((long)source[6] << 8) | ((long)source[7]);
    }

    public static boolean[] parseBooleanArray(final byte[] source, final int length) {
        final boolean[] booleans = new boolean[length];
        for (int i = 0; i < length; i++) {
            booleans[i] = parseBoolean((byte)(source[i / 8] >>> (7 - (i % 8))));
        }
        return booleans;
    }

    public static boolean[] parseBooleanArray(final Object[] source) {
        final boolean[] booleans = new boolean[source.length];
        for (int i = 0; i < source.length; i++) {
            booleans[i] = (boolean)source[i];
        }
        return booleans;
    }

    public static char[] parseCharArray(final byte[] source) {
        final char[] chars = new char[source.length / 2];
        for (int i = 0; i < chars.length; i++) {
            final int offset = i * 2;
            chars[i] = parseChar(new byte[]{source[offset], source[offset + 1]});
        }
        return chars;
    }

    public static int[] parseIntArray(final byte[] source) {
        final int[] ints = new int[source.length / 4];
        for (int i = 0; i < ints.length; i++) {
            final int offset = i * 4;
            ints[i] = parseInt(new byte[]{source[offset], source[offset + 1], source[offset + 2], source[offset + 3]});
        }
        return ints;
    }

    public static long[] parseLongArray(final byte[] source) {
        final long[] longs = new long[source.length / 8];
        for (int i = 0; i < longs.length; i++) {
            final int offset = i * 8;
            longs[i] = parseLong(new byte[]{source[offset], source[offset + 1], source[offset + 2], source[offset + 3], source[offset + 4], source[offset + 5], source[offset + 6], source[offset + 7]});
        }
        return longs;
    }

    public static String parseString(final byte[] source) {
        return new String(source, StandardCharsets.UTF_8);
    }

    public static String[] parseStringArray(final byte[] source) {
        List<String> result = new ArrayList<>();
        int count = 0;
        final byte[] size = new byte[4];

        while(count < source.length) {
            for (int i = 0; i < 4; i++)
                size[i] = source[count++];

            final int length = parseInt(size);
            final byte[] string = new byte[length];
            for (int i = 0; i < length; i++) {
                string[i] = source[count++];
            }

            result.add(parseString(string));
        }

        return result.toArray(new String[result.size()]);
    }
}
