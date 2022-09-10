package service;

import dao.DaoBookingFile;
import entity.Booking;
import logging.Logger;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class BookingService {
    private final DaoBookingFile bookingFile;

    public BookingService() {
        this.bookingFile = new DaoBookingFile(new File("C:\\Users\\Perviz Veliyev\\IdeaProjects\\booking_plane_tickets\\src\\database\\bookings.txt"));
    }

    public List<Booking> getAllBookings(){
        return bookingFile.getAll();
    }

    public boolean makeBooking(Booking booking){
        boolean result = bookingFile.save(booking);
        if(result) Logger.info("A booking saved or updated to the database.");
        return result;
    }

    public Optional<Booking> get(int id){
        return bookingFile.get(id);
    }

    public boolean remove(int id){
        boolean result = bookingFile.remove(id);
        if(result) Logger.info("A booking removed from the database.");
        return result;
    }
}
