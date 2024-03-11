package org.example.DeadlockExample;

public class CookieJar {
    private int cookiesRemaining = 2;

    public Boolean onFinalCookie(){
        return cookiesRemaining == 1;
    }
    public int getCookiesRemaining(){
        return cookiesRemaining;
    }

    public void takeCookie(){
        cookiesRemaining -= 1;
    }
}
