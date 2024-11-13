package ru.kata.spring.boot_security.demo.Init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DatabaseInitialization {

    final UserRepository userRepository;

    final RoleRepository roleRepository;

    final PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseInitialization(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        roleRepository.save(new Role("ROLE_ADMIN"));
        roleRepository.save(new Role("ROLE_USER"));
        Set<Role> collect = roleRepository.findById(1).stream().collect(Collectors.toSet());
        userRepository.save(new User("admin", "admin", (byte) 35,
                "admin@mail.ru", passwordEncoder.encode("100"), collect));
    }
}
