package org.mystic.panama;

import java.lang.foreign.MemorySegment;

public class IndigoObject {

    private final IndigoAPI dispatcher;
    private final int self;

    public IndigoObject(IndigoAPI dispatcher, int id) {
        this.dispatcher = dispatcher;
        this.self = id;
    }

    public String canonicalSmiles() {
        dispatcher.setSessionID();
        try {
            MemorySegment result = (MemorySegment) IndigoLibrary.CANONICAL_SMILES.invokeExact(self);
            return dispatcher.checkResultString(result);
        } catch (RuntimeException e) {
            throw e;
        } catch (Throwable e) {
            throw new RuntimeException("Failed to get canonical SMILES", e);
        }
    }

    public void layout() {
        dispatcher.setSessionID();
        try {
            dispatcher.checkResult((int) IndigoLibrary.LAYOUT.invokeExact(self));
        } catch (RuntimeException e) {
            throw e;
        } catch (Throwable e) {
            throw new RuntimeException("Failed to layout molecule", e);
        }
    }

    public void aromatize() {
        dispatcher.setSessionID();
        try {
            dispatcher.checkResult((int) IndigoLibrary.AROMATIZE.invokeExact(self));
        } catch (RuntimeException e) {
            throw e;
        } catch (Throwable e) {
            throw new RuntimeException("Failed to aromatize molecule", e);
        }
    }
}
