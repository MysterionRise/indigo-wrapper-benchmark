package org.mystic.panama;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.nio.file.Path;

public class IndigoLibrary {

    public static final SymbolLookup LOOKUP =
            SymbolLookup.libraryLookup(Path.of("lib/libindigo.dylib"), Arena.global());
    public static final Linker LINKER = Linker.nativeLinker();

    public static final MethodHandle ALLOC_SESSION_ID = LINKER.downcallHandle(
            LOOKUP.find("indigoAllocSessionId").orElseThrow(),
            FunctionDescriptor.of(ValueLayout.JAVA_LONG));

    public static final MethodHandle SET_SESSION_ID = LINKER.downcallHandle(
            LOOKUP.find("indigoSetSessionId").orElseThrow(),
            FunctionDescriptor.ofVoid(ValueLayout.JAVA_LONG));

    public static final MethodHandle LOAD_MOLECULE_FROM_STRING = LINKER.downcallHandle(
            LOOKUP.find("indigoLoadMoleculeFromString").orElseThrow(),
            FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));

    public static final MethodHandle LAYOUT = LINKER.downcallHandle(
            LOOKUP.find("indigoLayout").orElseThrow(),
            FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));

    public static final MethodHandle AROMATIZE = LINKER.downcallHandle(
            LOOKUP.find("indigoAromatize").orElseThrow(),
            FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));

    public static final MethodHandle CANONICAL_SMILES = LINKER.downcallHandle(
            LOOKUP.find("indigoCanonicalSmiles").orElseThrow(),
            FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));

    public static final MethodHandle GET_LAST_ERROR = LINKER.downcallHandle(
            LOOKUP.find("indigoGetLastError").orElseThrow(),
            FunctionDescriptor.of(ValueLayout.ADDRESS));
}
