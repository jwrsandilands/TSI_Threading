package org.example.DeadlockExample;

public class Snacker {
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

    public synchronized void eatCookieAfter(Snacker other){
        if(!other.lastAte){
            System.out.println(other.name + " should take first.");
            other.eatCookieAfter(this);
        }
        System.out.println(this.name + " took a cookie. Now it is " + other.name + "'s turn.");
        lastAte = true;
    }

    public synchronized void eatAndLeaveLastCookieFor(Snacker other){
        if(globalCookieJar.cookiesRemaining == 1){
            System.out.println(other.name + " should take the last one.");
            other.eatAndLeaveLastCookieFor(this);
        }
        else if (globalCookieJar.cookiesRemaining > 1){
            globalCookieJar.cookiesRemaining --;
        }
    }
}
