package Feb_25th;

public class Thread1 extends Thread{//5 we have to extend the thread class for both threads
    public void run(){//3 we cannot change the signature thats why we use try and catch
        while(true){
            System.out.println("loop 1");
            try{
                Thread.sleep(100);
            }catch(Exception ex){
            }
        }
    }
}