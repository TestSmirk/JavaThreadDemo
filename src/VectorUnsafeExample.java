import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VectorUnsafeExample {
    private static Vector<Integer> vectoe = new Vector<>();
    public static void main(String[] args) {
        while (true){
            for (int i = 0; i < 100; i++) {
                vectoe.add(i);
            }
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(() -> {
                synchronized (vectoe) {
                    for (int i = 0; i < vectoe.size(); i++) {
                        vectoe.remove(i);
                    }
                }
            });
            executorService.execute(() -> {
                synchronized (vectoe) {
                    for (int i = 0; i < vectoe.size(); i++) {
                        vectoe.get(i);
                    }
                }
            });
            executorService.shutdown();
        }
    }
}
