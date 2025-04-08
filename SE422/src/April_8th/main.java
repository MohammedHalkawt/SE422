package April_8th;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class main {
    public static void main(String[] args) {
        
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(cores+1);

        for(int j = 0; j<cores+1; j++){
            executor.execute(()->{
                for(int i = 0; i< arr.length; i++){
                    multiply(arr[i], arr[i+1]);
                }
            });
        }
        
    }
    public synchronized void multiply(int firstNum, int secondNum){
        int result = firstNum*secondNum;
        System.out.println("result:" + result);
    }
}
