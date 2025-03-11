package Feb_25th;
public class Main {//1 we have 2 loops that we want to run them simoultainiously
    public static void main(String... args) throws Exception{
        // while(true){
        //     System.out.println("loop 1");
        //     Thread.sleep(100);
        // }
        // while(true){//2 code will be unreachable, but if we assume it launches and we visualize it we will see that its one process with one thread that starts to execute the main, so the main thread (what we will cal it now) is now stuck in the first loop and since its one thread then it will never reach the second loop.
        //     System.out.println("loop 2");
        //     Thread.sleep(100);
        // }


        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();


        // t1.run();
        // t2.run();//4 we are still stuck with the first loop here, so its still under one thread thats why its not running loop 2. so we tricked java now since the second loop is unreachable but now it runs. now we have to use another thread.

       
        t1.start();
        t2.start();
         //6 after extending from thread class, we shouldnt call the run methods and should call a builtin method called start, this means that the run methods will run eventually, now if we call then it will work.

        //7 when we run it, first a process will be created by the os that loads the jvm into memory, the jvm instruction set will be loaded, then jvm will ask the os to create a thread for main() in the Main class, we will call this thread the main thread since its the one that starts the application. over time:
        /* start
         *
         *
         *
         *  create object t1
         *  create object t2
         *   
         * 
         *  t1.start() the start method will be called which is part of the t1 object, the main thread now will talk to the os to create a new thread, the second thread is a new execution path and it is independent from the first thread, this new thread asks to run the run() in t1, so this thread is busy with running the run(), and its an infinite loop, once the start() is finished, regardless of what situation the new thread is in, it will continue to other steps
         * 
         * 
         * 
         * t2.start() again same thing happens here and a new thread is created because it asks the os to create a new thread and execute the run() in t2, this will also be an infinite loop, an dmain thread will continue regardless of this new thread's state as well
         * 
         * 
         * now we have 3 threads but after some time when main thread reaches the end then we will only have the 2 threads that run the loops. also some libraries like for databases and stuff also create threads when they work.
         * 
         * 
        *///end

        //8 for this to work, we need to extend the Thread class, and also we need to call the start() and not the run(), this is also why we didnt want to change the signature of the run method, since if we want it to work then java expects us to have the run() as it is since we are overriding it.
    }
}
