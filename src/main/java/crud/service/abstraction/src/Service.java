package crud.service.abstraction.src;

import java.util.List;

public interface Service<T, I> {
    T create(T element);

    T get(I id);

    List<T> getAll();

    T update(T element);

    boolean delete(I id);
}
