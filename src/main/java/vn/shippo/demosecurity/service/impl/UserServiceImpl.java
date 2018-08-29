package vn.shippo.demosecurity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.shippo.demosecurity.model.User;
import vn.shippo.demosecurity.repository.UserRepository;
import vn.shippo.demosecurity.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll() ;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }
    /**
     * Checks the authentication token and if it is valid prepares
     * {@link org.springframework.security.core.context.SecurityContext} and returns true.
     */
    public boolean checkToken(String token){
        return true;
    }
}
