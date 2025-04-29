package April_29th;
//1 a class is immutable when you cannot change anything (the properties, attributes) of this object, one easy way to find out is to look and see if the variables are final or not.

import java.util.concurrent.atomic.AtomicReference;

//2 sometimes an object has a pointer to another object, in immutablility, we talk about the whole picture, so if in student side everything is final, everything in department side must also be final for things to be immutable.

class Student {
    private final int id;
    private final double gpa;
    private final String name = "";
    //3 dont be fooled by the final keyword, here it will only make the pointer 'name' final and immutable, so it will only point at one objevct and thats it, for String class, in the documentation it says that Strings are constant and cannot be changed, this means its immutable by itself.

    //4 if you really need things that cannot be set as immutable, keep them in a separate class, so immutable classes should be things such as data types like names and stuff, and other things like creating threads and launching things these dont need to be immutable and can be set in another class
    public Student(int id, double gpa){
        this.id = id;
        this.gpa = gpa;
    }

    public Student setId(int id){
        return new Student(id, this.getGPA());
    }

    public Student setGPA(double gpa){
        return new Student(this.getId(), gpa);
    }

    public int getId(){
        return id;
    }

    public double getGPA(){
        return gpa;
    }
}


public class Main{
    public static void main(String[] args) {
    
        Student s = new Student(110,3.0);
        s = s.setGPA(3.0).setId(100);
        //5 until now we have created 3 student objects, one for the first, and one for each set

        Student old = s;
        if(old == s){
            System.out.println("true");
        }else{
            System.out.println("false");//6.2 here it will be true since both point at the same object (memory space)
        }
        s = s.setGPA(3.0);
        if(old == s){
            System.out.println("true");
        }else{
            System.out.println("false");//6 it will say false even though both s and old have the same id and gpa, this is because the setGPA method always creates a new object and sets its GPA, so now here s is pointing at a new object and old is pointing at another one. content wise both are the same but that doesnt matter, pointers care about what they are pointing at and whether it is the same memory address or not.
        }

        // Thread t1 = new Thread1(s);
        // Thread t2 = new Thread2(s);

        Student old2 = s;
        s = s.setGPA(3.0);
        //7 as we can see, no matter what the thread is going to do, it wont propogate back to the main, so neither s nor the obj in Thread 2 will be changed when t1 runs and adds to the gpa, since a new object is always created when changes are made. so if a change happens in one side, the rest of the system will be decoupled, this happens because we pass the reference by copying and not by referencing. if we have a scenario where each thread does its own thing, immutables are very good for that since we know that changes wont propogate back.

        //8 now we will try to make changes propogate back to the original, for that we will make a mutable class and we will call it database, so if we want to pass pointers by reference, we need a class thats mutable and points at the object that we have
        // DB.ptr = s;
        // Thread t1 = new Thread1();
        Thread t2 = new Thread2();

        Student old3 = s;
        s = s.setGPA(3.0);
    }
}

class DB{//9 this can sometimes be cached and will cause problems, so we will make it volatile
    // public volatile static Student ptr;

    //10.2 we use this which makes ptr
    public static AtomicReference<Student> ptr = new AtomicReference<>();
}

// class Thread1 extends Thread{
//     private Student obj;

//     public Thread1(Student obj){
//         this.obj = obj;
//     }

//     public void run(){
//         obj = obj.setGPA(obj.getGPA() + 0.1);
//     }
// }


// class Thread2 extends Thread{
//     private Student obj;

//     public Thread2(Student obj){
//         this.obj = obj;
//     }

//     public void run(){
        
//     }
// }

// class Thread1 extends Thread{
//     private Student obj;

//     public Thread1(){
//         this.obj = DB.ptr;
//     }

//     public void run(){
//         DB.ptr = obj = obj.setGPA(obj.getGPA() + 0.1);
//         DB.ptr = obj = obj.setGPA(obj.getGPA() + 0.1);//8.1 we say the db.tr = obj = obj.setGPA(obj.getGPA() + 0.1); because if we just say db.ptr = obj.setGPA(obj.getGPA() + 0.1); then it will be using the old data, and if we say obj = obj.setGPA(obj.getGPA() + 0.1); then it will not change the static one since its a separate object. we can also say DB.ptr = DB.ptr.setGPA(DB.ptr.getGPA() + 0.1);, so we directly use the static pointer and dont need the obj anymore
//     }
// }

// class Thread2 extends Thread{
//     private Student obj;

//     public Thread2(){
//         this.obj = DB.ptr;
//     }

//     public void run(){
//         DB.ptr = obj = obj.setGPA(obj.getGPA() + 0.1);
//     }
// }


class Thread2 extends Thread{//10 a case where we see if idk wala
    private Student obj;

    public Thread2(){
        this.obj = DB.ptr.get(); //10.3 since one is atomicreference and one is student
    }

    public void run(){
        // Student old;
        // old = obj;
        // obj = obj.setId(200);

        // if(DB.ptr == old){
        //     DB.ptr = obj;//10.1 this is like a safety measure, where we check if it is still looking at the previous space or not, this is to see if changes have already been made without you knowing or not. but this will not be thread safe since another thread can come in between and change things (where the obj is pointing at now), to make it thread safe, we will change the static in the DB class, we will change to atomicreference which implements CAS
        // }



        Student old = obj;
        obj = obj.setId(200);

        // 10.3 - Attempt to set only if no other thread has changed DB.ptr in the meantime
        DB.ptr.compareAndSet(old, obj);     
    }
}
