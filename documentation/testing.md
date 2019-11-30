## About testing and performance

### Week 5

This is the first week of performance testing. I tested both algorithms with a fairly small input, but the Earley parser is already somewhat faster than CYK.

I tested the algorithms in separate classes (both in package `efficiencytesting`), since i read that the state of the processor or the JVM could have an effect on performance if the tests would be done during the same run.

I measured the time with `System.nanoTime()` right before and after the execution of each algorithm, and substracted the time before from the time after.

The CYK-algorithm manages to parse the given sentence in the range of 0.002-0.004 seconds, whereas the Earley parser takes 0.0015-0.0025 seconds.

(More info will follow.)
