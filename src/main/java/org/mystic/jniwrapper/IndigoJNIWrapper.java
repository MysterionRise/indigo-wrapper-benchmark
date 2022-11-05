package org.mystic.jniwrapper;

import com.jniwrapper.*;

public class IndigoJNIWrapper {

    protected static Library SAMPLE_LIB;
    static
    {
        String libName = "libindigo.dylib";
        SAMPLE_LIB = new Library(libName,
                Function.STDCALL_CALLING_CONVENTION);
    }

    static
    {
        DefaultLibraryLoader.getInstance().addPath("lib");
        SAMPLE_LIB.load();
    }

    public static void main(String[] args) {
        Function indigoLoadMoleculeFromString = SAMPLE_LIB.getFunction("indigoLoadMoleculeFromString");
        Int result = new Int();
        indigoLoadMoleculeFromString.invoke(result, new Str("C"));
        System.out.println(result);
    }
}
