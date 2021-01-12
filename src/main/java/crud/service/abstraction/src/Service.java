package crud.service.abstraction.src;

import java.util.List;

public interface Service<T> {
    T create(T element);

    T get(long id);

    List<T> getAll();

    T update(T element);

    boolean delete(long id);
}
