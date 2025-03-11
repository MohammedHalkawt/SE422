package Feb_27th;

public class Test {
    public static void main(String[] args) {
        System.out.println("start main");
        
        // LoopPrintTask t1 = new LoopPrintTask();
        //3 we can use polymorphism to make it cleaner like this:
        // Thread t1 = new LoopPrintTask();


        //4 also we can remove t1 by doing this, this is because we wont reallyy need t1 after this
        // new LoopPrintTask()
        //         .start();//4.1 this creates the object and starts it immediately.


        //5 we can also use anonymous classes like this:
        // Thread t1 = new Thread(){
        //     //5.1 this creates a class on the fly and we override the run in the main method
        //     @Override
        //         public void run(){
        //             for(int i = 0; i < 5; i++){
        //                 System.out.println("loop: " + i);
        //             }
        //         }
        // };
        //5.3 advantages and disadvantages of anonymous classes:
        /*
         disadvantages: not very readable,
         once we create one object with it, we cannot create more objects and if we want to do so, we need to have more anonymous classes.

         advantages: makes it less complex for example if we have a thread we want to use once we dont create a new class for it and just run it in main, if we want to run the same thread again then having a separate class is better but if we run it once and never repeat it again then anonymous classes is better. 
         */



        //6.2 if we use the interface then we cannot use run() or start() since they dont exist anymore in the runnable interface
        //6.3 using runnable is better than inheritance
        //6.4 first we create the object from our thread class that we have, then we create another object from the general thread class and pass our own thread class to it, then we call the .start and now it works
        // LoopPrintTask task1 = new LoopPrintTask();
        // Thread t1 = new Thread(task1);
        // t1.start();

        //7 we can also do this to make it shorter and better:
        // Thread t1 = new Thread(new LoopPrintTask());
        // t1.start();
        
        //8 even shorter:
        // new Thread(new LoopPrintTask())
        //                             .start();
        


        //9 another way: here we used runnable which is the interface and used anonymous classes to override the run method in the thread class, so here we create an anonymous class that implements runnable
        new Thread(new Runnable(){
            @Override
            public void run(){
                for(int i = 0; i < 5; i++){
                    System.out.println("loop: " + i);
                }
            }
        }).start();


        new Thread(){//9.1 this is another way that we already did it extends thread class in the anonymous class
                @Override
                    public void run(){
                        for(int i = 0; i < 5; i++){
                            System.out.println("loop: " + i);
                        }
                    }
            }.start();

        //10 lambda operations with runnable, this syntax works only if you have one method, this implements runnable
        new Thread(() ->{
                for(int i = 0; i < 5; i++){
                    System.out.println("loop: " + i);
                }
        }).start();//11 although this looks like a code in main, it launches a new thread and execution path thats different from main. also it knows it is runnable becaus ethe constructor takes runnable and has one method which is run()

        


        System.out.println("end main");//2 we see that this runs before the loop that we call, this is because its not a single thread, main started the thread and moved on and it took a while for the new thread we created to be executed.

        
    }
}
