package service;

import dao.DaoFlightFile;
import entity.Flight;
import logging.Logger;

import java.io.File;
import java.util.List;

public class FlightService {
    private final DaoFlightFile flightFile;

    public FlightService() {
        this.flightFile = new DaoFlightFile(new File("C:\\Users\\Perviz Veliyev\\IdeaProjects\\booking_plane_tickets\\src\\database\\flights.txt"));
    }

    public List<Flight> getAllFlights(){
        return flightFile.getAll();
    }

    public boolean save(Flight flight){
        boolean result = flightFile.save(flight);
        if(result) Logger.info("A flight saved or updated to the database.");
        return result;
    }

    public boolean remove(int id){
        boolean result = flightFile.remove(id);
        if(result) Logger.info("A flight removed.");
        return result;
    }

    public void saveAll(List<Flight> data){
        flightFile.saveAll(data);
        Logger.info("A list of flights saved to the database.");
    }
}
