package Mar_6th;

public class SharedCounter {
    private int count = 0;

    // public void increment(){
    //     count++;
    //     //4.1  the thread needs to get the current value from memory and read count, then perform the arithmatic and write the new count value whenever the .increment method runs. for example lets say t1 takes it and reads and increments 1, before adding it back, if t2 reads it then it will also take 5 and make it 6, and then both will have 6 and even if t1 has written 6 and t2 has 6 now, it will write it again and now +1 has dissapeared for us. This is called the data race condition and will occur if parallelism or concurrency exist. we need to fix this issue and to do that one way is to use intrinsic locks
    // }


    //5 intrinsic locks are a builtin feature and can work by writing synchronized on the method

    //public synchronized void increment(){
        //count++;//5.1 so if t1 comes then it reads from memory then locks the current object then increments then writes and unlocks. its like a choke point and says that only one thread can execute this method at a time, so it will throw away parallelism and some part of concurrency for this variable. it basically makes it either t1 happens before t2 or t2 happens before t1 but they never occur concurrently. and the magic is that we never know which one happens before the other because of threads. after using the intrinsic lock we can say this method is a thread safe method.
        
        //System.out.println("Inc start");
        //count++;//6 even though this part alone needs to be locked and the other prints are thread safe, it still locks both the other two if we dont do anything. we can do this: instead of writing synchronized on the method, we write it as a method and have the thing we want inside of it like in 6.1 bellow
        //System.out.println("Inc end");
    //}

    public void increment(){//6.1 this is more optimized and makes it so that we lock only the incrementation of the variable and not the whole method, we have more flexibility with the intrinsic lock in java with objects, instead of the this we can also use other things like system.in if we need them, so its okay if its not related to the object we are working on at all. both this and the previous one we did are thread safe
        System.out.println("Inc start");
        synchronized(this){
            count++;
        }
        System.out.println("Inc end");
    }

    public int getCount(){
        return count;
    }
}

/*
How to know if a line is thread safe:
we have t1 and t2, one is going to read x(first) and write(second) x when the other is going to write x(first) and read x(second), this is thread safe

if both read x then its thread safe
if both write x then its still thread safe, since no one is reading then values that are being changed

if t1 writes and t2 reads its safe, since if t1 executed then it gets the old value and then t2 reads that new one..

if t1 works on x and t2 works on y, then its safe
emmutable objects are thread safe... such as strings, we dont need locks fro them

HW: Java Object Monitor

 */
