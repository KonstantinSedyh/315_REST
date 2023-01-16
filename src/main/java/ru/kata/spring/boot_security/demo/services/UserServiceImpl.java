package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userRepository
                .findById(user.getId())
                .ifPresent(user1 -> {
                    user1.setUsername(user.getUsername());
                    user1.setLastName(user.getLastName());
                    user1.setAge(user.getAge());
                    user1.setEmail(user.getEmail());
                    user1.setRoles(user.getRoles());
                    if (user.getPassword().equals(user1.getPassword())) {
                        user1.setPassword(user.getPassword());
                    } else {
                        user1.setPassword(passwordEncoder.encode(user.getPassword()));
                    }
                    userRepository.save(user1);
                });
    }

    @Override
    public User findUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalStateException("No such user in database!"));
    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserByUserName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
