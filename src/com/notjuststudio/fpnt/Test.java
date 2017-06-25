package com.notjuststudio.fpnt;

import java.io.File;

/**
 * Created by George on 25.06.2017.
 */
public class Test {

    public static void main(String[] args) {
        final FPNTContainer container = new FPNTContainer();
        container.putBoolean("IMPORTANT_BOOL", true);
        container.putBoolean("IMPORTANT_BOOL1", true);
        container.putBoolean("IMPORTANT_BOOL2", true);
        container.putBoolean("IMPORTANT_BOOL3", true);
        container.putBoolean("IMPORTANT_BOOL4", true);
        container.putBoolean("IMPORTANT_BOOL5", true);
        container.putBoolean("IMPORTANT_BOOL6", true);
        container.putBoolean("IMPORTANT_BOOL7", true);
        container.putBoolean("IMPORTANT_BOOL8", true);
        container.putBoolean("IMPORTANT_BOOL9", true);
        container.putByte("Byte_code", Byte.MAX_VALUE);
        FPNTDecoder.write(new File("test.fpnt"), container, true);
        final FPNTContainer output = FPNTDecoder.read(new File("test.fpnt"));
        System.out.println(output);
    }
}
