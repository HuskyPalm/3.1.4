package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;




@Component
public class TableInserter implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public TableInserter(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) {
        User user1 = new User("petr",
                "peetrov", (byte) 77, "asdf@gmail.com", "123");
        User user2 = new User("tom",
                "cruse", (byte)15, "cruse@gmail.com", "qwerty666");
        User user3 = new User("ivan",
                "ivanov", (byte)82, "ivashka@gmail.com", "qwes");
        Role user = new Role("ROLE_USER");
        Role admin = new Role("ROLE_ADMIN");

        roleService.saveRole(user);
        roleService.saveRole(admin);

        user1.addRole(user);
        user2.addRole(admin);
        user3.addRole(user);
        user3.addRole(admin);

        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);

    }
}