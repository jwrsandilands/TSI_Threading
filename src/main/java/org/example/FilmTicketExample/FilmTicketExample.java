package org.example.FilmTicketExample;

public class FilmTicketExample {
    static final TicketBooth ticketBooth = new TicketBooth();

    public static void main(String[] args) throws InterruptedException {
        final var Susie = new MovieGoer("Susie");
        final var Kris = new MovieGoer("Kris");

        final var t1 = new Thread(() -> Susie.getMovieSeat("5D", ticketBooth));
        final var t2 = new Thread(() -> Kris.getMovieSeat("5D", ticketBooth));

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("Seats Given Out.");
    }
}
