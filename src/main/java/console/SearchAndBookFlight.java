package console;

import controller.BookingController;
import controller.FlightController;
import controller.UserController;
import entity.Booking;
import entity.Flight;
import entity.Passenger;
import entity.User;
import exception.TicketsOverCapacity;
import logging.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class SearchAndBookFlight {
    public static void run(Console console, User loggedIn){
        while(true) {
            UserController userController = new UserController();
            FlightController flightController = new FlightController();
            BookingController bookingController = new BookingController();
            try {
                console.print("Please, write the destination city (e.g Madrid):");
                String destination = console.nextLine();

                console.print("Please, write the origin city (e.g Baku):");
                String origin = console.nextLine();

                console.print("Please, write the date of flight in a format \"dd/MM/yyyy\":");
                String date = console.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate localDate = LocalDate.parse(date, formatter);

                console.print("Please, write the number of people:");
                int number = Integer.parseInt(console.nextLine());

                List<Flight> flights = flightController.getAllFlights()
                        .stream()
                        .filter(flight -> flight.getAirportTo().toString().equalsIgnoreCase(destination)
                                && flight.getAirportFrom().toString().equalsIgnoreCase(origin)
                                && flight.getTime().toLocalDate().isEqual(localDate)
                                && flight.getCapacity() > number)
                        .toList();

                if (flights.isEmpty()) console.printLine("No such flights found.");
                else flights.forEach(flight -> console.printLine(String.valueOf(flight)));

                console.print("Please, write Airline code in order to book a flight (e.g AA325) or write 'o' to exit:");
                String choice = console.nextLine();
                if (choice.equalsIgnoreCase("o")) break;

                int i = 1;
                List<Passenger> passengers = new ArrayList<>();

                while (i <= number) {
                    console.print("Please, write the name of passenger " + i + ":");
                    String name = console.nextLine();
                    console.print("Please, write the surname of passenger " + i + ":");
                    String surname = console.nextLine();
                    Passenger passenger = new Passenger(name, surname);
                    passengers.add(passenger);
                    i++;
                }

                if(flightController.get(choice).orElseThrow().getCapacity() < number)
                    throw new TicketsOverCapacity("There is not enough capacity for this flight",
                            new RuntimeException());

                flightController.decreaseCapacity(choice, number);
                Booking booking = new Booking(bookingController.getMaxId() + 1, flightController.get(choice).orElseThrow(), passengers);
                bookingController.makeBooking(booking);
                userController.addBookingToTheUser(loggedIn.getId(), booking);
                console.printLine("Booked.");
                break;
            } catch (DateTimeParseException e){
                    console.printLine("Please, provide date in a format of \"dd/MM/yyyy\".");
                    Logger.error(e.getMessage());
            } catch (NumberFormatException e){
                console.printLine("Please, provide a number for count of tickets.");
                Logger.error(e.getMessage());
            } catch (TicketsOverCapacity e){
                console.printLine("Not enough capacity for this flight");
                Logger.error(e.getMessage());
            } catch(NoSuchElementException e){
                console.printLine("No such flight found.");
                Logger.error(e.getMessage());
            }
        }
    }
}
