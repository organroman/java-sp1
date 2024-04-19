package dao;

import java.io.IOException;
import java.util.List;

public interface Dao<T> {
    void create(T y);

    List<T> getAll();
    T getById(int id);

    boolean deleteEntity(int id);

    boolean updateEntity(T y);

    void loadDataBase() throws IOException, ClassNotFoundException;

    void saveDataBase() throws IOException;
}
