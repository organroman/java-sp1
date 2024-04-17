package dao;

import entity.AbstractEntity;
import entity.Flight;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface Dao<T extends AbstractEntity> {
    void create(T y);

    List<T> getAll();

    default Optional<T> getById(int id) {
        return getAll().stream().filter(item -> item.getId() == id).findFirst();
    }

    boolean deleteEntity(int id);

    boolean updateEntity(T y);

    void loadDataBase() throws IOException, ClassNotFoundException;

    void saveDataBase() throws IOException;
}
