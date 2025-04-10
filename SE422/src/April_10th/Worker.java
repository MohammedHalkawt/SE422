package April_10th;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {
    private CountDownLatch latch;
    private String name;

    public Worker(CountDownLatch latch, String name){
        this.latch = latch;
        this.name = name;
    }

    public void run() {
        System.out.println(name + " is preparing");
        try{
            Thread.sleep((long)(Math.random()*3000));
        }catch(InterruptedException ex){
            System.out.println(ex);
        }
        System.out.println(name+"is ready");
        latch.countDown();// 5 this decreases the counter, meaning one thread has been executed successfully
    }
}
