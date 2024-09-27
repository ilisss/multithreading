import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerWithBlockingQueue {

    private static final int CAPACITY = 3;
    private final BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(CAPACITY);
    private final Random random = new Random();

    public static void main(String[] args) {
        ProducerConsumerWithBlockingQueue example = new ProducerConsumerWithBlockingQueue();
        Thread producerThread = new Thread(example.new Producer());
        Thread consumerThread = new Thread(example.new Consumer());

        producerThread.start();
        consumerThread.start();
    }


    class Producer implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    int value = random.nextInt(100);
                    if (buffer.size() == CAPACITY) {
                        System.out.println("Буфер переполнен. Производитель ждет...");
                    }
                    buffer.put(value);
                    System.out.println("Произведено: " + value);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    if (buffer.size() == 0) {
                        System.out.println("Буфер пуст. Потребитель ждет...");
                    }

                    int value = buffer.take();
                    System.out.println("Потреблено: " + value);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
