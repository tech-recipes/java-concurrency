import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ConuntDownLatchExample {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        System.out.println("Main Thread Started");
        new Thread(new Task(countDownLatch,2)).start();

        new Thread(new Task(countDownLatch,5)).start();

        new Thread(new Task(countDownLatch, 9)).start();


        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main Thread Completed");
    }

    static class Task implements Runnable{
        CountDownLatch countDownLatch;
        int secondsWait;
        public Task(CountDownLatch countDownLatch, int secondsWait){
            this.countDownLatch = countDownLatch;
            this.secondsWait = secondsWait;
        }
        @Override
        public void run() {

            try {
                System.out.println("Task Started"+ Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(secondsWait);
            } catch (InterruptedException e) {
                System.out.println("Thread Interrupted");
            }
            System.out.println("Task Completed "+ Thread.currentThread().getName());
            countDownLatch.countDown();
        }
    }
}
