package vn.shippo.demosecurity.service;

import vn.shippo.demosecurity.model.User;

import java.util.Optional;

public interface UserService {

    Iterable<User> findAll();

    Optional<User> findById(Integer id);

    /**
     * Checks the authentication token and if it is valid prepares
     * {@link org.springframework.security.core.context.SecurityContext} and returns true.
     */
    boolean checkToken(String token);
}
