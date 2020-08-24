package org.mystic.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public interface IndigoLibrary extends Library {
    IndigoLibrary INSTANCE = Native.load("lib/libindigo.dylib", IndigoLibrary.class);

    int indigoLoadMoleculeFromString(String str);

    int indigoFingerprint(int item, String type);

    long indigoAllocSessionId();

    void indigoSetSessionId(long id);

    Pointer indigoCanonicalSmiles(int molecule);

    String indigoGetLastError();

    int indigoLayout(int object);

    int indigoAromatize(int item);

}
