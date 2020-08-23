package org.mystic.jnr;


import jnr.ffi.Pointer;

public class IndigoAPI {

    private final long sid;

    public IndigoAPI() {
        this.sid = IndigoLibrary.INSTANCE.indigoAllocSessionId();
    }

    public void setSessionID() {
        IndigoLibrary.INSTANCE.indigoSetSessionId(sid);
    }

    public int checkResult(int result) {
        if (result < 0) throw new RuntimeException(IndigoLibrary.INSTANCE.indigoGetLastError());
        return result;
    }

    public IndigoObject loadMolecule(String str) {
        setSessionID();
        return new IndigoObject(this, checkResult(IndigoLibrary.INSTANCE.indigoLoadMoleculeFromString(str)));
    }

    public static String checkResultString(Object obj, Pointer result) {
        if (result == null) throw new RuntimeException(IndigoLibrary.INSTANCE.indigoGetLastError());
        return result.getString(0);
    }
}
