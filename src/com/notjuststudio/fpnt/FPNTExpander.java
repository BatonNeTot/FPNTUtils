package com.notjuststudio.fpnt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * FPNTExpander can expand default FPNTContainer for custom read/write in FPNTDecoder
 * @author KLLdon
 */
public interface FPNTExpander {

    /**
     * Custom write for FPNTDecoder
     * @param output already opened outputStream
     * @param type byte key for value
     * @param count count of values
     * @param container FPNTContainer
     * @return can current type be handle by method
     * @throws IOException can be thrown by outputStream
     */
    boolean write(final OutputStream output, final byte type, final int count, final FPNTContainer container) throws IOException;

    /**
     * Custom read for FPNTDecoder
     * @param input already opened inputStream
     * @param type byte key for value
     * @param count count of values
     * @param container FPNTContainer
     * @return can current type be handle by method
     * @throws IOException can be thrown by inputStream
     */
    boolean read(final InputStream input, final byte type, final int count, final FPNTContainer container) throws IOException;

}
