package Mar_4th;

import java.util.Random;

public class Main {
    // static int sid = 2004001;
    //2.2 still wont work, we can create a new class and put it there
    // int sid = 2004001;
    //2.1 wont work
    public static void main(String[] args) throws Exception{
        // int sid = 2004001;
        //2 wont work
        
        System.out.println("Main: "+"hello");
        // System.out.println("Main: "+ Info.sid);
        //3 now it works since we have a shared class scope between the classes using the static method

        Info i = new Info();
        i.sid = 2004001;
        //4.1 here we have a unique sid now for this, but we cant send it to the Task1 right away

       

        //5.1 now we send it to Task1 with the constructor, so we passed an object which is a complex data type, from one thread to another
        // Thread t1 = new Thread(new Task1(i));
        // t1.start();//6 here we have 2 threads, one for main and one for t1, we cannot really say that Task1 itself is a thread, since it doesnt extend thread and is runnable, thats why we pass it here to thread class.
        

        // Thread.sleep(5000);//7 here we change the sid after a few seconds to see if it is automatically changed in task1
        // i.sid = 500;

        

        //8 here we will create an id to test permitive datatypes:
        int id = 2000;
        // Thread t1 = new Thread(new Task1(id));
        // t1.start();
        // Thread.sleep(5000);
        // id = 500;


        //9 here we use anonymous classes to see if we can pass the int id which was permitive to an anonymous class, and it works. so if its in the same class, then we can just send it right away, and if its static and in another class, then no problem it still will work, but remember this is a permitive datatype, meaning if we later change it then this one wont change. now we will test with objects which are passed by reference
        Thread t2 = new Thread(){
            public void run(){
                System.err.println(id);
            }
        };
        t2.start();


        //10 here we test if passing an object works or not, while it works, if the pointer or variable is changed later, it will cause an error because we need to add a final before the thing we are passing, so we should make it final random r = new random();
        final Random r = new Random();
        Thread t3 = new Thread(){
            public void run(){
                System.err.println(r.nextInt());
            }
        };
        t3.start();

         // r = new Random(); 
        // 10 this causes the error for final

        //11 lambda is the same case but he says if we dont change it later then there would be no error, so basically if java complains, then include the keyword final, and that doesnt mean the object is not modifyable anymore, only the pointer cant, which means we cannot point the pointer for another object, but we can still change the variables in the object itself
        Thread t4 = new Thread(() -> {
            System.out.println(r.nextInt());
        });
        t4.start();

        //12 in passing by value, the changes wont propogate to the other side, in passing by reference it will
       

        // Thread t1 = new Thread(new Task1());
        // t1.start();
        //1 here we have multiple threads running under the same process, which means that the mempry is shared between the threads. so now we want to transfer a number from main to t1
    }
}
