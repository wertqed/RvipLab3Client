import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService messageSourceService = Executors.newFixedThreadPool(300);
        for (int i = 0; i < 300; i++) {
            messageSourceService.execute(new MessageSource(i, 10));
        }
    }
}