package Mar_2nd;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
public class ThreadPoolExample{
    public static void main(String[] args) {
        // ExecutorService executor = Executors.newFixedThreadPool(3);
        //1 this means that at most the threads that will be created is 3, also executors follow the factory design pattern thats why we dont have new and just have executors.newfixedthreadpool().


        
        // executor.execute(() -> {//2 This executor.execute or even executor.submit submits a task and needs a runnable object, the runnable object is a loop that prints AUIS 100 times, so the task is represented as a runnable object and we created it through a lambda expression.
        //     for(int i = 0; i < 1000; i++){
        //         System.out.println("AUIS");
        //     }
        // });

        //3 even if we create this and make it a 100 tasks, it only makes at most 3 and puts the other ones in a queue:
        // for(int i = 0; i < 100; i++){
        //     executor.execute(() -> {
        //         for(int j = 0; j < 1000; j++){
        //             System.out.println("AUIS");
        //         }
        //     });
        // }

        //4 if we have 3 and we have 23 cores, its not good and it promotes more concurreny instead of parallelism. if we set 22 then we have 22 when we are busy, other times most of the cores are asleep.  for us if the cores we have is 23 and we use 22 its good, but if we give it to another system then its not good if they have less or more. so we can get the number of cores from the system.
        //4.2 we can use this:
        int cores = Runtime.getRuntime().availableProcessors();

        //4.3 then we use the cores+1 instead of the 3. and we say +1 because??? search for it
        ExecutorService executor = Executors.newFixedThreadPool(cores+1);

        for(int i = 0; i < 100; i++){
            executor.execute(() -> {
                for(int j = 0; j < 1000; j++){
                    System.out.println("AUIS");
                }
            });
        }
    }
}