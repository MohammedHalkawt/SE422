package April_8th;
public class WaitNotifyExample {
 public static void main(String[] args) {
    SharedResource resource = new SharedResource();

    Thread producer = new Thread(()-> {
        for(int i = 0; i<5; i++);
        resource.produce();
    
    });

    Thread consumer = new Thread(()->{

       
        for(int i = 0; i<5; i++){
            resource.consume();
        }
    });

    producer.start();
    consumer.start();

 }   
}
class SharedResource{
    private boolean available = false;

    public synchronized void produce(){
        System.out.println("produced");
        available = true;
        notify();//2 this means notify anyone whi is waiting in sleeping mode and wake them up, so if we never reach notify its called thread starvation. after calling notify the other thread is woken up again it will aquire the lock again and resume from the wait line.
        try{
            wait();
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }//3 we can add the wait here as well which will wait for another thread to wake it up
    }


    public synchronized void consume(){
        try{
            wait();
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }        //1 we will add the wait method that exists as part of the object class, it needs a try and catch. the wait method releases the lock and makes the thread go into sleeping mode, until another thread calls the notify method, which will wake this one up again, but until then the lock is released from here.
        System.out.println("consumed");
        available = false;
    }
}
//4 wait puts a thread into sleeping mode until it recieves a notify signal on that lock. this is coordination between two threads will be waiting for eachother to finish their works. we chain the threads through the act of event-driven programming, where one thread waits for a signal which is an event, and waits for a notify to be recieved form another thread.

//5 what if more than 1 thread is waiting for a signal which is notify. first the lock will be released from the thread that sends out the notify, and if there are 2 waiting threads, java randomly gives one of the threads the signal, which wakes it up and takes the lock, while the other one stays asleep. notify cannot target a thread, so you cannot say i want to wake up t2 but not t3, we need a bit of engineering to do that but cannot do it right away. we also have notifyAll(), if we call that, then java wakes up the first thread first and gives it the lock, waits for it to finish, takes away its lock and gives it to the second sleeping thread and wakes that up as well, so the sleeping threads will get into a queue and wait for their turn.
//6 what these methods give you is that they allow you to define the happens before relationships.