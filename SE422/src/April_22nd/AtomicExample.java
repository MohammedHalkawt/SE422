package April_22nd;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {
    public static void main(String[] args) {
        
        AtomicInteger count = new AtomicInteger(0);//1 we use this constructor which takes the initial value from us (the default one puts 0 automatically), and remember this is not a permitive data type, so we cannot do count++ here right away and need to use a method for it, we also cannot say count = 55 for example and need to call a method
        //4 if we even make this static and allow the others to access it, still it will be thread safe and will not cause data corruption

        //int result = count.getAndIncrement();//2 returns current value to the caller and adds one to it after the method is done (like ++count)and it is atomic
        
        //result = count.incrementAndGet();//2.1 like count++, add one and then get it back to the caller
        //3 for these methods inside the atomic class, they are thread safe by nature and do not need locks

        for(int i = 0; i<1000; i++){
            new Thread(()->{
                count.incrementAndGet();
                //8 adding another incrementAndGet then it still is thread safe and no problem, and if we use decrementAndGet it still the same idea

                // if(count.get() == 0){
                //     count.set(1);//9 this is not threadsafe, the get alone and the set alone are thread safe, but together not since there is a chance that a thread will come in between and will change the count before set does it. without having locks do not do this
                //     count.compareAndSet(0,1);//9.1 this works fine no problem to use thsi without locks

                //     if(a.get() == 0 && b.get()==0){
                //         a.set(1)
                //         n.set(1);
                //     }//10 still not thread safe because i may enter the if then the a and b are changed and are not 0 anymore.
                // }
            }).start();
        }
        try{Thread.sleep(1000);}catch(InterruptedException e){}

        System.out.println("Final Count: " + count.get());
        
        //5 compareAndSet(); this needs 2 values from us and the other methods here rely on this one as well, the first value is the expected value, and the second one is the new value (so it takes the current value and the new value and it will check if the expected value is the current value you gave, and if thats correct, then it will change it to 10). so if the expected value and the current value dont match, then it will not update it to the new value. This is why it is thread safe, because if it matches then proceed and if not then no data corruption. the method returns a boolean value. the compareAndSet itself is atomic.

        //6 other terminology for compareAndSet is compareAndExchange, we also have CAS as a name for it



    }

    // public final int incrementAndGet(){//7 the incrementAndGet is atomic because it still uses the compare and set method in a loop to check if the current == expected, this is pseudocode. and most of the methods in the atomic class do this and use the compareAndSet but its not nescessary that all the atomic data type shave the same methods
    //  int prev, next;
    //  do{
    //     prev = get();
    //     next = prev +1;

    //  }  while(!compareAndSet(prev, next));   
    // return next;  
    // }
}



// Extra stuff: by kevin


//  public class AtomcicExample{
//     public static void main(String...args) {
//         AtomicINteger count = new AtomicInteger(0);

//         for(int i=0; i < 1000; i++) {
//             new Thread()) -> {
//                 count.incrementAndGet();
//                 )).start();
//             }
//             try {Thread.sleep(2000); } catch
//                 (INterruptedException e) {}
//             System.out.println("Final count:" + count.get());




//             public final int incrementAndGet() {
//             int prev, next;
//             do {
//                 prev = get(); // read current value
//                 next = prev + 2; // compute new value
//             } while (!ompareAndSet( prev, next)); // retry until successful

//             return next;



//             count.decAndGet(); so if you add one and subtract one, its atomic.

                
//                 if(count.get() ==0)
//                 count.set(1) is this atomic?










    
