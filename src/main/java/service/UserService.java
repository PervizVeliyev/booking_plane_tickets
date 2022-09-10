package service;

import dao.DaoUserFile;
import entity.User;
import logging.Logger;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final DaoUserFile userFile;

    public UserService(String pathname) {
        this.userFile = new DaoUserFile(new File(pathname));
    }

    public List<User> getAllUsers(){
        return userFile.getAll();
    }

    public boolean save(User user){
        boolean result = userFile.save(user);
        if(result) Logger.info("A user saved to the database.");
        return result;
    }

    public Optional<User> get(int id){
        return userFile.get(id);
    }
}
