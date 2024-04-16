package dao;

import entity.AbstractEntity;

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


    List<T> loadDataBase() throws IOException, ClassNotFoundException;

    void saveDataBase(List<T> object) throws IOException;
}
