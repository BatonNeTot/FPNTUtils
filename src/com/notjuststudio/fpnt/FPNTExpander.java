package com.notjuststudio.fpnt;

import com.sun.istack.internal.NotNull;

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
    boolean write(@NotNull final OutputStream output,@NotNull  final byte type,@NotNull  final int count,@NotNull  final FPNTContainer container) throws IOException;

    /**
     * Custom read for FPNTDecoder
     * @param input already opened inputStream
     * @param type byte key for value
     * @param count count of values
     * @param container FPNTContainer
     * @return can current type be handle by method
     * @throws IOException can be thrown by inputStream
     */
    boolean read(@NotNull final InputStream input,@NotNull  final byte type,@NotNull  final int count,@NotNull  final FPNTContainer container) throws IOException;

}
