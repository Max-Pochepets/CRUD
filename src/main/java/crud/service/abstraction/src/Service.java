package crud.service.abstraction.src;

import java.util.Map;

public interface Service<T> {
    T create(T element);

    T get(Long id);

    Map<Long, T> getAll();

    T update(T element);

    boolean delete(Long id);
}
