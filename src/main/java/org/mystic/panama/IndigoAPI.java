package org.mystic.panama;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

public class IndigoAPI {

    private final long sid;
    private final Arena arena;

    public IndigoAPI(Arena arena) {
        this.arena = arena;
        try {
            this.sid = (long) IndigoLibrary.ALLOC_SESSION_ID.invokeExact();
        } catch (Throwable e) {
            throw new RuntimeException("Failed to allocate Indigo session", e);
        }
    }

    public void setSessionID() {
        try {
            IndigoLibrary.SET_SESSION_ID.invokeExact(sid);
        } catch (Throwable e) {
            throw new RuntimeException("Failed to set session ID", e);
        }
    }

    public int checkResult(int result) {
        if (result < 0) throw new RuntimeException(getLastError());
        return result;
    }

    public IndigoObject loadMolecule(String str) {
        setSessionID();
        try {
            MemorySegment cString = arena.allocateFrom(str);
            int result = (int) IndigoLibrary.LOAD_MOLECULE_FROM_STRING.invokeExact(cString);
            return new IndigoObject(this, checkResult(result));
        } catch (RuntimeException e) {
            throw e;
        } catch (Throwable e) {
            throw new RuntimeException("Failed to load molecule", e);
        }
    }

    public String checkResultString(MemorySegment result) {
        if (result.equals(MemorySegment.NULL)) throw new RuntimeException(getLastError());
        return result.reinterpret(Long.MAX_VALUE).getString(0);
    }

    private String getLastError() {
        try {
            MemorySegment err = (MemorySegment) IndigoLibrary.GET_LAST_ERROR.invokeExact();
            return err.reinterpret(Long.MAX_VALUE).getString(0);
        } catch (Throwable e) {
            return "Unknown error (failed to retrieve error message)";
        }
    }
}
