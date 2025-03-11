package Mar_11th;
import java.util.concurrent.locks.*;//6 we will use this for explicit locks

public class Algo1{
    // public synchronized void method1(){
    //     System.out.println("Method1 is called");
    // }

    // public void method2(){
    //     synchronized(this){
    //         System.out.println("Method2 is called");
    //     }
    // }
    //1 these are two ways to use intrinsic locks, need to look for their differences, and the one that has the (this) only accepts objects so we cannot pass a permitive datatype to it



    // public synchronized void method1(){
    //     System.out.println("Method1 is called");
    //     method2();
        //2 here we lock inside the method one, and we cannot unlock until the end curly bracket is reached, so since it goes to method2(), it will still be locked

        //3 when we go to method2 and say synchronized(this) it locks the object inside of method two, and he says in java if we lock the same object twice and obtain the locks, no problem, but when we have obtained two times, it means we have to also release them two times, basically we need method 2 finish then method1 finishes which means both locks are released.

        //4 reentrant locks means it allows you to lock the same object again and again and it wont timeout itself, now we will try explicit locks, both intrinsic and explicit locks are reentrant locks (reentrant lock being an object can aquire the same lock a few times).
    // }

    // public void method2(){
    //     synchronized(this){
    //         System.out.println("Method2 is called");
    //     }
    // }





    
    //5.1 to use explicit locks
    ReentrantLock l = new ReentrantLock();
    ReentrantLock l2 = new ReentrantLock();

    public synchronized void method1(){
        l2.lock();//5.3 here we have another lock and the unlock we can have it whenever we want
        System.out.println("Method1 is called");
        l2.unlock();
        method2();
    }

    public void method2(){
        l.lock();//5.2 these are explicit locks, we are required to call the locks explicitly, and if we dont call unlock then it will never release it. in synchronized we are in control when to lock but not when to unlock, but here we are in control for both ways, lock and unlock. and also l is still special to this instance of object algo1, so no problem the locking still works. also we can create many locks
        System.out.println("Method2 is called");
        l.unlock();
    }
    //6 explicit locks give more control, and in certain scenarios only explicit locks can be used, and in those scenarios they will be way better compared to intrinsic locks, so thats the advantage, but the disadvantage is that it becomes more complex, since we need to ensure we have unlocked it by ourselves.

    //7 if we lock and then an exception happens before the unlock, then the lock will not be unlocked, thats not the case in intrinsic locks when we use synchronized, so only in explicit this is a problem, and we need to use try-catch and finally here:

    public void method3(){
        try{
            l.lock();
            //return;
            System.out.println("Method3 is called");
    
        }catch(Exception e){//8 its also okay if we dont write the catch since we are not trying to catch the problem here and just want to run finally, but no problem and always remember not to use unlock in the catch, use it in finally
            System.err.println(e);
        }finally{
            l.unlock();//7.1 here it means run this even if the try block throws and exception, so when we leave the try block, the finally will always happen, so even if we have a return; in the try block, finally will still run. 
            //7.2 this is the problem with explicit locks, more complexity, since intrinsic locks are not like this
        }
    }

    //9 now we will show the uses of explicit locks:
    Integer arr[] = new Integer[500];
    //9.1 we want to lock the first two indexes everytime

    /*
     for(int i = 0; i < arr.length; i++){
        Integer e1 = arr[i];
        Integer e2 = arr[i+1];
            sync(e1){
                sync(e2){
                    syso(e1*e2);
                }
            } //9.2 the problem here is even though it goes through everything and multiplies the 2 indexes everytime, if for example we want to not unlock the second index, we cannot do that with intrinsic locks, since if we leave the sync block then it unlocks.. we need to use explicit locks for this
     
        }
     */

     //10 here we use explicit locks again in pseudocode:
     //Integer arr[] = new Integer[500];
     ReentrantLock locks[] = new ReentrantLock[arr.length];
 
     /*
      for(int i = 0; i < arr.length; i++){
        ReentrantLock l1 = locks[i];
        ReentrantLock l2 = locks[i+1];
        
        l1.lock();
        Integer e1 = arr[i];
        Integer e2 = arr[i+1];
        
        l2.lock();
        syso(e1*e2);
        l1.unlock();
        //10.1 here we dont unlock l2, which is what we wanted. this can only be done with explicit locks, also not using finally and try and catch is bad this is only pseudocode
      
         }
      */
}