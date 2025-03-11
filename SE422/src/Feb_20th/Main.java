package Feb_20th;
public class Main{//After the notes file
    public static void main(String[] args) {
        while(true){
            System.out.println("Hello from AUIS");
            //1 this endless loop means that the core will always be busy, and even though we have a small set of instructions, the end of the set of instructions has a jump statement which sends it back to the beginning of the loop.
            //2 when a core gets hot and keeps constantly running, your process will be moved to a different core, so your process will be moved around based on certain criteria, this is done by the operating system, specifically the process scheduling
        }
         //3 if we have 2 loops that are infinite then it will only run the first, meaning it will not reach the second one, to counter that we can put the second one on another class and split it manually, so when we run it then jvm is loaded twice and we act as if these are 2 processes and 2 cores will be busy with them.
        // while(true){
        //     System.out.println("Hello");
        // }

        //4 if we have multiple processes and have the hardware to run them both at the same time, we say these processes are running in parallel. when we talk about parallelism we mena that 2 processes can be run at the same time, we can also use the word simultaniously, but the word simultaniously is less technical.

        //5 if we have 2 processes and 2 cores, and then add another process, then we can only run 2 processes at the same time since we have 2 cores, while we cant run them in parallel, we can still do them in sequence and swap which 2 are run, which gives the illusion that all three are running at the same time, this trick is done with the help of scheduling. we use the word concurrently for this.
            //concurrency doesnt mean they are all happening at the same time, it means that we take some away to make room for the others, and the operating system is what decides which ones are swapped. so for example if firefox launches 20 processes, it doesnt mean it will get all the cores it gets, most of the times concurrency is used for these things. the side effect is that you can launch more processes that your system can handle, which causes things to become slower if you push it too much.    
    }
}