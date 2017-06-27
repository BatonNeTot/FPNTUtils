package com.notjuststudio.fpnt;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * FPNTDecoder can read from FPNT, write to FPNT and check is file FPNT
 * @author KLLdon
 */
public class FPNTDecoder {

    /**
     * Check is file FPNT type
     * @param file
     * @return is file FPNT type
     */
    public static boolean checkFile(final File file) {
        if (!file.exists() || !file.isFile())
            return false;
        final BufferedInputStream input;
        try {
            input = new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            return false;
        }
        boolean flag = false;
        try {
            final byte[] data = new byte[2];
            input.read(data);
            char check = FPNTParser.parseChar(data);
            flag = check == FPNTConstants.MAGIC_NUMBER;
            input.close();
        } catch (IOException e) {
            return false;
        }
        return flag;
    }

    /**
     * @see #write(File, FPNTContainer, boolean)
     */
    public static void write(final File file, final FPNTContainer container) {
        write(file, container, false);
    }

    /**
     * Write FPNTContainer to file
     * @param file target file
     * @param container source FPNTContainer
     * @param canBeOverwritten will be file overwritten, if already exist
     */
    public static void write(final File file, final FPNTContainer container, final boolean canBeOverwritten) {
        if (!canBeOverwritten && file.exists())
            throw new FPNTException("File already exists");
        final BufferedOutputStream output;
        try {
            output = new BufferedOutputStream(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new FPNTException(e);
        }
        try {
            output.write(FPNTConstants.MAGIC_NUMBER_ARRAY);

            maps:
            for (Map.Entry<Byte, Map<String, Object>> map : container.getMaps().entrySet()) {
                output.write(map.getKey());
                output.write(FPNTParser.parse(map.getValue().size()));

                for (FPNTExpander expander : container.getExpanderList()) {
                    if (expander.write(output, map.getKey(), map.getValue().size(), container))
                        continue maps;
                }

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
                    case FPNTConstants.STRING: {
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeKey(output, entry.getKey());
                            writeKey(output, (String)entry.getValue());
                        }
                        break;
                    }
                    case FPNTConstants.STRING_ARRAY: {
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeKey(output, entry.getKey());
                            final byte[] strings = FPNTParser.parse(entry.getValue());
                            output.write(FPNTParser.parse(strings.length));
                            output.write(strings);
                        }
                        break;
                    }
                    case FPNTConstants.BYTE_BUFFER: {
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeKey(output, entry.getKey());
                            final byte[] bytes = FPNTParser.parse(entry.getValue());
                            output.write(FPNTParser.parse(bytes.length));
                            output.write(bytes);
                        }
                        break;
                    }
                    case FPNTConstants.BUFFERED_IMAGE: {
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeKey(output, entry.getKey());
                            final byte[] bytes = FPNTParser.parse(entry.getValue());
                            output.write(FPNTParser.parse(bytes.length));
                            output.write(bytes);
                        }
                        break;
                    }
                    default:
                        throw new FPNTException("Can't write to file, unknown type " + Byte.toString(map.getKey()));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Write String key in already opened outputStream
     * @param output opened outputStream
     * @param key
     * @throws IOException can be thrown by outputStream.write()
     */
    public static void writeKey(final OutputStream output, final String key) throws IOException {
        final byte[] bytes = FPNTParser.parse(key);
        output.write(FPNTParser.parse(bytes.length));
        output.write(bytes);
    }

    /**
     * @see #read(File, FPNTContainer)
     */
    public static FPNTContainer read(final File file) {
        return read(file, new FPNTContainer());
    }

    /**
     * Read file to FPNTContainer
     * @param file
     * @param container target FPNTContainer
     * @return container
     */
    public static FPNTContainer read(final File file, final FPNTContainer container) {
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
            byte type;
            maps:
            while((type = (byte)input.read()) != -1) {
                final byte[] count = new byte[4];
                input.read(count);
                final int length = FPNTParser.parseInt(count);

                for (FPNTExpander expander : container.getExpanderList()) {
                    if (expander.read(input, type, length, container))
                        continue maps;
                }

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
                        break;
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
                            input.read(count);
                            final int size = FPNTParser.parseInt(count);
                            final byte[] bytes = new byte[size / 8 + ((size % 8) > 0 ? 1 : 0)];
                            input.read(bytes);
                            container.putBooleanArray(key, FPNTParser.parseBooleanArray(bytes, size));
                        }
                        break;
                    }
                    case FPNTConstants.BYTE_ARRAY:{
                        for (int i = 0; i < length; i++) {
                            final String key = readKey(input);
                            input.read(count);
                            final int size = FPNTParser.parseInt(count);
                            final byte[] bytes = new byte[size];
                            input.read(bytes);
                            container.putByteArray(key, bytes);
                        }
                        break;
                    }
                    case FPNTConstants.CHAR_ARRAY:{
                        for (int i = 0; i < length; i++) {
                            final String key = readKey(input);
                            input.read(count);
                            final int size = FPNTParser.parseInt(count);
                            final byte[] bytes = new byte[size * 2];
                            input.read(bytes);
                            container.putCharArray(key, FPNTParser.parseCharArray(bytes));
                        }
                        break;
                    }
                    case FPNTConstants.INT_ARRAY:{
                        for (int i = 0; i < length; i++) {
                            final String key = readKey(input);
                            input.read(count);
                            final int size = FPNTParser.parseInt(count);
                            final byte[] bytes = new byte[size * 4];
                            input.read(bytes);
                            container.putIntArray(key, FPNTParser.parseIntArray(bytes));
                        }
                        break;
                    }
                    case FPNTConstants.LONG_ARRAY:{
                        for (int i = 0; i < length; i++) {
                            final String key = readKey(input);
                            input.read(count);
                            final int size = FPNTParser.parseInt(count);
                            final byte[] bytes = new byte[size * 8];
                            input.read(bytes);
                            container.putLongArray(key, FPNTParser.parseLongArray(bytes));
                        }
                        break;
                    }
                    case FPNTConstants.STRING: {
                        for (int i = 0; i < length; i++) {
                            final String key = readKey(input);
                            final String value = readKey(input);
                            container.putString(key, value);
                        }
                        break;
                    }
                    case FPNTConstants.STRING_ARRAY: {
                        for (int i = 0; i < length; i++) {
                            final String key = readKey(input);
                            input.read(count);
                            final int size = FPNTParser.parseInt(count);
                            final byte[] bytes = new byte[size];
                            input.read(bytes);
                            container.putStringArray(key, FPNTParser.parseStringArray(bytes));
                        }
                        break;
                    }
                    case FPNTConstants.BYTE_BUFFER:{
                        for (int i = 0; i < length; i++) {
                            final String key = readKey(input);
                            input.read(count);
                            final int size = FPNTParser.parseInt(count);
                            final byte[] bytes = new byte[size];
                            input.read(bytes);
                            container.putByteBuffer(key, FPNTParser.parseByteBuffer(bytes));
                        }
                        break;
                    }
                    case FPNTConstants.BUFFERED_IMAGE:{
                        for (int i = 0; i < length; i++) {
                            final String key = readKey(input);
                            input.read(count);
                            final int size = FPNTParser.parseInt(count);
                            final byte[] bytes = new byte[size];
                            input.read(bytes);
                            container.putBufferedImage(key, FPNTParser.parseBufferedImage(bytes));
                        }
                        break;
                    }
                    default:
                        throw new FPNTException("Can't read file, unknown type " + Byte.toString(type));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return container;
    }

    /**
     * Read String key from already opened inputStream
     * @param input opened inputStream
     * @return String key
     * @throws IOException can be thrown by outputStream.write()
     */
    public static String readKey(final InputStream input) throws IOException {
        final byte[] size = new byte[4];
        input.read(size);
        final int length = FPNTParser.parseInt(size);

        final byte[] key = new byte[length];
        input.read(key);
        return FPNTParser.parseString(key);
    }

}
