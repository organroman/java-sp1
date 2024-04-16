package dao;

import entity.AbstractEntity;

import java.io.IOException;
import java.util.List;

public interface Dao<T extends AbstractEntity>  {
    void create(T y);
    List<T> getAll();

    T getById(int id);

    boolean deleteEntity(int id);

    boolean updateEntity(T y);


    List<T> loadDataBase();

    void saveDataBase(List<T> object) throws IOException;
}
