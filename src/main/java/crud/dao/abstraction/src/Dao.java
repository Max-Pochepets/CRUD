package crud.dao.abstraction.src;

import java.util.Map;
import java.util.Optional;

public interface Dao<T> {
    T create(T element);

    Optional<T> get(Long id);

    Map<Long, T> getAll();

    T update(T element);

    boolean delete(Long id);
}
