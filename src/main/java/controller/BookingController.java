package controller;

import entity.Booking;
import service.BookingService;

public class BookingController {
    private final BookingService bookingService = new BookingService();

    public boolean makeBooking(Booking booking){
        return bookingService.makeBooking(booking);
    }
}
