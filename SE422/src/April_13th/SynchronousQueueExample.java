package April_13th;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueExample {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue <String> queue = new SynchronousQueue<>();//1 if we want to use student we would say <Student>.

        //queue.put("Value");//2 if we have a queue and a thread, and the thread tries to add data to the queue, and there is no take method called, then the thread who did it will be put to sleep, this means that the only way to add and take data from the queue is for 2 threads to work at the same time, so one should put and the other should take, if one of them are slow, then the other one is put to sleep. so if put is slow, then take is put to sleep until put has sent, and if put is slow then take is asleep until put sends its things.

        //3 difference between this and wait/notify: this provides a channel to exchange data between two threads, so not only you have a synchronizer, you can also transfer data from one thread to another.
        //queue.take();

        Thread producer = new Thread(() ->{
            String[] messages = {"hello", "world"};
            try{
                for(String msg : messages){
                    System.out.println("Producing: "+ msg);
                    queue.put(msg);
                    System.out.println("Produced: "+ msg);
                }
                queue.put("DONE");
            }catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() ->{
            try{
                String msg;
                while(!(msg = queue.take()).equals("DONE")){//4 will take the ones sent by the producer, run to see the results
                    System.out.println("Consumed: " + msg);
                }
                System.out.println("FINISHED");            
            }catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        });



        producer.start();
        consumer.start();

        //5 we dont target one thread here, we target a side, so if we have 2 senders and 2 recievers, we synchronizer them and if one reciever slow, then both senders slowed down
        //6 in other languages they usually have synchronousQueues but most of the time the name is different
    }
}
