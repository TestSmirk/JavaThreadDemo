import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public synchronized void func1(){
            for (int i = 0; i < 10; i++) {
                System.out.print(i+" ");
            }
        System.out.println("-");
    }
    public static void main(String[] args) {
        Main main = new Main();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(main::func1);
        executorService.execute(main::func1);
    }
}
