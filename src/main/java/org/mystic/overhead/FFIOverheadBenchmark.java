package org.mystic.overhead;

import com.epam.indigo.Indigo;
import com.epam.indigo.IndigoLib;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@Fork(value = 1, jvmArgs = {"--enable-native-access=ALL-UNNAMED"})
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 10, time = 1)
@State(Scope.Thread)
public class FFIOverheadBenchmark {

    private Indigo indigo;
    private IndigoLib indigoLib;
    private long epamSid;

    private org.mystic.jna.IndigoLibrary jnaLib;
    private long jnaSid;

    private org.mystic.jnr.IndigoLibrary jnrLib;
    private long jnrSid;

    private long panamaSid;

    @Setup(Level.Trial)
    public void setup() throws Throwable {
        // EPAM Indigo
        indigo = new Indigo();
        indigoLib = Indigo.getLibrary();
        epamSid = indigo.getSid();

        // JNA
        jnaLib = org.mystic.jna.IndigoLibrary.INSTANCE;
        jnaSid = jnaLib.indigoAllocSessionId();

        // JNR
        jnrLib = org.mystic.jnr.IndigoLibrary.INSTANCE;
        jnrSid = jnrLib.indigoAllocSessionId();

        // Panama
        panamaSid = (long) org.mystic.panama.IndigoLibrary.ALLOC_SESSION_ID.invokeExact();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void setSessionId_EPAM() {
        indigoLib.indigoSetSessionId(epamSid);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void setSessionId_JNA() {
        jnaLib.indigoSetSessionId(jnaSid);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void setSessionId_JNR() {
        jnrLib.indigoSetSessionId(jnrSid);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void setSessionId_Panama() throws Throwable {
        org.mystic.panama.IndigoLibrary.SET_SESSION_ID.invokeExact(panamaSid);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(FFIOverheadBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
