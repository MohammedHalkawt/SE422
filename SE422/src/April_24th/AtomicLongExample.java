import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
//NOTE first code part
public class AtomicLongExample {
    public static void main(String[] args) {
        AtomicLong counter = new AtomicLong(0);
        int numberOfThreads = 10;
        int incrementsPerThread = 1000;

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        for(int i = 0; i < numberOfThreads; i++){
            executor.submit(() ->{
                for(int j = 0; j <incrementsPerThread; j++){
                    long before = counter.get();
                    long after = counter.incrementAndGet();//1 atomically increment and get the new value

                    //2 our point is that dont always expect after to be before +1, since here a thread might come in between and change it, but be sure that the final result will always be correct, the example we mentioned that the long might become 40, it wasnt worng, we were just looking at it form the perspective from one thread, but overall the answer was surely correct

                    //3 with lock we dont have this case and we can predict things, since we say only us can edit the value at that moment, if we remove locks and add CAS, we no longer can predict things since it is likely it would have changed in the middle and it means that after doesnt always equal before +1

                    //4 we will now look at how we can make the performance better, while it is better than locks, we can still make it better in the add():
                    // public final int add(int num) {
                    //     int prev, next;
                    //     do {
                    //         prev = get();  
                    //         next = prev + num;
                    //     } while (!compareAndSet( prev, next));  //4.1 this is making things slow since if we fail we retry
                    
                    //     return next;
                    // }
                    //4.2 one way to do it is to have a data structure (cells) for each thread, and this removes the contention on the add operation when the add occurs, eahc thread will hold a number and not share it with the other threads, so the only way to make atomicLong faster is by not sharing the value with other threads, so each thread will add what it wants to its own box, and at the end when we want the final result we add everything with the base value. So getting the final result is O(1) since we only get the base and add the values that we have in the data structures, the previous one was O(n) since we had a lot of retries  in java there is a class called LongAdder which does what we talked about.


                }
            });
        }

        executor.shutdown();
        while(!executor.isTerminated()){
            //wait for all tasks to finish
        }
        System.out.println("FInal count:" + counter.get());
    }
}
