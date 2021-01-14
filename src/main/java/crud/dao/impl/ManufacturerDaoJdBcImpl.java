package crud.dao.impl;

import crud.dao.abstraction.ManufacturerDao;
import crud.lib.DaoImpl;
import crud.model.Manufacturer;
import crud.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DaoImpl
public class ManufacturerDaoJdBcImpl implements ManufacturerDao {
    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        String query = "INSERT INTO manufacturers (name,country) VALUES (?,?)";

        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement
                    = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            setUpdate(preparedStatement, manufacturer).executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                manufacturer.setId(resultSet.getObject(1, Long.class));
            }
            preparedStatement.close();

            return manufacturer;
        } catch (SQLException throwables) {
            throw new RuntimeException("Couldn't establish connection to MySQL server. "
                    + throwables);
        }
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        String query = "SELECT id, name, country FROM manufacturers WHERE id = "
                + id + " AND deleted = FALSE";

        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            Manufacturer manufacturer = new Manufacturer("", "");
            while (resultSet.next()) {
                Long newId = resultSet.getObject("id", Long.class);
                manufacturer.setId(newId);
                String name = resultSet.getString("name");
                manufacturer.setName(name);
                String country = resultSet.getString("country");
                manufacturer.setCountry(country);
            }
            statement.close();

            return Optional.ofNullable(manufacturer);
        } catch (SQLException throwables) {
            throw new RuntimeException("Couldn't establish connection to MySQL server. "
                    + throwables);
        }
    }

    @Override
    public List<Manufacturer> getAll() {
        String query = "SELECT * FROM manufacturers WHERE deleted = FALSE";

        try (Connection connection = ConnectionUtil.getConnection()) {
            List<Manufacturer> manufacturers = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Long id = resultSet.getObject("id", Long.class);
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                Manufacturer manufacturer = new Manufacturer(name, country);
                manufacturer.setId(id);
                manufacturers.add(manufacturer);
            }
            statement.close();

            return manufacturers;
        } catch (SQLException throwables) {
            throw new RuntimeException("Couldn't establish connection to MySQL server. "
                    + throwables);
        }
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        String query = "UPDATE manufacturers SET name = ?, country = ?"
                + " WHERE id = ? AND deleted = FALSE";
        String getNewQuery = "SELECT * FROM manufacturers WHERE id = " + manufacturer.getId();

        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement
                    = setUpdate(connection.prepareStatement(query), manufacturer);
            preparedStatement.setLong(3, manufacturer.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getNewQuery);
            Manufacturer manufacturerNew = null;
            if (resultSet.next()) {
                manufacturerNew
                        = new Manufacturer(resultSet.getString("name"),
                                resultSet.getString("country"));
                manufacturerNew.setId(resultSet.getObject("id", Long.class));
            }
            if (manufacturerNew == null) {
                throw new RuntimeException("Couldn't get new manufacturer");
            }
            statement.close();

            return manufacturerNew;
        } catch (SQLException throwables) {
            throw new RuntimeException("Couldn't establish connection to MySQL server. "
                    + throwables);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE manufacturers SET deleted = TRUE WHERE id = ?";

        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new RuntimeException("Couldn't establish connection to MySQL server. "
                    + throwables);
        }
    }

    private static PreparedStatement setUpdate(PreparedStatement statement,
                                               Manufacturer manufacturer) throws SQLException {
        statement.setString(1, manufacturer.getName());
        statement.setString(2, manufacturer.getCountry());
        return statement;
    }
}
