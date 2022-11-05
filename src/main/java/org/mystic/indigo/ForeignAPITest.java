package org.mystic.indigo;

//import java.lang.foreign.*;
//import java.lang.invoke.MethodHandle;
//import java.util.Arrays;

public class ForeignAPITest {


    public static void main(String[] args) throws Throwable {
        // 1. Find foreign function on the C library path
//        Linker linker = Linker.nativeLinker();
//        SymbolLookup stdlib = linker.defaultLookup();
////        MethodHandle radixSort = linker.downcallHandle(stdlib.lookup("radixsort"))
////                new Functiontdlib.lookup("radixsort"),);
//// 2. Allocate on-heap memory to store four strings
//        String[] javaStrings = {"mouse", "cat", "dog", "car"};
//// 3. Allocate off-heap memory to store four pointers
//        SegmentAllocator allocator = SegmentAllocator.implicitAllocator();
//        MemorySegment offHeap = allocator.allocateArray(ValueLayout.ADDRESS, javaStrings.length);
//// 4. Copy the strings from on-heap to off-heap
//        for (int i = 0; i < javaStrings.length; i++) {
//            // Allocate a string off-heap, then store a pointer to it
//            MemorySegment cString = allocator.allocateUtf8String(javaStrings[i]);
//            offHeap.setAtIndex(ValueLayout.ADDRESS, i, cString);
//        }
//// 5. Sort the off-heap data by calling the foreign function
//        radixSort.invoke(offHeap, javaStrings.length, MemoryAddress.NULL, '\0');
//// 6. Copy the (reordered) strings from off-heap to on-heap
//        for (int i = 0; i < javaStrings.length; i++) {
//            MemoryAddress cStringPtr = offHeap.getAtIndex(ValueLayout.ADDRESS, i);
//            javaStrings[i] = cStringPtr.getUtf8String(0);
//        }
//        assert Arrays.equals(javaStrings, new String[]{"car", "cat", "dog", "mouse"});  // true
    }
}
