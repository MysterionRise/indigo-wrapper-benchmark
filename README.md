### Indigo wrapper tests for different Java invocation of native libs

All benchmarks happened on Mac OSX 12.0.1 on Apple M1 Max 64 GB
Project requires to have prebuilt native libs of Indigo (https://github.com/epam/Indigo)

### Results

```
Benchmark                    Mode  Cnt  Score     Error   Units
IndigoJNA.benchmarkJNA       avgt    5  876.912 ± 22.658  ms/op
IndigoJNA.benchmarkJNA       avgt    5  875.835 ± 18.079  ms/op
IndigoJNR.benchmarkJNR       avgt    5  876.333 ± 27.011  ms/op
IndigoJNA.benchmarkJNA       avgt    5  852.179 ± 46.401  ms/op
IndigoTest.benchmarkIndigo   avgt   25  822.508 ± 69.830  ms/op
```


