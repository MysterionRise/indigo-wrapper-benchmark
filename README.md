### Indigo wrapper tests for different Java invocation of native libs

All benchmarks happened on Mac OSX 10.15.6 on 2.3 GHz 8-Core Intel Core i9 16 GB 2667 MHz DDR4
Project requires to have prebuilt native libs of Indigo (https://github.com/epam/Indigo)

### Results

Benchmark               Mode  Cnt    Score   Error  Units
IndigoJNA.benchmarkJNA  avgt   25  711.993 ± 3.094  ms/op
IndigoJNA.benchmarkJNA  avgt   25  712.853 ± 7.679  ms/op
IndigoJNR.benchmarkJNR  avgt   25  747.419 ± 50.118  ms/op
IndigoJNR.benchmarkJNR  avgt   25  719.520 ± 15.704  ms/op
IndigoTest.benchmarkIndigo  avgt   25  822.508 ± 69.830  ms/op

