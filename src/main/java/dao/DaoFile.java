package dao;

import logging.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoFile<A extends Identifiable> implements DAO<A> {

        private final File file;

        public DaoFile(File file){
                this.file=file;
        }

        @Override
        public void saveAll(List<A> data) {
                try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file))) {
                        writer.writeObject(data);
                } catch (Exception e) {
                        e.printStackTrace();
                        Logger.error(e.getMessage());
                }
        }

        @Override
        public List<A> getAll() {
                try(ObjectInputStream reader=new ObjectInputStream(new FileInputStream(file))){
                        return(List<A>)reader.readObject();
                }catch(Exception e){
                        Logger.error(e.getMessage());
                        return new ArrayList<>();
                }
        }

        @Override
        public Optional<A> get(int id) {
                List<A> all= getAll();
                return all
                        .stream()
                        .filter(a -> a.getId()==id)
                        .findFirst();
        }

        @Override
        public boolean save(A a){
        List<A> all=getAll();
        if(a == null) return false;
        else if(all.contains(a)) {
                all.set(all.indexOf(a), a);
                saveAll(all);
                return true;
        }
        else {
                all.add(a);
                saveAll(all);
                return true;
             }
        }

        @Override
        public boolean remove(int id) {
                List<A> all=getAll();
                boolean result = all.removeIf(a -> a.getId()==id);
                saveAll(all);
                return result;
        }
}
