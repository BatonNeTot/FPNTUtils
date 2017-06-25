package com.notjuststudio.fpnt;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by George on 25.06.2017.
 */
public class FPNTDecoder {

    public static void write(final File file, final FPNTContainer container) {
        write(file, container, false);
    }

    public static void write(final File file, final FPNTContainer container, final boolean canBeOverwritten) {
        if (!canBeOverwritten && file.exists())
            throw new FPNTException("File already exists");
        final BufferedOutputStream output;
        try {
            output = new BufferedOutputStream(new FileOutputStream(file));
            output.write(FPNTConstants.MAGIC_NUMBER_ARRAY);

            maps:
            for (Map.Entry<Byte, Map<String, Object>> map : container.getMaps().entrySet()) {
                output.write(map.getKey());
                output.write(FPNTParser.parse(map.getValue().size()));
                switch (map.getKey()) {
                    case FPNTConstants.BOOLEAN: {
                        final List<String> keys = new ArrayList<>(map.getValue().keySet());
                        final boolean[] values = new boolean[map.getValue().size()];

                        for (String key : map.getValue().keySet()) {
                            final int index = keys.indexOf(key);
                            values[index] = (boolean)map.getValue().get(key);
                        }

                        for (String key : keys)
                            writeKey(output, key);
                        output.write(FPNTParser.parse(values));
                        break;
                    }
                    case FPNTConstants.BYTE: {
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeKey(output, entry.getKey());
                            output.write((byte)entry.getValue());
                        }
                        break;
                    }
                    case FPNTConstants.CHAR:
                    case FPNTConstants.INT:
                    case FPNTConstants.LONG: {
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeKey(output, entry.getKey());
                            output.write(FPNTParser.parse(entry.getValue()));
                        }
                        break;
                    }
                    case FPNTConstants.BOOLEAN_ARRAY:{
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeKey(output, entry.getKey());
                            output.write(FPNTParser.parse(((boolean[])entry.getValue()).length));
                            output.write(FPNTParser.parse(entry.getValue()));
                        }
                        break;
                    }
                    case FPNTConstants.BYTE_ARRAY: {
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeKey(output, entry.getKey());
                            output.write(FPNTParser.parse(((byte[])entry.getValue()).length));
                            output.write((byte[])entry.getValue());
                        }
                        break;
                    }
                    case FPNTConstants.CHAR_ARRAY:{
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeKey(output, entry.getKey());
                            output.write(FPNTParser.parse(((char[])entry.getValue()).length));
                            output.write(FPNTParser.parse(entry.getValue()));
                        }
                        break;
                    }
                    case FPNTConstants.INT_ARRAY:{
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeKey(output, entry.getKey());
                            output.write(FPNTParser.parse(((int[])entry.getValue()).length));
                            output.write(FPNTParser.parse(entry.getValue()));
                        }
                        break;
                    }
                    case FPNTConstants.LONG_ARRAY: {
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeKey(output, entry.getKey());
                            output.write(FPNTParser.parse(((long[])entry.getValue()).length));
                            output.write(FPNTParser.parse(entry.getValue()));
                        }
                        break;
                    }
                    default:
                        break maps;
                }
            }

            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeKey(final BufferedOutputStream output, final String key) throws IOException {
        final byte[] bytes = FPNTParser.parse(key);
        output.write(FPNTParser.parse(bytes.length));
        output.write(bytes);
    }

    public static FPNTContainer read(final File file) {
        final BufferedInputStream input;
        try {
            input = new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new FPNTException(e);
        }
        try {
            final byte[] data = new byte[2];
            input.read(data);
            char check = FPNTParser.parseChar(data);
            if (check != FPNTConstants.MAGIC_NUMBER) {
                throw new FPNTException("File is not FPNT type");
            }
            final FPNTContainer container = new FPNTContainer();
            byte type;
            maps:
            while((type = (byte)input.read()) != -1) {
                final byte[] count = new byte[4];
                input.read(count);
                final int length = FPNTParser.parseInt(count);
                switch (type) {
                    case FPNTConstants.BOOLEAN:{
                        final String[] keys = new String[length];
                        for (int i = 0; i < length; i++) {
                            keys[i] = readKey(input);
                        }
                        final byte[] bytes = new byte[length / 8 + ((length % 8) > 0 ? 1 : 0)];
                        input.read(bytes);
                        final boolean[] value = FPNTParser.parseBooleanArray(bytes, length);
                        for (int i = 0; i < length; i++) {
                            container.putBoolean(keys[i], value[i]);
                        }
                        break;
                    }
                    case FPNTConstants.BYTE:{
                        for (int i = 0; i < length; i++) {
                            final String key = readKey(input);
                            container.putByte(key, (byte)input.read());
                        }
                        break;
                    }
                    case FPNTConstants.CHAR:{
                        for (int i = 0; i < length; i++) {
                            final String key = readKey(input);
                            final byte[] bytes = new byte[2];
                            input.read(bytes);
                            container.putChar(key, FPNTParser.parseChar(bytes));
                        }
                        break;
                    }
                    case FPNTConstants.INT:{
                        for (int i = 0; i < length; i++) {
                            final String key = readKey(input);
                            final byte[] bytes = new byte[4];
                            input.read(bytes);
                            container.putInt(key, FPNTParser.parseInt(bytes));
                        }
                    }
                    case FPNTConstants.LONG:{
                        for (int i = 0; i < length; i++) {
                            final String key = readKey(input);
                            final byte[] bytes = new byte[8];
                            input.read(bytes);
                            container.putLong(key, FPNTParser.parseLong(bytes));
                        }
                        break;
                    }
                    case FPNTConstants.BOOLEAN_ARRAY:{
                        for (int i = 0; i < length; i++) {
                            final String key = readKey(input);
                            final int size = input.read(count);
                            final byte[] bytes = new byte[size / 8 + ((size % 8) > 0 ? 1 : 0)];
                            input.read(bytes);
                            container.putBooleanArray(key, FPNTParser.parseBooleanArray(bytes, size));
                        }
                    }
                    case FPNTConstants.BYTE_ARRAY:{
                        for (int i = 0; i < length; i++) {
                            final String key = readKey(input);
                            final int size = input.read(count);
                            final byte[] bytes = new byte[size];
                            input.read(bytes);
                            container.putByteArray(key, bytes);
                        }
                        break;
                    }
                    case FPNTConstants.CHAR_ARRAY:{
                        for (int i = 0; i < length; i++) {
                            final String key = readKey(input);
                            final int size = input.read(count);
                            final byte[] bytes = new byte[size * 2];
                            input.read(bytes);
                            container.putCharArray(key, FPNTParser.parseCharArray(bytes));
                        }
                        break;
                    }
                    case FPNTConstants.INT_ARRAY:{
                        for (int i = 0; i < length; i++) {
                            final String key = readKey(input);
                            final int size = input.read(count);
                            final byte[] bytes = new byte[size * 4];
                            input.read(bytes);
                            container.putIntArray(key, FPNTParser.parseIntArray(bytes));
                        }
                        break;
                    }
                    case FPNTConstants.LONG_ARRAY:{
                        for (int i = 0; i < length; i++) {
                            final String key = readKey(input);
                            final int size = input.read(count);
                            final byte[] bytes = new byte[size * 8];
                            input.read(bytes);
                            container.putLongArray(key, FPNTParser.parseLongArray(bytes));
                        }
                        break;
                    }
                    default:
                        break maps;
                }
            }
            input.close();
            return container;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String readKey(BufferedInputStream input) throws IOException {
        final byte[] size = new byte[4];
        input.read(size);
        final int length = FPNTParser.parseInt(size);

        final byte[] key = new byte[length];
        input.read(key);
        return FPNTParser.parseString(key);
    }

}
