package controller;

import entity.Booking;
import entity.User;
import service.BookingService;
import service.UserService;

import java.util.List;
import java.util.Optional;

public class UserController {
    private final UserService userService = new UserService();
    private final BookingService bookingService = new BookingService();

    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    public boolean saveUser(User user){
        return userService.save(user);
    }

    public Optional<User> getUser(int id){
        return userService.get(id);
    }

    public boolean addBookingToTheUser(int userId, Booking booking){
        User user = getUser(userId).get();
        List<Booking> bookings = user.getBookings();
        bookings.add(booking);
        user.setBookings(bookings);
        return saveUser(user);
    }

    public String cancelBookingFromUser(int bookingId, int userId){
        User user = getUser(userId).get();
        List<Booking> bookings = user.getBookings();
        bookings.remove(bookingService.get(bookingId).get());
        user.setBookings(bookings);
        saveUser(user);
        if (bookingService.remove(bookingId)) return "Booking cancelled.";
        else return "No such booking found.";
    }
}
