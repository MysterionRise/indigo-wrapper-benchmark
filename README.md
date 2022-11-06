### Indigo wrapper tests for different Java invocation of native libs

Project requires to have prebuilt native libs of Indigo (https://github.com/epam/Indigo)

### How to install JNI wrapper

```
mvn install:install-file -Dfile=jniwrapper-repo/jniwrap-generator-3.12.jar -DgroupId=jniwrap -DartifactId=jniwrap-generator -Dversion=3.12 -Dpackaging=jar -DlocalRepositoryPath=jniwrapper-repo -DcreateChecksum=true
mvn install:install-file -Dfile=jniwrapper-repo/jniwrap-3.12.jar -DgroupId=jniwrap -DartifactId=jniwrap -Dversion=3.12 -Dpackaging=jar -DlocalRepositoryPath=jniwrapper-repo -DcreateChecksum=true
mvn install:install-file -Dfile=jniwrapper-repo/license.jar -DgroupId=jniwrap -DartifactId=license -Dversion=1.0.0 -Dpackaging=jar -DlocalRepositoryPath=jniwrapper-repo -DcreateChecksum=true
mvn install:install-file -Dfile=jniwrapper-repo/antlr.jar -DgroupId=jniwrap -DartifactId=antlr -Dversion=1.0.0 -Dpackaging=jar -DlocalRepositoryPath=jniwrapper-repo -DcreateChecksum=true

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

## Test 2
#### Date 5.11.2022
Benchmark was completed on Mac OSX 12.6 on Apple M1 Max 64 GB
### Results

```
Benchmark                    Mode  Cnt  Score     Error   Units
IndigoJNA.benchmarkJNA  avgt    5  850.148 ± 3.754  ms/op
IndigoJNR.benchmarkJNR  avgt    5  855.261 ± 13.167  ms/op
IndigoJNA.benchmarkJNA       avgt    5  875.835 ± 18.079  ms/op
IndigoJNR.benchmarkJNR       avgt    5  876.333 ± 27.011  ms/op
IndigoJNA.benchmarkJNA       avgt    5  852.179 ± 46.401  ms/op
IndigoTest.benchmarkIndigo   avgt   25  822.508 ± 69.830  ms/op
```


