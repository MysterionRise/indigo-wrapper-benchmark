package org.mystic.jnr;

import jnr.ffi.LibraryLoader;
import jnr.ffi.Pointer;

public interface IndigoLibrary {
    IndigoLibrary INSTANCE = LibraryLoader.create(IndigoLibrary.class).load("lib/libindigo.dylib");

    int indigoLoadMoleculeFromString(String str);

    int indigoFingerprint(int item, String type);

    long indigoAllocSessionId();

    void indigoSetSessionId(long id);

    Pointer indigoCanonicalSmiles(int molecule);

    String indigoGetLastError();

    int indigoLayout(int object);

    int indigoAromatize(int item);
}
