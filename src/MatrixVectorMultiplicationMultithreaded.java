import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class MatrixVectorMultiplicationMultithreaded {

    // Метод для умножения матрицы на вектор с использованием нескольких потоков
    public static double[] multiplyMatrixVector(double[][] matrix, double[] vector, int threadCount) throws InterruptedException {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[] result = new double[rows];

        // Пул потоков
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        // Задачи для каждого потока
        for (int i = 0; i < rows; i++) {
            final int row = i;
            executor.submit(() -> {
                double sum = 0;
                for (int j = 0; j < cols; j++) {
                    sum += matrix[row][j] * vector[j];
                }
                result[row] = sum;
            });
        }

        // Завершаем пул потоков
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // Ввод размерности матрицы
        System.out.print("Введите количество строк матрицы: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество столбцов матрицы: ");
        int cols = scanner.nextInt();

        // Ввод матрицы
        double[][] matrix = new double[rows][cols];
        System.out.println("Введите элементы матрицы:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }

        // Ввод вектора
        System.out.println("Введите элементы вектора размером " + cols + ":");
        double[] vector = new double[cols];
        for (int i = 0; i < cols; i++) {
            vector[i] = scanner.nextDouble();
        }

        // Массив для хранения времени выполнения для разных количеств потоков
        int maxThreads = Runtime.getRuntime().availableProcessors(); // количество доступных процессоров
        long[] executionTimes = new long[maxThreads];

        // Выполнение для 1, 2, ..., maxThreads потоков и замер времени
        for (int threads = 1; threads <= maxThreads; threads++) {
            long startTime = System.nanoTime();
            multiplyMatrixVector(matrix, vector, threads); // умножение с threads потоками
            long endTime = System.nanoTime();
            executionTimes[threads - 1] = endTime - startTime;
            System.out.println("Количество потоков: " + threads + ", Время выполнения: " + (endTime - startTime) / 1_000_000.0 + " ms");
        }

        // Вывод результатов
        System.out.println("\nТаблица времени выполнения для разного количества потоков:");
        System.out.println("Потоки | Время выполнения (мс)");
        for (int threads = 1; threads <= maxThreads; threads++) {
            System.out.println(threads + "       | " + executionTimes[threads - 1] / 1_000_000.0);
        }

        scanner.close();
    }
}
