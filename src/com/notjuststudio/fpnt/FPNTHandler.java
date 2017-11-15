package com.notjuststudio.fpnt;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

public interface FPNTHandler {

    void handle(@NotNull final FPNTContainer container, @NotNull final byte type, @NotNull final String key, @Nullable final Object old, @Nullable final Object value);

}
