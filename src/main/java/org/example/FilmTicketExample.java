package org.example;

import java.util.ArrayList;

public class FilmTicketExample {
    static final ArrayList<String> ticketSeats = new ArrayList<>();

    static class RequestTicketRunnable implements Runnable{
        private final String name;
        private final String seatRequest;

        RequestTicketRunnable(String name, String seatRequest){
            this.name = name;
            this.seatRequest = seatRequest;
        }

        public void run(){
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            synchronized (ticketSeats) {
                if (ticketSeats.contains(seatRequest)) {
                    ticketSeats.remove(seatRequest);
                    System.out.println(ticketSeats);
                    System.out.println("Seat given to " + name);
                } else {
                    System.out.println("Seat already taken");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String[] alphabet = new String[]{
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"
        };
        for(int x = 1; x <= 10; x++) {
            for(int y = 0; y <= 9; y++){
                ticketSeats.add(Integer.toString(x) + alphabet[y]);
            }
        }

        final var requestThreadOne = new Thread(new RequestTicketRunnable("Bert","9A"));
        final var requestThreadTwo = new Thread(new RequestTicketRunnable("Maggie", "9A"));

        requestThreadOne.start();
        Thread.sleep(10);
        requestThreadTwo.start();
    }
}
