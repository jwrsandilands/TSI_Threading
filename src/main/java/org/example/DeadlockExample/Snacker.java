package org.example.DeadlockExample;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Snacker {

    private final Lock lock = new ReentrantLock();
    public final String name;
    public boolean lastAte = false;
    public CookieJar globalCookieJar;

    Snacker(String name){
        this.name = name;
    }

    Snacker(String name, CookieJar globalCookieJar){
        this.name = name;
        this.globalCookieJar = globalCookieJar;
    }

    public void eatCookieAfter(Snacker other) throws InterruptedException {
        if(lock.tryLock(5, TimeUnit.SECONDS)){
            if(!other.lastAte){
                System.out.println(other.name + " should take first.");
                other.eatCookieAfter(this);
            }
        }
        else{
            System.out.println(this.name + " took a cookie. Now it is " + other.name + "'s turn.");
            lastAte = true;
        }
    }

    public void eatOrLeaveLastCookieFor(Snacker other) throws InterruptedException {
        Thread.sleep(400);

        if(lock.tryLock(5, TimeUnit.SECONDS)) {
            synchronized (globalCookieJar){
                if (globalCookieJar.onFinalCookie()) {
                    System.out.println(this.name + " says " + other.name + " should take the last one.");
                    other.eatOrLeaveLastCookieFor(this);
                } else if (globalCookieJar.getCookiesRemaining() > 1) {
                    System.out.println(this.name + " ate a cookie from the jar.");
                    globalCookieJar.takeCookie();
                }
            }
        } else {
            System.out.println(this.name + " couldn't take it anymore and ate the last cookie");
            globalCookieJar.takeCookie();
        }
    }
}
