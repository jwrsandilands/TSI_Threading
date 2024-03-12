package org.example.FilmTicketExample;

public class MovieGoer {
    public String name;

    MovieGoer(String name){
        this.name = name;
    }

    public void getMovieSeat(String seat, TicketBooth ticketBooth){
        TicketBooth.giveTicketTo(seat, this.name);
    }
}
