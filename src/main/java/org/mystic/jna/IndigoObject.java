package org.mystic.jna;

public class IndigoObject {

    private final IndigoAPI dispatcher;
    private final int self;

    public IndigoObject(IndigoAPI dispatcher, int id) {
        this.dispatcher = dispatcher;
        this.self = id;
    }

    public String canonicalSmiles() {
        dispatcher.setSessionID();
        return dispatcher.checkResultString(this, IndigoLibrary.INSTANCE.indigoCanonicalSmiles(self));
    }
}
