package org.mystic.jna;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class IndigoJNA {

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkJNA() throws IOException {
        IndigoAPI indigoAPI = new IndigoAPI();
        List<String> smilesList = new ArrayList<>();
        Scanner in = new Scanner(Paths.get("src", "test", "resources", "smiles_sample.csv"));
        while (in.hasNext()) {
            String smile = in.nextLine();
            IndigoObject indigoObject = indigoAPI.loadMolecule(smile);
            indigoObject.layout();
            indigoObject.aromatize();
            smilesList.add(indigoObject.canonicalSmiles());
        }
    }

    public static void main(String[] args) throws IOException, RunnerException {
        Options opt = new OptionsBuilder()
                .include(IndigoJNA.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }


}
