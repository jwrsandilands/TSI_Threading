package org.example;

public class LongLivedRunnable implements Runnable{
    public void run(){
        final var tasks = 1000;
        for(int i = 1; i <= tasks; i++){
            System.out.printf("Doing task %d%n", i);
            if(Thread.interrupted()) {
                System.out.println("Interrupted.");
                return;
            }
        }
    }
}
