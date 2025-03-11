package Mar_6th;

public class Test{
    public static void main(String[] args) {

        SharedCounter counter = new SharedCounter();

        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 10000; i++){
                //System.out.println("t1");
                counter.increment();
            }
        });
        
        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 10000; i++){
                //System.out.println("t2");
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        try{//1 up to this point we have three threads, t1 t2 and main and they will either run concurrently or in parallel, in our case since we know cpu can handle it we cany say they are parallel
            //2 we want to make it so that the main thread prints end once t1 and t2 have finished, so it should wait for them. for this we will use join methods. it sees if they finished or not, if no, main goes to sleep and waits until they finish
            t1.join();
            t2.join();
            System.out.println("Result = " + counter.getCount());//3 this makes it wait for both of them, basically we make it certain that the print statement happens after the 2 threads are finished

            //4 we created a counter class and incremented whith both loops, but it never prints 20000, but always less, this is a problem with multithreading , and the counter++ line causes data corruption and is not thread safe, meaning if a lot of threads bombard it with data it will fail.
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
