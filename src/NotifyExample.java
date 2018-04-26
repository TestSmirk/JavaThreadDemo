import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotifyExample {
    public synchronized void before(){
        System.out.println("before");
        notify();
    }
    public synchronized void after(){

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after");
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        NotifyExample notifyExample = new NotifyExample();
        executorService.execute(notifyExample::after);
        executorService.execute(notifyExample::before);
    }
}
