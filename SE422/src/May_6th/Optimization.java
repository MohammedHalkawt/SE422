package May_6th;

public class Optimization {
    public static void main(String[] args) {
        // int x = 0;
        // int y = 0;
        // y = 5;
        // int z = 0;
        // System.out.println(x+y);//1 some optimizations: the jvm will remove the z int since its never used, sometimes reordering may occur. some optimizations are considered safe, like reordering based on the sequential executing of things.

        //2 low level optimizations change teh software model a lil bit but do not change the expected output.

        //3 code for application to show optimization: he calls warmup that tries to call and execute other methods again and again. we have 2 methods that we call and the deadCodeLoop is called and x is calculated, but is never used. then the other one in usedcode is called and its useful actually. he calculated the time it takes to call the methods individually, like the deadcodeLoop() and then the UsedCodeLoop(), each one does a 100,000,000 iterations. we see that the dead code takes 0ms, which means we didnt actually execute that method, java in compile time did this because it proved to itself that removing this code is safe and having it doesnt add anything to the program. so here we can see that what is in the code is not what is executed, so jvm optimized this.
        //3.1  one way to prove that the jvm optimized things is to give some code flag thing in the command line and it will give you a lot of output, every line described what jvm did as a task. if we take a closer look we can see that somewhere it says deadcodeloop "made not entrant". which means it didnt use it. this is not the only optimiation and a lot of other optimizations happen.

        //4 by safe optimizations we mean the execution order should be in order with the sequential order, but not exactly the same.
        //5 the most used code will be optimized and not somthing that happens once in a method. also what we are required to know is that what is executed at runtime is not what you write as code, things like order and removing lines and stuff like that can happen.

        

        for(int i =0; i<100;i++){
            int x = 10;
            x = 0;//7 so in reordering lines, some things are allowed and some arent, for example if we put this bellow the int y is okay, but if we put it bellow the print statement or bellow the result, it will change the behaviour of our application and its not safe. when we are executing and also changing the order, what matters is not the lines but the result of the reordering of the instructions, and to be exact we mean the final state we have.

            //7.1 if we have a b c in this order which are lines in a program, if a is x = 0, b is y = 1, c is x = 10. and then if we change the order to a c and b, no problem since the final answer is still y = 1 and x = 10, but if we do somth like cab then the final result is x = 0 and y = 1, which is not what we have which means this changes the memory order.

            int y = 20;
            int result = x*y;
            int unusedResult = result * 1000;//6 this and the int declarations above it will be removed sinec it is dead code and is not used
            System.out.println("Hello world" + x + result);
        }


        //8 software consistency: this means what we have in the code and the executed results match even if the order has been changed. it talks about the order of memory operations. so the happens before and causality are important here.

        //9 how should we know what is consistent and what is a safe reordering? 1- perform no low-level optimizations, which slows down your application a lot but at the same time you know nothing is changed. 2- do perform then and account for safe reordering. in java it does the optimization and considers safery for reordering for only one thread, so we need to change things and make it a safe reordering for multiple threads
    }
}




/*
Extra:
Public class Optimization{
    static final int ITERATIONS = 100_000_000;
    public static void main(String[] args) {
        int x;
        int y;

        x = 0;
        x = 10;

        y = 0;
        y = 20;

        int result = x+y;

        System.out.println(result);
    }
}

What isnt acceptable as x = 0; and x = 10;. If you have the 10 before the 0 such as
    x = 10;
    x = 0;

Software consistency is about the state of the software after the reordering.

From JVM side, it would re-order things as long as it does not change the output of a single thread. But, they ignore multi-threaded things.
    Some of those re-orderings will accept the application.
    They will try and make it faster because of this.
    The hardware reordering happens anyways. 

    When we talk about consistency, we are talking about memory reordering.
    So why explain software consistency just for memory. The main reason for this is because imagine you have two cores. One is executing on another and th eother on another, they are using one shared resource.
    That shared resource is memory. So if one core changes the order of the operations and you have a certain assumption that the other is also doing this.
    */
