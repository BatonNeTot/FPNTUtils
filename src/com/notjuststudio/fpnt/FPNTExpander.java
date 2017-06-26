package com.notjuststudio.fpnt;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by George on 26.06.2017.
 */
public interface FPNTExpander {

    public boolean write(final OutputStream output, final byte type, final int count, final FPNTContainer container);

    public boolean read(final InputStream input, final byte type, final int count, final FPNTContainer container);

}
