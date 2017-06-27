package com.notjuststudio.fpnt;

import com.sun.istack.internal.NotNull;

/**
 * @author KLLdon
 */
public class FPNTException extends RuntimeException {

    public FPNTException(@NotNull final Throwable cause) {
        super(cause);
    }

    public FPNTException(@NotNull final String message) {
        super(message);
    }
}
