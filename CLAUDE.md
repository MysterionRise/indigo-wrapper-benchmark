# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Java benchmarking project comparing four approaches for calling the native Indigo cheminformatics library from Java:

1. **EPAM Indigo** (`org.mystic.indigo`) — direct wrapper via `com.epam.indigo`
2. **JNA** (`org.mystic.jna`) — custom Java Native Access binding
3. **JNR** (`org.mystic.jnr`) — custom Java Native Runtime binding
4. **Panama FFM** (`org.mystic.panama`) — Project Panama Foreign Function & Memory API

Each wrapper has a mirrored structure: `IndigoLibrary` (FFI interface), `IndigoAPI` (session management), `IndigoObject` (high-level object wrapper). Benchmarks use JMH (Java Microbenchmark Harness) and process SMILES molecular structure strings from CSV files in `src/test/resources/`.

## Build & Run

```bash
# Build uber JAR with benchmarks
./mvnw clean package

# Run all benchmarks
java --enable-native-access=ALL-UNNAMED -jar target/benchmarks.jar

# Run a specific benchmark
java --enable-native-access=ALL-UNNAMED -jar target/benchmarks.jar IndigoJNA
java --enable-native-access=ALL-UNNAMED -jar target/benchmarks.jar IndigoJNR
java --enable-native-access=ALL-UNNAMED -jar target/benchmarks.jar IndigoTest
java --enable-native-access=ALL-UNNAMED -jar target/benchmarks.jar IndigoPanama
```

## Prerequisites

- Java 25+ (compiler release target is 25)
- Maven 3.x (Maven Wrapper included via `./mvnw`)
- Prebuilt Indigo native libraries (`libindigo.dylib`) in `lib/` — built from [EPAM Indigo](https://github.com/epam/Indigo)
- Currently macOS ARM64 only (Apple Silicon native libs)

## Key Architecture Details

- Native Indigo library requires explicit session allocation (`indigoAllocSessionId`) and session switching (`indigoSetSessionId`) before each operation
- The JNA and JNR wrappers load `libindigo.dylib` from `lib/` via their respective library loading mechanisms
- The Panama wrapper uses `SymbolLookup.libraryLookup()` to load the native library
- Benchmark operation: load SMILES -> layout molecule -> aromatize -> get canonical SMILES (iterates over ~4,871 molecules)
- `maven-shade-plugin` builds an uber JAR with `org.openjdk.jmh.Main` as the entry point
- `--enable-native-access=ALL-UNNAMED` is required at runtime for Panama FFM API (also set in `@Fork` JMH annotations)
