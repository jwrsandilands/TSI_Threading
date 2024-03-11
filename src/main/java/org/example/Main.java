package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final var myThread = new MyThread();
        myThread.start();
        System.out.println("This is the main thread!");

        final var myRunnable = new MyRunnable();
        final var runnableThread = new Thread(myRunnable);
        runnableThread.start();
        System.out.println("This is the main thread!");

        final var interruptRunnable = new LongLivedRunnable();
        final var interuptThread = new Thread(interruptRunnable);
        interuptThread.start();

        Thread.sleep(10);

        interuptThread.interrupt();
    }
}