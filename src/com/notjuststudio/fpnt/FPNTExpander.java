package com.notjuststudio.fpnt;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

/**
 * Created by George on 26.06.2017.
 */
public interface FPNTExpander {

    public boolean write(final BufferedOutputStream output, final byte type, final int count, final FPNTContainer container);

    public boolean read(final BufferedInputStream input, final byte type, final int count, final FPNTContainer container);

}
