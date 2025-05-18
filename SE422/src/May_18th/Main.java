import java.lang.invoke.VarHandle;

public class Main{
    static int length = 1_0;
    volatile static int [][] arr = new int [length][10_000_000];

    public static void main(String[] args) {
        int i = 0;
        int a,b,c;
        for(int j = 0; j< length; j++){
            for(i = 0; i<arr[j].length; i++){
                arr[j][i] = i*2;
            }
        }

        a = 10;
        System.out.println(a);
        // VarHandle.acquireFence();//2 load store and load load

        b = 20;
        c = 30;

        // VarHandle.releaseFence(); //1 store store and store load, these mean finish with the writes then go to the read

        VarHandle.fullFence();//3 this means all the fences need to be applied and is very agressive, we tell the compiler to not do any optimization and reordering and tell it to only do it based on code order. fullFence slows down application and needs to be a last resort


        VarHandle.storeStoreFence();//4 here we only pick one from the release fence, which is store store
        VarHandle.loadLoadFence();//5 read read, making sure all the reads are done before doing the next read

        System.out.println(i);
    }
}