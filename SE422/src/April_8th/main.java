package April_8th;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class main {
    public static void main(String[] args) {

        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(cores+1);

        // for(int j = 0; j<cores+1; j++){//3 no need to say cores+1 you can say arr.length..
        //     executor.execute(()->{
        //         for(int i = 0; i< arr.length; i++){//1 you are not using the thread sthemselves
        //             multiply(arr[i], arr[i+1]);
        //         }
        //     });
        // }
        
    }
    public synchronized void multiply(int firstNum, int secondNum){//2 how can you call it its not static, you need an object to call this method from above
        int result = firstNum*secondNum;
        System.out.println("result:" + result);
    }
}
