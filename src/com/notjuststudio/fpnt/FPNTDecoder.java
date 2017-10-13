package com.notjuststudio.fpnt;

import com.sun.istack.internal.NotNull;

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
    public static boolean checkFile(@NotNull final File file) {
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
     * Encode FPNTContainer to outputStream
     * @param output target stream
     * @param container source FPNTContainer
     */
    public static void encode(@NotNull final OutputStream output, @NotNull final FPNTContainer container) {
        try {
            maps:
            for (Map.Entry<Byte, Map<String, Object>> map : container.getMaps().entrySet()) {
                output.write(map.getKey());

                for (FPNTExpander expander : container.getExpanderSet()) {
                    if (expander.write(output, map.getKey(), container.getTypeMap(map.getKey())))
                        continue maps;
                }

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
                            writeString(output, key);
                        output.write(FPNTParser.parse(values));
                        break;
                    }
                    case FPNTConstants.BYTE: {
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeString(output, entry.getKey());
                            output.write((byte)entry.getValue());
                        }
                        break;
                    }
                    case FPNTConstants.CHAR:
                    case FPNTConstants.INT:
                    case FPNTConstants.LONG:
                    case FPNTConstants.FLOAT:
                    case FPNTConstants.DOUBLE: {
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeString(output, entry.getKey());
                            output.write(FPNTParser.parse(entry.getValue()));
                        }
                        break;
                    }
                    case FPNTConstants.BOOLEAN_ARRAY:{
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeString(output, entry.getKey());
                            output.write(FPNTParser.parse(((boolean[])entry.getValue()).length));
                            output.write(FPNTParser.parse(entry.getValue()));
                        }
                        break;
                    }
                    case FPNTConstants.BYTE_ARRAY: {
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeString(output, entry.getKey());
                            output.write(FPNTParser.parse(((byte[])entry.getValue()).length));
                            output.write((byte[])entry.getValue());
                        }
                        break;
                    }
                    case FPNTConstants.CHAR_ARRAY:{
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeString(output, entry.getKey());
                            output.write(FPNTParser.parse(((char[])entry.getValue()).length));
                            output.write(FPNTParser.parse(entry.getValue()));
                        }
                        break;
                    }
                    case FPNTConstants.INT_ARRAY:{
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeString(output, entry.getKey());
                            output.write(FPNTParser.parse(((int[])entry.getValue()).length));
                            output.write(FPNTParser.parse(entry.getValue()));
                        }
                        break;
                    }
                    case FPNTConstants.LONG_ARRAY: {
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeString(output, entry.getKey());
                            output.write(FPNTParser.parse(((long[])entry.getValue()).length));
                            output.write(FPNTParser.parse(entry.getValue()));
                        }
                        break;
                    }
                    case FPNTConstants.FLOAT_ARRAY: {
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeString(output, entry.getKey());
                            output.write(FPNTParser.parse(((float[])entry.getValue()).length));
                            output.write(FPNTParser.parse(entry.getValue()));
                        }
                        break;
                    }
                    case FPNTConstants.DOUBLE_ARRAY: {
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeString(output, entry.getKey());
                            output.write(FPNTParser.parse(((double[])entry.getValue()).length));
                            output.write(FPNTParser.parse(entry.getValue()));
                        }
                        break;
                    }
                    case FPNTConstants.STRING: {
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeString(output, entry.getKey());
                            writeString(output, (String)entry.getValue());
                        }
                        break;
                    }
                    case FPNTConstants.STRING_ARRAY: {
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeString(output, entry.getKey());
                            final byte[] strings = FPNTParser.parse(entry.getValue());
                            output.write(FPNTParser.parse(strings.length));
                            output.write(strings);
                        }
                        break;
                    }
                    case FPNTConstants.BYTE_BUFFER: {
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeString(output, entry.getKey());
                            final byte[] bytes = FPNTParser.parse(entry.getValue());
                            output.write(FPNTParser.parse(bytes.length));
                            output.write(bytes);
                        }
                        break;
                    }
                    case FPNTConstants.BUFFERED_IMAGE: {
                        for (Map.Entry<String, Object> entry : map.getValue().entrySet()) {
                            writeString(output, entry.getKey());
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
    }

    /**
     * @see #write(File, FPNTContainer, boolean)
     */
    public static void write(@NotNull final File file,@NotNull  final FPNTContainer container) {
        write(file, container, false);
    }

    /**
     * Write FPNTContainer to file
     * @param file target file
     * @param container source FPNTContainer
     * @param canBeOverwritten will be file overwritten, if already exist
     */
    public static void write(@NotNull final File file,@NotNull  final FPNTContainer container,@NotNull  final boolean canBeOverwritten) {
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
            output.write(FPNTParser.parse(container.getVersion()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        encode(output, container);
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write String value in already opened outputStream
     * @param output opened outputStream
     * @param value
     * @throws IOException can be thrown by outputStream.write()
     */
    public static void writeString(@NotNull final OutputStream output, @NotNull  final String value) throws IOException {
        final byte[] bytes = FPNTParser.parse(value);
        output.write(FPNTParser.parse(bytes.length));
        output.write(bytes);
    }

    /**
     * Write int value in already opened outputStream
     * @param output opened outputStream
     * @param value
     * @throws IOException can be thrown by outputStream.write()
     */
    public static void writeInt(@NotNull final OutputStream output,@NotNull  final int value) throws IOException {
        output.write(FPNTParser.parse(value));
    }

    /**
     * Decode FPNTContainer from inputStream
     * @param input source stream
     * @param container target FPNTContainer
     */
    public static void decode(@NotNull final InputStream input, @NotNull final FPNTContainer container) {
        try {
            byte type;
            maps:
            while((type = (byte)input.read()) != -1) {
                final byte[] count = new byte[4];

                for (FPNTExpander expander : container.getExpanderSet()) {
                    if (expander.read(input, type, container))
                        continue maps;
                }

                input.read(count);
                final int length = FPNTParser.parseInt(count);

                switch (type) {
                    case FPNTConstants.BOOLEAN:{
                        final String[] keys = new String[length];
                        for (int i = 0; i < length; i++) {
                            keys[i] = readString(input);
                        }
                        final byte[] bytes = new byte[length / 8 + ((length % 8) > 0 ? 1 : 0)];
                        input.read(bytes);
                        final boolean[] value = FPNTParser.parseBooleanArray(bytes, length);
                        for (int i = 0; i < length; i++) {
                            container.putValue(FPNTConstants.BOOLEAN, keys[i], value[i]);
                        }
                        break;
                    }
                    case FPNTConstants.BYTE:{
                        for (int i = 0; i < length; i++) {
                            final String key = readString(input);
                            container.putValue(FPNTConstants.BYTE, key, (byte)input.read());
                        }
                        break;
                    }
                    case FPNTConstants.CHAR:{
                        for (int i = 0; i < length; i++) {
                            final String key = readString(input);
                            final byte[] bytes = new byte[2];
                            input.read(bytes);
                            container.putValue(FPNTConstants.CHAR, key, FPNTParser.parseChar(bytes));
                        }
                        break;
                    }
                    case FPNTConstants.INT:{
                        for (int i = 0; i < length; i++) {
                            final String key = readString(input);
                            final byte[] bytes = new byte[4];
                            input.read(bytes);
                            container.putValue(FPNTConstants.INT, key, FPNTParser.parseInt(bytes));
                        }
                        break;
                    }
                    case FPNTConstants.LONG:{
                        for (int i = 0; i < length; i++) {
                            final String key = readString(input);
                            final byte[] bytes = new byte[8];
                            input.read(bytes);
                            container.putValue(FPNTConstants.LONG, key, FPNTParser.parseLong(bytes));
                        }
                        break;
                    }
                    case FPNTConstants.FLOAT:{
                        for (int i = 0; i < length; i++) {
                            final String key = readString(input);
                            final byte[] bytes = new byte[4];
                            input.read(bytes);
                            container.putValue(FPNTConstants.FLOAT, key, FPNTParser.parseFloat(bytes));
                        }
                        break;
                    }
                    case FPNTConstants.DOUBLE:{
                        for (int i = 0; i < length; i++) {
                            final String key = readString(input);
                            final byte[] bytes = new byte[8];
                            input.read(bytes);
                            container.putValue(FPNTConstants.DOUBLE, key, FPNTParser.parseDouble(bytes));
                        }
                        break;
                    }
                    case FPNTConstants.BOOLEAN_ARRAY:{
                        for (int i = 0; i < length; i++) {
                            final String key = readString(input);
                            input.read(count);
                            final int size = FPNTParser.parseInt(count);
                            final byte[] bytes = new byte[size / 8 + ((size % 8) > 0 ? 1 : 0)];
                            input.read(bytes);
                            container.putValue(FPNTConstants.BOOLEAN_ARRAY, key, FPNTParser.parseBooleanArray(bytes, size));
                        }
                        break;
                    }
                    case FPNTConstants.BYTE_ARRAY:{
                        for (int i = 0; i < length; i++) {
                            final String key = readString(input);
                            input.read(count);
                            final int size = FPNTParser.parseInt(count);
                            final byte[] bytes = new byte[size];
                            input.read(bytes);
                            container.putValue(FPNTConstants.BYTE_ARRAY, key, bytes);
                        }
                        break;
                    }
                    case FPNTConstants.CHAR_ARRAY:{
                        for (int i = 0; i < length; i++) {
                            final String key = readString(input);
                            input.read(count);
                            final int size = FPNTParser.parseInt(count);
                            final byte[] bytes = new byte[size * 2];
                            input.read(bytes);
                            container.putValue(FPNTConstants.CHAR_ARRAY, key, FPNTParser.parseCharArray(bytes));
                        }
                        break;
                    }
                    case FPNTConstants.INT_ARRAY:{
                        for (int i = 0; i < length; i++) {
                            final String key = readString(input);
                            input.read(count);
                            final int size = FPNTParser.parseInt(count);
                            final byte[] bytes = new byte[size * 4];
                            input.read(bytes);
                            container.putValue(FPNTConstants.INT_ARRAY, key, FPNTParser.parseIntArray(bytes));
                        }
                        break;
                    }
                    case FPNTConstants.LONG_ARRAY:{
                        for (int i = 0; i < length; i++) {
                            final String key = readString(input);
                            input.read(count);
                            final int size = FPNTParser.parseInt(count);
                            final byte[] bytes = new byte[size * 8];
                            input.read(bytes);
                            container.putValue(FPNTConstants.LONG_ARRAY, key, FPNTParser.parseLongArray(bytes));
                        }
                        break;
                    }
                    case FPNTConstants.FLOAT_ARRAY:{
                        for (int i = 0; i < length; i++) {
                            final String key = readString(input);
                            input.read(count);
                            final int size = FPNTParser.parseInt(count);
                            final byte[] bytes = new byte[size * 4];
                            input.read(bytes);
                            container.putValue(FPNTConstants.FLOAT_ARRAY, key, FPNTParser.parseFloatArray(bytes));
                        }
                        break;
                    }
                    case FPNTConstants.DOUBLE_ARRAY:{
                        for (int i = 0; i < length; i++) {
                            final String key = readString(input);
                            input.read(count);
                            final int size = FPNTParser.parseInt(count);
                            final byte[] bytes = new byte[size * 8];
                            input.read(bytes);
                            container.putValue(FPNTConstants.DOUBLE_ARRAY, key, FPNTParser.parseDoubleArray(bytes));
                        }
                        break;
                    }
                    case FPNTConstants.STRING: {
                        for (int i = 0; i < length; i++) {
                            final String key = readString(input);
                            final String value = readString(input);
                            container.putValue(FPNTConstants.STRING, key, value);
                        }
                        break;
                    }
                    case FPNTConstants.STRING_ARRAY: {
                        for (int i = 0; i < length; i++) {
                            final String key = readString(input);
                            input.read(count);
                            final int size = FPNTParser.parseInt(count);
                            final byte[] bytes = new byte[size];
                            input.read(bytes);
                            container.putValue(FPNTConstants.STRING_ARRAY, key, FPNTParser.parseStringArray(bytes));
                        }
                        break;
                    }
                    case FPNTConstants.BYTE_BUFFER:{
                        for (int i = 0; i < length; i++) {
                            final String key = readString(input);
                            input.read(count);
                            final int size = FPNTParser.parseInt(count);
                            final byte[] bytes = new byte[size];
                            input.read(bytes);
                            container.putValue(FPNTConstants.BYTE_BUFFER, key, FPNTParser.parseByteBuffer(bytes));
                        }
                        break;
                    }
                    case FPNTConstants.BUFFERED_IMAGE:{
                        for (int i = 0; i < length; i++) {
                            final String key = readString(input);
                            input.read(count);
                            final int size = FPNTParser.parseInt(count);
                            final byte[] bytes = new byte[size];
                            input.read(bytes);
                            container.putValue(FPNTConstants.BUFFERED_IMAGE, key, FPNTParser.parseBufferedImage(bytes));
                        }
                        break;
                    }
                    default:
                        throw new FPNTException("Can't read file, unknown type " + Byte.toString(type));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @see #read(File, FPNTContainer)
     */
    public static FPNTContainer read(@NotNull final File file) {
        return read(file, new FPNTContainer());
    }

    /**
     * Read file to FPNTContainer
     * @param file
     * @param container target FPNTContainer
     * @return container
     */
    public static <T extends FPNTContainer> T read(@NotNull final File file, @NotNull final T container) {
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
            final byte[] version = new byte[4];
            input.read(version);
            container.setVersion(FPNTParser.parseInt(version));
        } catch (IOException e) {
            e.printStackTrace();
        }
        decode(input, container);
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return container;
    }

    /**
     * Read String value from already opened inputStream
     * @param input opened inputStream
     * @return String value
     * @throws IOException can be thrown by outputStream.write()
     */
    public static String readString(@NotNull final InputStream input) throws IOException {
        final byte[] size = new byte[4];
        input.read(size);
        final int length = FPNTParser.parseInt(size);

        final byte[] value = new byte[length];
        input.read(value);
        return FPNTParser.parseString(value);
    }

    /**
     * Read int value from already opened inputStream
     * @param input opened inputStream
     * @return int value
     * @throws IOException can be thrown by outputStream.write()
     */
    public static int readInt(@NotNull final InputStream input) throws IOException {
        final byte[] size = new byte[4];
        input.read(size);
        return FPNTParser.parseInt(size);
    }

}
