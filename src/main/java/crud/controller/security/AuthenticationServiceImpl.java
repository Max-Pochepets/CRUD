package crud.controller.security;

import crud.lib.Inject;
import crud.lib.ServiceImpl;
import crud.lib.exception.AuthenticationException;
import crud.model.Driver;
import crud.service.abstraction.DriverService;

@ServiceImpl
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    DriverService driverService;

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Driver driverFromDB = driverService.findByLogin(login).orElseThrow(() ->
                new AuthenticationException("Incorrect login or password."));
        if (driverFromDB.getPassword().equals(password)) {
            return driverFromDB;
        }
        throw new AuthenticationException("Incorrect login or password.");
    }
}
