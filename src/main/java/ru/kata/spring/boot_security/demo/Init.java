package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class Init {

    private final RoleRepository roleRepository;
    private final UserService userService;

    @Autowired
    public Init(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
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
        adminUser.setPassword("admin");
        adminUser.addRole(adminRole);

        User user = new User();
        user.setUsername("user");
        user.setLastName("user");
        user.setAge(18);
        user.setEmail("user@mail.ru");
        user.setPassword("user");
        user.addRole(userRole);

        userService.addUser(adminUser);
        userService.addUser(user);
    }
}
