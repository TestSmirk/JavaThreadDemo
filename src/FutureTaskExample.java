import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskExample {
    public static void main(String[] args) {
        FutureTask<Integer> integerFutureTask = new FutureTask<>(() -> {

            int result = 0;

            for (int i = 0; i < 100; i++) {
                Thread.sleep(10);
                result += i;
            }
            System.out.println("result "+result);
            return result;
        });
        Thread thread = new Thread(integerFutureTask);
        thread.start();
        Thread thread1 = new Thread(() -> {
            System.out.println("other task is running");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        });
        thread1.start();
        try {
            System.out.println("integerFutureTask.get() "+integerFutureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
