package org.example.DeadlockExample;

public class DeadlockExample {
    public static void main(String[] args) throws InterruptedException{
        //takeTurnsToEatExample();
        //leaveLastCookieExample();
    }

    public static void takeTurnsToEatExample() throws InterruptedException {
        final var Fred = new Snacker("Fred");
        final var Gabbie = new Snacker("Gabbie");

        final var t1 = new Thread(() -> Fred.eatCookieAfter(Gabbie));
        final var t2 = new Thread(() -> Gabbie.eatCookieAfter(Fred));

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("Cookies are eaten.");
    }

    public static void leaveLastCookieExample() throws InterruptedException{
        final var cookieJar = new CookieJar();
        final var Vincent = new Snacker("Vincent", cookieJar);
        final var Emily = new Snacker("Emily", cookieJar);

        final var t1 = new Thread(() -> Vincent.eatAndLeaveLastCookieFor(Emily));
        final var t2 = new Thread(() -> Emily.eatAndLeaveLastCookieFor(Vincent));

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("Cookies are eaten.");
    }
}
