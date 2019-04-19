package model.interfaces;

import model.entity.Model;

public interface CustomerI<T extends Model> {

    T create(T t);

    T update(T t);

    T read(Long id);

    void delete(T t);

    void close();
}
