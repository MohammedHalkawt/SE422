package April_27th;

class Student {
    private final int id;//4 we make it private so nobody can access it
    private final double gpa;//7 we added final to make sure these two will not be modified in the future
    // Department dept;

    public Student(int id, double gpa){
        this.id = id;
        this.gpa = gpa;
    }
    public Student setId(int id){
        return new Student(id, this.getGPA());
    }
    public int getId(){
        return id;
    }

    // public void setGPA(int id){
    //     this.id = id;
    // }

    public Student setGPA(double gpa){
        return new Student(this.getId(), gpa);
    }

    public double getGPA(){
        return gpa;
    }
}

// class Department{
//     int deptId;
// }

public class Main{
    public static void main(String[] args) {
        // Student s = new Student();
        // s.id = 100;

        //THREAD2
        // s.id = 200;//1 here we can see this is mutable since another thread can change its properties
        // s.dept.deptId = 10;//2 since we can write to the memory address directly and change things even though it is nested in an object, it means that that object is not immutable

        
        // s.setId(100);//5 still mutable since we can still change its value

        Student s = new Student(110,3.0);//6 these two will not be modified after this line and the setting of the id and gpa happens one time in the constructor

        //7 if we want to change the gpa, we need to make a new student object like this:
        s = new Student(s.getId(), 3.2);//7.1 here the pointer points at a new memory address, thus making both objects thread-safe, also the other one will not be accessible anymore and will be picked up by the java garbage collector

        //key disadvantage: the more the modifications, the more objects, meaning more code complexity and space complexity
        s = new Student(0, 3.2);//7.2 this will also break everything if you forget the student's id and place 0, now we wil fix things like this
    
        s = s.setGPA(3.2).setId(100);//8 this can call either one or both of them, this will help the user so that no problem occurs



        //9 String s is immutable btw
        //10 if we have Dept object in student and inside the dept class its not immutable, then student object is also not immutable since when we look at immutability we look at the things as a whole, so on student side its thread-safe and on dept side it isnt
    }
}
//3 even if we have an object shared between threads, it cannot be changed thus making it thread-safe





/* Atomic classes being lock free and thread safe is what separates it from other normal things. One other thing is immutable objects.
    an object can be classified as immutable when we cannot change its properties.
    It is only read only and only that. No edits can be changed.

    this.id = id; eould break the immutability.

    HW: What about the case id = id;

When designing immutable its not just abou tyour classes but what you also have in them.
    It just means you cant modify anything in that region.
    
