package Mar_20th;


public class ThreadTwo extends Thread {
        @Override
    public void run(){
        // try{
        //     SharedResources.LOCK2.lock();//3 now we switched the places for lock1 and lock 2, and now when we run it, it causes lock starvation, since in thread one it takes lock1 and thread2 takes lock2 right away. but after the delay, thread1 waits for the lock2 but cannot get it since thread2 has it, and thread2 waits for lock1 since thread1 has it. but none of them get the ones they need since the other one is also waiting, so none of them get to their finally block to release the locks.

        //     //4 deadlock is when you have multiple locks and multiple threads, one thread wants a lock but cannot get it since another thread has it and is waiting for someting else to release the lock, but cannot get that thing.

        //     //5 deadlock is not just between two threads, it can occur between more than two threads as long as there is a dependency between the threads no matter how many they are, so if A depends on B and B on C and C on A, then if they have locked locks then this cyclic dependency will cause holds and starvation since if one of them doesnt release it, then deadlock will happen.

        //     //6 if you want to study this in a topic you can look into DeadLocks and DAGs(not really needed)
        //     //7 we now need to find ways to avoid deadlocks, since if we get into one, there is nothing we can do and we need to restart the application

        //     //8 Two rules: if we have 1 lock, starvation i will not occur, and if we have multiple locks but they are not really shared between multiple threads, then starvation will not occur
            
        //     //9 now we will change the .lock() to .tryLock() and have an if statement where we try to see whether we can get the lock or not

        //     System.out.println("Thread 2 got LOCK2");
        //     try{ Thread.sleep(100);}catch(Exception ex){}

        //     SharedResources.LOCK1.lock();
        //     System.out.println("Thread 2 got LOCK1");

        // }finally{
        //     SharedResources.LOCK1.unlock();
        //     SharedResources.LOCK2.unlock();
            
        // }

        while(true){
            if(SharedResources.LOCK2.tryLock()){//9.2
                try{
                    System.out.println("Thread 2 got LOCK2");
                    try{ Thread.sleep(100);}catch(Exception ex){}
                    if(SharedResources.LOCK1.tryLock()){
                        try{
                            System.out.println("Thread 2 got LOCK1");
                            break;//9.3 when we have a while loop, we make it wait free, and it tries again and again in an endless loop, so if lock2 is not available, the finally for lock one runs as well and it is released, so we cannot aquire one lock alone, we need both at the same time, so with each iteration if we dont succeed in getting both, it releases the one obtained, and if we get both, it goes through both finally blocks

                            //10 we can make things cleaner by dividing things into methods and call them whenever we need them, since if we have more than two locks, things will be more messy.

                            //11 we cannot use this solution for synchronized keyword...

                            //12 the takeaway from todays lecture is that we need to avoid getting into deadlocks in the first place
                        }finally{
                            SharedResources.LOCK1.unlock();
                        }
                    }
                }finally{
                    SharedResources.LOCK2.unlock();
                }
            }
        }
    }
}
