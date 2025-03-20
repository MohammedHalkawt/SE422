package Mar_20th;

public class Main {
    public static void main(String[] args) {
        Thread t1 = new ThreadOne();
        Thread t2 = new ThreadTwo();

        t1.start();
        t2.start();

        //1 without delays everything works, 1 gets both then 2 gets both
    }
}
