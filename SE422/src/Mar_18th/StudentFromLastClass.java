package Mar_18th;

import java.util.concurrent.locks.ReentrantLock;

public class StudentFromLastClass {

    private ReentrantLock idLock, gpaLock;

    public StudentFromLastClass(){
        //1 since the default behaviour is not fair and more random, using the policy against lock starvation is much better whenever we have more than 1 or 2 threads, by fairness policy we mean the boolean constructor.
        //2 when we enable this constructor, causality also takes place since we say the oldest thread is given the lock first.
        idLock = new ReentrantLock(true);
        gpaLock = new ReentrantLock(true);
    }

    //3 if we have a thread that has called the lock method 3 times, then in order to release that lock, it needs to call the unlock method 3 times, sometimes we are not sure how many times we have called the lock method, so we dont really know how many times we should call unlock, here we can use a method called getHoldCount() from the ReentrantLock class. this method keeps track of the number of times the current thread has called the lock function, to use it we can create a loop and call unlock based on the number that the getHoldCount method gives us.

    //4 another method that we want to discuss is the tryLock() method. lets say we have 2 threads that are fighting for a lock, and one of them obtained the lock already (lets say t1), now if t2 calls the lock() as well after the fact that t1 has already called it, t2 should wait and this waiting is not productive and is wasting time. we can call the trylock method during the wait time, if we see that the lock is still taken by others, this one doesnt wait and returns false, if its available it returns true. so the difference between trylock() and lock() is that lock has wait time if the lock is not available, and trylock doesnt have that wait time and the thread can do other things and then try to access the lock again (maybe using a loop to repeatidly ask for the lock). this trylock is not applicable in every workspace and sometimes using the lock() method is the only option. we can also have a small delay with the tryLock() method, we send it a timeout as parameters, this makes it into a hybrid, since we have a wait, but its much less compared to the lock() method. we cant use this with synchronized and intrinsic locks.


    //5 Main.java and SharedResource.java are used for the rest of today's class
}