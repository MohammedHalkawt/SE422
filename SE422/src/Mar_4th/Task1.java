package Mar_4th;

public class Task1 implements Runnable {
    Info obj;
    int id;

    public Task1(Info arg1){
        obj = arg1;//5 here we got the sid from main and saved it to a variable here so we can access it later
    }

    public Task1(int arg1){
        id = arg1;
    }
    
    public void run() {
        // System.out.println("Task1: " + "hello");
        // System.out.println("Task1: "+Info.sid);//3.2 can also be accessed here

        //Info i = new Info();
        //i.sid; 4.2 this sid will be different from the main since a new object has been created, we can use a constructor


        //5.2 now this works
        // System.out.println(obj.sid);


        while(true){//7.1 this will run and use the old sid for a few seconds, then it will be updated, this is because we passed an object which is passing by reference, and that means since we changed the id in main, it changes here because they have the same memory place
            // System.out.println("thread1: "+ obj.sid);
            
            System.out.println("thread1: "+ id);//8.1 here it wont change since its permitive and that means this one has a separate memory compared to the one in main

            
            
            try{
                Thread.sleep(500);
            }catch(Exception ex){}
        }
    }
    
}
