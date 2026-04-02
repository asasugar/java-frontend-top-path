package learning.job02;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class Job02App {

    public static void main(String[] args) throws Exception {
        Path dataDir = Path.of("data");
        Files.createDirectories(dataDir);
        Path input = dataDir.resolve("input.txt");
        if (!Files.exists(input)) {
            Files.writeString(input, "apple\nBanana\napple\n", StandardCharsets.UTF_8);
        }
        try (BufferedReader reader = Files.newBufferedReader(input, StandardCharsets.UTF_8)) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            // Stream
            long distinct = lines.stream()
                    .map(s -> s.toLowerCase(Locale.ROOT)) // 忽略大小写
                    .distinct() // 去重
                    .count(); // 统计数量
            System.out.println("distinct lines (ignore case): " + distinct);
        }

        // 线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        // try-with-resources
        try {
            Future<?> f1 = pool.submit(() -> System.out.println("task1 " + Thread.currentThread().getName()));
            Future<Integer> f2 = pool.submit(() -> 1 + 2);
            Future<Integer> f3 = pool.submit(() -> 3 + 4);
            f1.get();
            System.out.println("task2 result: " + f2.get());
            System.out.println("task3 result: " + f3.get());
        } finally {
            pool.shutdown();
        }
    }
}
