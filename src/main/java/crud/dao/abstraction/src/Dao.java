package crud.dao.abstraction.src;

import java.util.List;
import java.util.Optional;

public interface Dao<T, I> {
    T create(T element);

    Optional<T> get(I id);

    List<T> getAll();

    T update(T element);

    boolean delete(I id);
}
