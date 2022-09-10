package dao;

import java.util.List;
import java.util.Optional;

public interface DAO<A extends Identifiable> {
    List<A> getAll();

    Optional<A> get(int id);

    boolean save(A a);

    void saveAll(List<A> data);

    boolean remove(int id);
}
