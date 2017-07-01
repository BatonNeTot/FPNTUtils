package com.notjuststudio.fpnt;

/**
 * FPNTConstants contains constants
 * @author KLLdon
 */
public class FPNTConstants {

    public static final char MAGIC_NUMBER = 0xFA1A;

    public static final byte[] MAGIC_NUMBER_ARRAY;

    public static final String EXTENSION = ".fpnt";

    static {
        MAGIC_NUMBER_ARRAY = new byte[2];
        MAGIC_NUMBER_ARRAY[0] = (byte) (MAGIC_NUMBER >> 8);
        MAGIC_NUMBER_ARRAY[1] = (byte) (MAGIC_NUMBER & 0xff);
    }

    public static final byte
            BOOLEAN = 0,
            BYTE = 1,
            CHAR = 2,
            INT = 3,
            LONG = 4,
            FLOAT = 5,
            DOUBLE = 6,
            BOOLEAN_ARRAY = 7,
            BYTE_ARRAY = 8,
            CHAR_ARRAY = 9,
            INT_ARRAY = 10,
            LONG_ARRAY = 11,
            FLOAT_ARRAY = 12,
            DOUBLE_ARRAY = 13,
            STRING = 14,
            STRING_ARRAY = 15,
            BYTE_BUFFER = 16,
            BUFFERED_IMAGE = 17;

}
