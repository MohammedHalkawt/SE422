package Mar_20th;

public class ThreadOne extends Thread {
    @Override
    public void run(){
        // try{
        //     SharedResources.LOCK1.lock();
        //     System.out.println("Thread 1 got LOCK1");
        //     try{ Thread.sleep(100);}catch(Exception ex){}//2 these delays make it so that thread 1 needs to finish then thread 2 can start, or thread 2 needs to finish then thread 1 can start. so you cannot ask for both locks immediately and you need to first ask for lock one then for lock two. so basically we mean that sequencial matters in locks, if you ask for lock one, and you cannot get it right away, you need to wait for it and cannot get lock two in the meantime.
            
            

        //     SharedResources.LOCK2.lock();
        //     System.out.println("Thread 1 got LOCK2");

        // }finally{
        //     SharedResources.LOCK1.unlock();
        //     SharedResources.LOCK2.unlock();
            
        // }



        while(true){
            if(SharedResources.LOCK1.tryLock()){//9.1
                try{
                    System.out.println("Thread 1 got LOCK1");
                    try{ Thread.sleep(100);}catch(Exception ex){}
                    if(SharedResources.LOCK2.tryLock()){
                        try{
                            System.out.println("Thread 1 got LOCK2");
                            break;
                        }finally{
                            SharedResources.LOCK2.unlock();
                        }
                    }
                }finally{
                    SharedResources.LOCK1.unlock();
                }
            }
        }
    }   
}
