package org.mystic.jni;

import java.io.File;

public class IndigoJNI {

    static {
        System.load(System.getProperty("user.dir") + File.separator + "lib" + File.separator + "libindigo.dylib");
    }

    private native void loadMolecule(String mol);

    public static void main(String[] args) {
        new IndigoJNI().loadMolecule("");
    }
}
