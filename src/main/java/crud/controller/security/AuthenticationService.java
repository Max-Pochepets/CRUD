package crud.controller.security;

import crud.lib.exception.AuthenticationException;
import crud.model.Driver;

public interface AuthenticationService {
    Driver login(String login, String password) throws AuthenticationException;
}
