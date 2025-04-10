package April_10th;

import java.util.concurrent.CountDownLatch;

public class Launcher implements Runnable {
    private CountDownLatch latch;
    public Launcher(CountDownLatch latch){
        this.latch = latch;
    }

    public void run(){
        try{
            System.out.println("Main thread waiting for systems to be ready");
            latch.await();//3 this waits for the others and until the counter becomes 0
            System.out.println("All systems ready");
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}
