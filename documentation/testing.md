## About testing and performance

### Week 5

#### Testing in general

Most classes have been tested with JUnit as soon as possible after the first implementation. I would have liked to actually try the test driven development way of writing tests before implementation, but I still found it useful to first figure out a basic draft of a class.

I'm aiming for writing tests for any methods doing something more complicated than merely returning or setting values. So far I haven't tested the ui-package, nor the main.


#### Performance testing

This is the first week of performance testing. I tested both algorithms with a fairly small input, but the Earley parser is already somewhat faster than CYK.

I tested the algorithms in separate classes (both in package `efficiencytesting`), since i read that the state of the processor or the JVM could have an effect on performance if the tests would be done during the same run.

I measured the time with `System.nanoTime()` right before and after the execution of each algorithm, and substracted the time before from the time after.

The CYK-algorithm manages to parse the given sentence in the range of 0.002-0.004 seconds, whereas the Earley parser takes 0.0015-0.0025 seconds.

(More info will follow.)
