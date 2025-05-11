package April_20th;

public class VolatileFirstDemo {
    // private static int count = 0;
    private static volatile int count = 0;
    //4 volatile means: tells jvm that whenever i read or write this variable, it should use only main memory and no cache should be used.
    
    //4.1 when a core wants to write a piece of data marked as volatile, that piece of data will enter the store buffer, and will immediatly ask the store buffer to flush the queue and all the write operations will be sent to main memory, another thing is that L1, L2, and L3 will be invalidated for this volatile variable (meaning if we have this variable in a cache, we will tell the core to ignore it).
    
    //5 the side effect is that all the cores that need that piece of volatile data, they are forced to get it from main memory which has the updated and newest version.

    //6 only the volatile variables ignore the caches and get it from main, if we have other normal variables, they work like they are and nothing changes.

    //7 when we get some volatile data from main memory, we can cache it and use it until the next write happens, then caches invalidated.

    private static volatile Object s = new Object();
    //8 when we mark it as volatile, only the pointer is volatile and not the object class itself, so if we later make s = null, everyone will see it right away because they got it back from main memory.

    public static void main(String[] args) {
    //1 we create two threads that implement the count, so we have a counter that has no locks and it runs 1000 times and adds one each time
    Runnable incTask = ()->{
        for(int i = 0; i<100000; i++){
            count++;
            //2 if we changed the count++ to count = 42, the jvm will send the machine instructions to the cpu, and it will be one core in the cpu since we made one thread. and its a write operation here, and we write the piece of data into memeory, and the value of that data is 42, and the memory we talk about is the integer that we refer to which is named count. there is some optimization and the write is not sent to the main memory right away, it is cached sometimes. so when a core writes something and wants to write it into main memory, it is cached in the core, so if a core does it then it is cached in that core's cache layer (L2 and L1 i think, not L3 tho). then this write command which was cached will enter a queue which the queue is called a store buffer, this queue will eventually be sent to main memory. So basically the write updates the cache that the core itself has, and this cache is sent to the main memory eventually since the core is fast and the main memory is slow. store buffer was added to compensate for the main memory's slowness
            
            //3 this means that if a core writes a variable for some variable, it is not nescessary that the other cores are able to see it, since it is still cached in the core's own cache. so if we have 2 threads that have the count, the only way for both to have updated variables right away and not have old data, we need to invalidate the cache for when we retrieve the data and also we need the main memory to have already saved the variable itself. Cores rely on their own cache for getting and sending data, the advantage is that its fast and the bad thing is that sometimes they dont have access to the new data since when one writes it saves it on its own cache and the other caches dont have that.




        }
    };
    Thread t1 = new Thread(incTask);
    Thread t2 = new Thread(incTask);
    //9 using volatile is unbelievably slower compared to when we dont use it, since we always force it to write to main memory. this is the main disadvantage

    //10 volatile is not a replacement for locks

    t1.start();
    t2.start();

    try{
        t1.join();
        t2.join();
    
    }catch(Exception ex){

    }    
    System.out.println(count);
    //11 we changed the 1000 to 100000 and we can see its not thread safe, we get the count from main memory, add 1 to it, return it to main memory, these two threads work in parallel, they get it from main memory, see one value, both add 1, send it back to main, so here +1 is lost. we still need a lock here as we can see. since volatile is only about the visibility of the value and does not guarantee that the variable is accessed one at a time.

    
    }
}
