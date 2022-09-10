package controller;

import entity.Booking;
import service.BookingService;

public class BookingController {
    private final BookingService bookingService = new BookingService("C:\\Users\\Perviz Veliyev\\IdeaProjects\\booking_plane_tickets\\src\\database\\bookings.txt");

    public boolean makeBooking(Booking booking){
        return bookingService.makeBooking(booking);
    }
}
