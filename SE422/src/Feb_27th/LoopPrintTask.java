package Feb_27th;
//1 when he uses task it represents a thread
// public class LoopPrintTask extends Thread {
    // @Override
    // public void run(){
    //     for(int i = 0; i < 5; i++){
    //         System.out.println("loop: " + i);
    //     }
    // }
// }

//6 another problem that exists is if this class has another class that it inherits from, for example it extends from AUISObject and it wants to also extend from Thread, it cannot, since in java a class can only inherit from one other class, in this scenario we can use interfaces..
public class LoopPrintTask implements Runnable {
    @Override
    public void run(){
        for(int i = 0; i < 5; i++){
            System.out.println("loop: " + i);
        }
    }
}
