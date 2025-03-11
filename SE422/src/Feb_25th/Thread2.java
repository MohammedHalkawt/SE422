package Feb_25th;

public class Thread2 extends Thread{//5.2 this class is the same as thread1 so no notes
    public void run(){
        while(true){
            System.out.println("loop 2");
            try{
                Thread.sleep(1000);
            }catch(Exception ex){
            }
        }
    }
}
