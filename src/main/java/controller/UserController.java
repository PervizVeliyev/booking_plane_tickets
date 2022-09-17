package controller;

import entity.Booking;
import entity.User;
import exception.UsernameExists;
import service.BookingService;
import service.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class UserController {
    private final UserService userService = new UserService("C:\\Users\\Perviz Veliyev\\IdeaProjects\\booking_plane_tickets\\src\\database\\users.txt");
    private final BookingService bookingService = new BookingService("C:\\Users\\Perviz Veliyev\\IdeaProjects\\booking_plane_tickets\\src\\database\\bookings.txt");

    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    public boolean saveUser(User user){
        return userService.save(user);
    }

    public Optional<User> getUser(int id){
        return userService.get(id);
    }

    public void addBookingToTheUser(int userId, Booking booking){
        User user = getUser(userId).orElseThrow();
        List<Booking> bookings = user.getBookings();
        bookings.add(booking);
        user.setBookings(bookings);
        saveUser(user);
    }

    public String cancelBookingFromUser(int bookingId, int userId){
        if(userService.get(userId)
                .orElseThrow()
                .getBookings()
                .stream()
                .map(Booking::getId)
                .toList()
                .contains(bookingId)) {
            User user = getUser(userId).orElseThrow();
            List<Booking> bookings = user.getBookings();
            bookings.remove(bookingService.get(bookingId).orElseThrow());
            user.setBookings(bookings);
            saveUser(user);
            bookingService.remove(bookingId);

            return "Booking cancelled.";
        }
        else return "No such booking found.";
    }

    public void userChecker(String username){
        Optional<User> user = userService.getAllUsers()
                .stream()
                .filter(user1 -> user1.getLogin().equalsIgnoreCase(username))
                .findFirst();

        if(user.isPresent()) throw new UsernameExists("Such a user exists", new RuntimeException());
    }

    public int getMaxId(){
        if(userService.getAllUsers().isEmpty()){
            return 0;
        }
        else{
            return userService.getAllUsers()
                    .stream()
                    .mapToInt(User::getId)
                    .max().orElseThrow(NoSuchElementException::new);
        }
    }

    public User getUserByLogin(String login){
        return userService.getAllUsers()
                .stream()
                .filter(user -> login.equalsIgnoreCase(user.getLogin()))
                .findFirst()
                .orElse(null);
    }
}
