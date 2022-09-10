package dao;

import entity.Booking;

import java.io.File;

public class DaoBookingFile extends DaoFile<Booking> {
    public DaoBookingFile(File file) {
        super(file);
    }
}
