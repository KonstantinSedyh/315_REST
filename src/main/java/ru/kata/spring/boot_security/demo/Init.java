package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class Init {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public Init(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void postConstruct() {
        Role userRole = new Role(1, "ROLE_USER");
        Role adminRole = new Role(2, "ROLE_ADMIN");
        roleRepository.saveAll(List.of(userRole, adminRole));

        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setLastName("admin");
        adminUser.setAge(20);
        adminUser.setEmail("admin@mail.ru");
        adminUser.setPassword(passwordEncoder.encode("admin"));
        adminUser.addRole(adminRole);

        User user = new User();
        user.setUsername("user");
        user.setLastName("user");
        user.setAge(18);
        user.setEmail("user@mail.ru");
        user.setPassword(passwordEncoder.encode("user"));
        user.addRole(userRole);

        userRepository.save(adminUser);
        userRepository.save(user);
    }
}
