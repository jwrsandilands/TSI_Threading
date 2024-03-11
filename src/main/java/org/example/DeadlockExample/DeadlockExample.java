package org.example.DeadlockExample;

public class DeadlockExample {
    private static final CookieJar cookieJar = new CookieJar();

    public static void main(String[] args) throws InterruptedException{
        //takeTurnsToEatExample();
        leaveLastCookieExample();
    }

    public static void takeTurnsToEatExample() throws InterruptedException {
        final var Fred = new Snacker("Fred");
        final var Gabbie = new Snacker("Gabbie");


        final var t1 = new Thread(() -> {
            try {
                Fred.eatCookieAfter(Gabbie);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        final var t2 = new Thread(() -> {
            try {
                Gabbie.eatCookieAfter(Fred);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("Cookies are eaten.");
    }

    public static void leaveLastCookieExample() throws InterruptedException{
        final var Vincent = new Snacker("Vincent", cookieJar);
        final var Emily = new Snacker("Emily", cookieJar);

        final var t1 = new Thread(() -> {
            try {
                Vincent.eatOrLeaveLastCookieFor(Emily);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        final var t2 = new Thread(() -> {
            try {
                Emily.eatOrLeaveLastCookieFor(Vincent);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Cookies are eaten.");
    }
}
