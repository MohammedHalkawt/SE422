import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class IncrementBenchmark {
    private static final int THREAD_COUNT = 16;
    private static final int ITERATIONS = 1_000_000;
    
    private static final AtomicLong atomicCounter = new AtomicLong();
    private static final LongAdder adderCounter = new LongAdder();
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Benchmarking with " + THREAD_COUNT + " threads and " + ITERATIONS + " iterations per thread");
        
        // Benchmark AtomicLong
        long atomicTime = benchmarkAtomicLong();
        System.out.println("AtomicLong.incrementAndGet() took: " + atomicTime + " ms");
        
        // Benchmark LongAdder
        long adderTime = benchmarkLongAdder();
        System.out.println("LongAdder.increment() took: " + adderTime + " ms");
        
        // Verify counts
        System.out.println("AtomicLong final count: " + atomicCounter.get());
        System.out.println("LongAdder final count: " + adderCounter.sum());
    }
    
    private static long benchmarkAtomicLong() throws InterruptedException {
        atomicCounter.set(0);
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                for (int j = 0; j < ITERATIONS; j++) {
                    atomicCounter.incrementAndGet();
                }
            });
        }
        
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        
        return System.currentTimeMillis() - startTime;
    }
    
    private static long benchmarkLongAdder() throws InterruptedException {
        adderCounter.reset();
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                for (int j = 0; j < ITERATIONS; j++) {
                    adderCounter.increment();
                }
            });
        }
        
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        
        return System.currentTimeMillis() - startTime;
    }
}

//when to use which one: for cases where the object is nto shared a lot, we dont need to use longadder, a disadvantage of longadder is that we cannot get the result immediatley, so for the incrementAndGet its easy to get the result back, but in the longAdder, its an evetually consistent solution, since if we want to get the result in the middle, its likely that we dont get an accurate value since when we scan it it might change while we are scanning and it will become outdated.


//HW: explain how the method sumThenReset() is threadsafe.