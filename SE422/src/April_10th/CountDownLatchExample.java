package April_10th;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) {
        //1 we would have a thread called launcher, this launcher shouldnt run unless the other threads are called, its like a rocket launcher scenario, so we have other threads and we dont know their order and cannot predict them. but we know that the launcher thread cannot run unless all the others have run.
        CountDownLatch latch = new CountDownLatch(3);//2 we will see this as a box and would put the initial value as 3, and we will monitor this and when it becomes 0, we can call the launcher thread i think. we will change the number to how many threads we need to run before the launcher thread
        
        //new Thread(new Launcher(latch)).start(); 4 used to call the specific threads

        new Thread(new Worker(latch, "Rocket Systems")).start();
        new Thread(new Worker(latch, "Fuel Engines")).start();
        new Thread(new Worker(latch, "Weather Systems")).start();
        new Thread(new Launcher(latch)).start();// 6 this is called after everything else is finished because the counter would be zero then

    }   
}
