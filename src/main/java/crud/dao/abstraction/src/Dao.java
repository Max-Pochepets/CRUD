package crud.dao.abstraction.src;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    T create(T element);

    Optional<T> get(long id);

    List<T> getAll();

    T update(T element);

    boolean delete(long id);
}
