import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<int[]> ints = new ArrayList<>();
        ints.add(generatePositiveArray(10000, 20000, 900000));
        ints.add(generatePositiveArray(9000, 50000, 200000));
        ints.add(generatePositiveArray(3000, 1000, 500000));

        LongAdder sum = new LongAdder();

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ints.forEach(ints1 -> executorService.submit(() -> {
            int sum1 = Arrays.stream(ints1).reduce(0, Integer::sum);
            sum.add(sum1);
        }));
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        
        System.out.println("Общий доход: " + sum);
    }

    public static int[] generatePositiveArray(int size, int min, int max) {
        int[] ints = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            ints[i] = random.nextInt((max - min) + 1) + min;
        }
        return ints;
    }
}
