package Mar_13th;

import java.util.concurrent.locks.ReentrantLock;

public class Student {
    private int id;
    private float gpa;//4 if this was not private, it wouldnt be thread safe even if we have the synchronized keywords bellow, since its accessible outside
    
    //3 now its thread safe since everything has the synchronized keyword
    // public synchronized int getId(){
    //     return id;
    // }
    // public synchronized float getGpa(){
    //     return gpa;
    // }
    // public synchronized void setId(int arg){
    //     id = arg;
    // }
    // public synchronized  void setGpa(float arg){
    //     gpa = arg;
    // }


    //3.2 this is not the best way to do it, since if thread1 wants to getid and thread2 wants to getgpa, they cant, and this reduces performance, we want to make it so if one calls getid and one calls setid, it should be blocked, if one wants getid and the other wants to setgpa, it should be allowed, and so on like that..
    private ReentrantLock idLock, gpaLock;

    public Student(){
        idLock = new ReentrantLock();
        gpaLock = new ReentrantLock();
    }

    public int getId(){
        try{
            idLock.lock();
            return id;
        }finally{
            idLock.unlock();
        }
    }

    public float getGpa(){
        try{
            gpaLock.lock();
            return gpa;
        }finally{
            gpaLock.unlock();
        }
    }//3.3 and so on for the other set methods.. these were for the exlicit locks

    //3.4 to use intrinsic locks for this, we can have 2 Object objects that are named idLock and gpaLock, we create them then have the synchronize() inside each method, for the ids we use the idLock and for the gpa we use the gpaLock, this makes it that 2 gpa methods cannot be used at the same time, the same for ids, and this fixes our issue, if we use the (this) for it, it wouldnt make a difference from the solution we had first, since all of them would rely on the current object and its not good.

    //6 in the end here, he reiterated that the lock that is related to student needs to stay in student. he also says its better not to use the synchronize keyword on the method signature, since other classes outside wouldnt see what lock we have used and wont be able to make their system based on that desicion, and that is good if they cant since if we change the lock type in the future, no class outside should be affected (if they have made their classes based on our lock type here, then if we change the lock in the future they will be affected).

    //7 if the reentrant lock we create is public, then the complexity associated with student spills outside, and if someone does this s.idLock = null; then it breaks everything. so thats why we say the complexity of locks needs to stay in the class its related to, in our case student class. and if in a specific case you really need to make the lock public, then you can use the final keyword, which is good.

    


    
}
