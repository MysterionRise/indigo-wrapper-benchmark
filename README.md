### Indigo wrapper tests for different Java invocation of native libs

Project requires to have prebuilt native libs of Indigo (https://github.com/epam/Indigo) placed under `lib`

Four approaches are benchmarked:
1. **EPAM Indigo** — direct wrapper via `com.epam.indigo`
2. **JNA** — custom Java Native Access binding
3. **JNR-FFI** — custom Java Native Runtime binding
4. **Project Panama (FFM API)** — Java 22+ Foreign Function & Memory API

### Prerequisites

- Java 25+
- Prebuilt Indigo native libraries (`libindigo.dylib`) in `lib/` — macOS ARM64 only

### Build & Run

```bash
# Build uber JAR
./mvnw clean package

# Run all benchmarks
java --enable-native-access=ALL-UNNAMED -jar target/benchmarks.jar

# Run a specific benchmark
java --enable-native-access=ALL-UNNAMED -jar target/benchmarks.jar IndigoJNA
java --enable-native-access=ALL-UNNAMED -jar target/benchmarks.jar IndigoJNR
java --enable-native-access=ALL-UNNAMED -jar target/benchmarks.jar IndigoTest
java --enable-native-access=ALL-UNNAMED -jar target/benchmarks.jar IndigoPanama
```

## Test 1
#### Date 10.12.2021
Benchmark was completed on Mac OSX 12.0.1 on Apple M1 Max 64 GB

### Results

```
Benchmark                    Mode  Cnt  Score     Error   Units
IndigoJNA.benchmarkJNA       avgt    5  876.912 ± 22.658  ms/op
IndigoJNA.benchmarkJNA       avgt    5  875.835 ± 18.079  ms/op
IndigoJNR.benchmarkJNR       avgt    5  876.333 ± 27.011  ms/op
IndigoJNA.benchmarkJNA       avgt    5  852.179 ± 46.401  ms/op
IndigoTest.benchmarkIndigo   avgt   25  822.508 ± 69.830  ms/op
```

**Note:** The JNR results above were actually measuring JNA performance due to incorrect imports (fixed in this version).

## Test 2
#### Date 5.11.2022
Benchmark was completed on Mac OSX 12.6 on Apple M1 Max 64 GB
OpenJDK 19.0.1

### Results

```
Benchmark                    Mode  Cnt  Score     Error   Units
IndigoJNA.benchmarkJNA       avgt    5  850.148 ± 3.754  ms/op
IndigoJNR.benchmarkJNR       avgt    5  855.261 ± 13.167  ms/op
IndigoTest.benchmarkIndigo   avgt    5  851.085 ± 33.988  ms/op
```

**Note:** The JNR results above were actually measuring JNA performance due to incorrect imports (fixed in this version).

## Test 3
#### Date 16.02.2026
Benchmark was completed on macOS 15.7.3 on Apple M1 Max 64 GB
Oracle JDK 25.0.2 LTS

JNR import bug fixed — JNR benchmark now correctly measures JNR-FFI performance.
Panama (FFM API) benchmark added.

### Results

```
Benchmark                                Mode  Cnt    Score   Error  Units
IndigoTest.benchmarkIndigo               avgt   30  875.593 ± 6.572  ms/op
IndigoJNA.benchmarkJNA                   avgt   30  875.187 ± 4.852  ms/op
IndigoJNR.benchmarkJNR                   avgt   30  873.689 ± 3.020  ms/op
IndigoPanama.benchmarkPanama             avgt   30  874.586 ± 4.404  ms/op
```

### FFI Overhead Micro-benchmark

The molecule-processing benchmarks above are dominated by compute, hiding FFI overhead.
This micro-benchmark isolates per-call marshalling cost by calling a trivial native function
(`indigoSetSessionId` — void return, single long parameter).

```
Benchmark                                 Mode  Cnt    Score   Error  Units
FFIOverheadBenchmark.setSessionId_EPAM    avgt   30  462.160 ± 3.581  ns/op
FFIOverheadBenchmark.setSessionId_JNA     avgt   30  458.980 ± 2.928  ns/op
FFIOverheadBenchmark.setSessionId_JNR     avgt   30    7.695 ± 0.038  ns/op
FFIOverheadBenchmark.setSessionId_Panama  avgt   30    4.394 ± 0.063  ns/op
```

Panama's direct downcall stubs are the fastest (~4.4 ns), JNR's JIT-compiled stubs are close behind (~7.7 ns), while JNA-based approaches (including EPAM Indigo, which wraps JNA) are ~100x slower (~460 ns) due to reflection-based marshalling.
