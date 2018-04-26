import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadUnsafeExample {
    private AtomicInteger cnt = new AtomicInteger();

    public  void add() {
        cnt.incrementAndGet();
    }

    public int get() {
        return cnt.get();
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(start);
        final int threadSize =1000;
        ThreadUnsafeExample threadUnsafeExample = new ThreadUnsafeExample();
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; i++) {
            executorService.execute(() -> {
                threadUnsafeExample.add();
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
            executorService.shutdown();
            System.out.println(threadUnsafeExample.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis()-start);
    }
}
