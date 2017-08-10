package com.notjuststudio.fpnt;

import com.notjuststudio.utils.ImageUtils;
import com.sun.istack.internal.NotNull;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * FPNTParser have methods to parse values to bytes and
 * @author KLLdon
 */
public class FPNTParser {

    public static byte[] parse(@NotNull final Object value) {
        if (value instanceof Character) {
            return parse((char)value);
        } else if (value instanceof Integer) {
            return parse((int)value);
        } else if (value instanceof Long) {
            return parse((long)value);
        } else if (value instanceof Float) {
            return parse((float)value);
        } else if (value instanceof Double) {
            return parse((double)value);
        } else if (value instanceof boolean[]) {
            return parse((boolean[])value);
        }else if (value instanceof char[]) {
            return parse((char[])value);
        } else if (value instanceof int[]) {
            return parse((int[])value);
        } else if (value instanceof long[]) {
            return parse((long[])value);
        } else if (value instanceof float[]) {
            return parse((float[])value);
        } else if (value instanceof double[]) {
            return parse((double[])value);
        } else if (value instanceof String) {
            return parse((String)value);
        } else if (value instanceof String[]) {
            return parse((String[])value);
        } else if (value instanceof ByteBuffer) {
            return parse((ByteBuffer)value);
        } else if (value instanceof BufferedImage) {
            return parse((BufferedImage)value);
        }
        return null;
    }

    public static byte parse(@NotNull final boolean value) {
        return (byte)(value ? 1 : 0);
    }

    public static byte[] parse(@NotNull final char value) {
        final byte[] bytes = new byte[2];
        bytes[0] = (byte)((value >> 8) & 0xFF);
        bytes[1] = (byte) (value & 0xFF);
        return bytes;
    }

    public static byte[] parse(@NotNull final int value) {
        final byte[] bytes = new byte[4];
        bytes[0] = (byte)((value >> 24) & 0xFF);
        bytes[1] = (byte)((value >> 16) & 0xFF);
        bytes[2] = (byte)((value >> 8) & 0xFF);
        bytes[3] = (byte) (value & 0xFF);
        return bytes;
    }

    public static byte[] parse(@NotNull final long value) {
        final byte[] bytes = new byte[8];
        bytes[0] = (byte)((value >> 56) & 0xFF);
        bytes[1] = (byte)((value >> 48) & 0xFF);
        bytes[2] = (byte)((value >> 40) & 0xFF);
        bytes[3] = (byte)((value >> 32) & 0xFF);
        bytes[4] = (byte)((value >> 24) & 0xFF);
        bytes[5] = (byte)((value >> 16) & 0xFF);
        bytes[6] = (byte)((value >> 8) & 0xFF);
        bytes[7] = (byte) (value & 0xFF);
        return bytes;
    }

    public static byte[] parse(@NotNull final float value) {
        return parse(Float.floatToRawIntBits(value));
    }

    public static byte[] parse(@NotNull final double value) {
        return parse(Double.doubleToRawLongBits(value));
    }

    public static byte[] parse(@NotNull final boolean[] value) {
        final int length = value.length / 8 + ((value.length % 8) > 0 ? 1 : 0);
        final byte[] bytes = new byte[length];
        for (int i = 0; i < value.length; i++) {
            bytes[i / 8] = (byte)((bytes[i / 8]) | (parse(value[i]) << (7 - (i % 8))));
        }
        return bytes;
    }

    public static byte[] parse(@NotNull final char[] value) {
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

    public static byte[] parse(@NotNull final int[] value) {
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

    public static byte[] parse(@NotNull final long[] value) {
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

    public static byte[] parse(@NotNull final float[] value) {
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

    public static byte[] parse(@NotNull final double[] value) {
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

    public static byte[] parse(@NotNull final String value) {
        return value.getBytes(StandardCharsets.UTF_8);
    }

    public static byte[] parse(@NotNull final String[] value) {
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

    public static boolean parseBoolean(@NotNull final byte source) {
        return (source & 1) == 1;
    }

    public static char parseChar(@NotNull final byte[] source) {
        return (char)((((char)source[0] & 0xFF) << 8) | ((char)source[1] & 0xFF));
    }

    public static int parseInt(@NotNull final byte[] source) {
        return (((int)source[0] & 0xFF) << 24) | (((int)source[1] & 0xFF) << 16) | (((int)source[2] & 0xFF) << 8) | ((int)source[3] & 0xFF);
    }

    public static long parseLong(@NotNull final byte[] source) {
        return (((long)source[0] & 0xFF) << 56) | (((long)source[1] & 0xFF)  << 48)| (((long)source[2] & 0xFF) << 40) | (((long)source[3] & 0xFF) << 32) | (((long)source[4] & 0xFF) << 24) | (((long)source[5] & 0xFF) << 16) | (((long)source[6] & 0xFF) << 8) | (((long)source[7]) & 0xFF);
    }

    public static float parseFloat(@NotNull final byte[] source) {
        return Float.intBitsToFloat(parseInt(source));
    }

    public static double parseDouble(@NotNull final byte[] source) {
        return Double.longBitsToDouble(parseLong(source));
    }

    public static boolean[] parseBooleanArray(@NotNull final byte[] source,@NotNull  final int length) {
        final boolean[] booleans = new boolean[length];
        for (int i = 0; i < length; i++) {
            booleans[i] = parseBoolean((byte)(source[i / 8] >> (7 - (i % 8))));
        }
        return booleans;
    }

    public static boolean[] parseBooleanArray(@NotNull final Object[] source) {
        final boolean[] booleans = new boolean[source.length];
        for (int i = 0; i < source.length; i++) {
            booleans[i] = (boolean)source[i];
        }
        return booleans;
    }

    public static char[] parseCharArray(@NotNull final byte[] source) {
        final char[] chars = new char[source.length / 2];
        for (int i = 0; i < chars.length; i++) {
            final int offset = i * 2;
            chars[i] = parseChar(new byte[]{source[offset], source[offset + 1]});
        }
        return chars;
    }

    public static int[] parseIntArray(@NotNull final byte[] source) {
        final int[] ints = new int[source.length / 4];
        for (int i = 0; i < ints.length; i++) {
            final int offset = i * 4;
            ints[i] = parseInt(new byte[]{source[offset], source[offset + 1], source[offset + 2], source[offset + 3]});
        }
        return ints;
    }

    public static long[] parseLongArray(@NotNull final byte[] source) {
        final long[] longs = new long[source.length / 8];
        for (int i = 0; i < longs.length; i++) {
            final int offset = i * 8;
            longs[i] = parseLong(new byte[]{source[offset], source[offset + 1], source[offset + 2], source[offset + 3], source[offset + 4], source[offset + 5], source[offset + 6], source[offset + 7]});
        }
        return longs;
    }

    public static float[] parseFloatArray(@NotNull final byte[] source) {
        final float[] floats = new float[source.length / 4];
        for (int i = 0; i < floats.length; i++) {
            final int offset = i * 4;
            floats[i] = parseFloat(new byte[]{source[offset], source[offset + 1], source[offset + 2], source[offset + 3]});
        }
        return floats;
    }

    public static double[] parseDoubleArray(@NotNull final byte[] source) {
        final double[] doubles = new double[source.length / 8];
        for (int i = 0; i < doubles.length; i++) {
            final int offset = i * 8;
            doubles[i] = parseDouble(new byte[]{source[offset], source[offset + 1], source[offset + 2], source[offset + 3], source[offset + 4], source[offset + 5], source[offset + 6], source[offset + 7]});
        }
        return doubles;
    }

    public static String parseString(@NotNull final byte[] source) {
        return new String(source, StandardCharsets.UTF_8);
    }

    public static String[] parseStringArray(@NotNull final byte[] source) {
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

    public static byte[] parse(@NotNull final ByteBuffer buffer) {
        final byte[] buffered = new byte[buffer.remaining()];
        final int position = buffer.position();
        buffer.get(buffered);
        buffer.position(position);
        return  buffered;
    }

    public static ByteBuffer parseByteBuffer(@NotNull final byte[] data) {
        return (ByteBuffer) ByteBuffer.allocateDirect(data.length).order(ByteOrder.nativeOrder()).put(data).flip();
    }

    public static byte[] parse(@NotNull final BufferedImage image) {
        final byte[] source = ImageUtils.imageToArray(image);
        final byte[] bytes = new byte[source.length + 8];
        final byte[] width = parse(image.getWidth());
        final byte[] height = parse(image.getHeight());
        for (int i = 0; i < 4; i++) {
            bytes[i] = width[i];
            bytes[i + 4] = height[i];
        }
        for (int i = 0; i < source.length; i++) {
            bytes[i + 8] = source[i];
        }
        return bytes;
    }

    public static BufferedImage parseBufferedImage(@NotNull final byte[] source) {
        final byte[] width = new byte[4];
        final byte[] height = new byte[4];
        final byte[] bytes = new byte[source.length - 8];
        for (int i = 0; i < 4; i++) {
            width[i] = source[i];
            height[i] = source[i + 4];
        }
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = source[i + 8];
        }
        return ImageUtils.arrayToImage(bytes, parseInt(width), parseInt(height));
    }
}
