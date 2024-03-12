package org.example.FilmTicketExample;

import java.util.ArrayList;

public class TicketBooth {
    static final ArrayList<String> ticketSeats = new ArrayList<>();

    TicketBooth(){
        generateSeats();
    }

    private void generateSeats(){
        String[] alphabet = new String[]{
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"
        };
        for(int x = 1; x <= 10; x++) {
            for(int y = 0; y <= 9; y++){
                ticketSeats.add(Integer.toString(x) + alphabet[y]);
            }
        }
    }

    public static synchronized void giveTicketTo(String request, String name){
        if(ticketSeats.contains(request)){
            System.out.println("Seat given to " + name);
            ticketSeats.remove(request);
        }
        else{
            System.out.println("Seat already taken");
        }
    }
}
