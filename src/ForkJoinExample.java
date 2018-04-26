import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample extends RecursiveTask<Integer> {


    public static void main(String[] args) {
        ForkJoinExample example = new ForkJoinExample(1, 10000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(example);
        try {
            System.out.println("submit.get()  " + submit.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


    private final int threhold = 5;
    private int first;
    private int last;

    public ForkJoinExample(int first, int last) {
        this.first = first;
        this.last = last;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if (last - first <= threhold) {
            for (int i = first; i <= last; i++) {
                result += i;
            }
        } else {
            int middle = first + (last - first) / 2;
            ForkJoinExample leftTask = new ForkJoinExample(first, middle);
            ForkJoinExample rightTask = new ForkJoinExample(middle + 1, last);
            leftTask.fork();
            rightTask.fork();
            result = leftTask.join() + rightTask.join();
        }

        return result;
    }
}
