package May_20th;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class Main{
    static int length = 1_0;
    volatile static int [][] arr = new int [length][10_000_000];

    private int data = 0;
    private static final VarHandle DATA_HANDLE;

    static {
        try{
            //7.0 look up the varhandle for the 'data' field in this class
            DATA_HANDLE = MethodHandles.lookup().findVarHandle(Main.class, "data", int.class);
        }catch(NoSuchFieldException | IllegalAccessException e){
            throw new Error(e);
        }
    }

    public void setData(int i){
        //7.1 this.data = i;
        DATA_HANDLE.set(this, i);//7.2 this is equal to the one above it but here we use the data handle
        //7.3 the difference between the two are that both can be reordered, but the jvm and compiler cannot remove the second one, while in the first they can actually remove it. we dont have fences yet here, we just mean that it will not be removed, meaning it has added new restrictions on the compiler and jvm. it sometimes wont remove so no guarantee but more restricted than the first

        DATA_HANDLE.setOpaque(this, i);//8 one key difference is it will really make sure that the code is not removed, but can still be reordered. so when you really really dont want it to be removed use this. so no removing at all but has freedom for reordering

        DATA_HANDLE.setRelease(this, i);//9 this is like opaque with a fence, menaing no removing and also has the release fences with it. it has store store and load store on the write to i on the object this.

        DATA_HANDLE.setVolatile(this, i);//10 stronger than the setRelease, since it has store store and load store before the write, and also has store load after the write, so 3 fences. this is identical to when we say volatile data. these are good since we can change the behaviour and they are not fixed

    }

    public void getData(int i){
        //11 we also have getVolatile getOpaque and stuff here too just for the load this time.
    }

    //12 we can perform the CAS operations and also CAS operations with aquire and release fences too. and all the atomic classes we have talked about are made on varhandle


    public static void main(String[] args) {
        int i = 0;
        int a,b,c;
        for(int j = 0; j< length; j++){
            for(i = 0; i<arr[j].length; i++){
                arr[j][i] = i*2;
                VarHandle.storeStoreFence();//2 this is pseudocode, this store store is because we are performing write operation a lot of times, so even though it is written once here, when we execute it it affects the write of iterations
            }
        }
        VarHandle.storeStoreFence();//3 if it was here, then we mean wiat until teh loops finish then be sure to have flushed all the write operations and so on.


        //4 here without the fence its 400ms, with the store store fence its 500ms, so more expensive, because even though the fence itself is cheap on the hardware, the fence with varhandle force the compiler and jvm to perform optimizations which is not really needed.

        //5 we tested releaseFence, and relative to the store store, its the same hardware wise compared to the store store, and it becomes almost as slow as the store store because we dont have that many writes

        //6 we tested fullfence, and its more expensive compared to the previous one, since the hardware is no longer cheap since one of the fences on x86 is not free of charge. the takeaway is do not use full fence since it will make a huge difference especially in backend.

        //7 now we will make a varhandle object to use with other things
        System.out.println(i);
    }
}