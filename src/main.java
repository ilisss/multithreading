
import java.text.SimpleDateFormat;
import java.util.Date;
public class main {
    public static void main (String[] args){
//        MyThread myThread= new MyThread();
//        myThread.start();
//        System.out.println("Hellow from main");
//        Thread thread = new Thread(new Runner());
//        thread.start();

        Thread timer = new Thread(new timer());
        timer.start();

        Thread fiveSec = new Thread(new fiveSec());
        fiveSec.start();

        Thread sevenSec = new Thread(new sevenSec());
        sevenSec.start();
    }
}

class Runner implements Runnable {
    @Override
    public void run() {
        for (int i = 0;i <100;i++){
            if (i%10==0) {
                System.out.println(i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

class MyThread extends Thread {
    public void run() {
        for (int i = 0;i <100;i++){
            if (i%10==0) {
                System.out.println(i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

class timer implements Runnable{

    long currenttime=System.currentTimeMillis();

    @Override
    public void run() {
        while (true) {
            long currentTime=System.currentTimeMillis();
            long elapsedtime = currentTime- currenttime;

            Date date = new Date(elapsedtime);

            SimpleDateFormat formatter = new SimpleDateFormat("ss");
            String seconds = formatter.format(date);

            System.out.println("Текущие секунды: " + seconds);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

class fiveSec implements Runnable {
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
                System.out.println("Это сообщение выводиться каждые 5 секунд");

        }
    }
}

class sevenSec implements Runnable {
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Это сообщение выводиться каждые 7 секунд");

        }
    }
}

