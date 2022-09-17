package controller;

import entity.Booking;
import service.BookingService;

import java.util.NoSuchElementException;

public class BookingController {
    private final BookingService bookingService = new BookingService("C:\\Users\\Perviz Veliyev\\IdeaProjects\\booking_plane_tickets\\src\\database\\bookings.txt");

    public boolean makeBooking(Booking booking){
        return bookingService.makeBooking(booking);
    }

    public int getMaxId(){
        if(bookingService.getAllBookings().isEmpty()){
            return 0;
        }
        else{
            return bookingService.getAllBookings()
                    .stream()
                    .mapToInt(Booking::getId)
                    .max().orElseThrow(NoSuchElementException::new);
        }
    }
}
