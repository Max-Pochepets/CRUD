package crud.controller.security;

import crud.lib.Inject;
import crud.lib.ServiceImpl;
import crud.lib.exception.AuthenticationException;
import crud.model.Driver;
import crud.service.abstraction.DriverService;
import java.util.Optional;

@ServiceImpl
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private DriverService driverService;

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Optional<Driver> driverFromDB = driverService.findByLogin(login);
        if (driverFromDB.isPresent() && driverFromDB.get().getPassword().equals(password)) {
            return driverFromDB.get();
        }
        throw new AuthenticationException("Incorrect login or password.");
    }
}
