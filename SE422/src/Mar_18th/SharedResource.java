package Mar_18th;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SharedResource {
    private String data = "Initial Data";
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void setData(String newData){//6 this section is us writing things and we need the lock now, and since this is a special class, instead of calling the lock method, we call this:
        lock.writeLock().lock();
        try{
            this.data = newData;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.readLock().lock();
            //9 this is a comon semantic used, where you downgrade the lock before you release it, this doesnt completely give up the lock entierly and just releases it for others in case anyone else needs the readlock and there is no writelock in another method, if we put the readlock().lock after the unlock for the writelock, its another case of trying to get the lock again which is not guaranteed, but the case when we put it above the unlock it makes it guaranteed.
            lock.writeLock().unlock();
        }
        this.data = newData;
    }

    public String getData(){
        lock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "is reading: " + data);
            Thread.sleep(500);
            return data;
        }catch(InterruptedException e){
            e.printStackTrace();
            return null;
        }finally{
            lock.readLock().unlock();
        }
    }

}
//7 this is good because look at this case: if both are getData, then one of them has to wait for the lock anyway, if one is set, still one has to wait, if both are set, still one has wait. but if we use readWriteLocks, its close to the current case but different in one aspect, if both try to access the lock for reading (the getData method), they will both obtain it, but not when one of them or both of them are trying to access the write lock. this is good since when both are read the is no thread in changing the data and both should be allowed to access it. but we need to be careful where we use read and where we use write, since java and the compiler dont check whats thread safe and what isnt.

//8 multiple threads can aquire the read lock simultainously as long as there is no write lock.
