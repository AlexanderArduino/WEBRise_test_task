package ru.spb.anohin.webrise_test_task.service.model;


import org.springframework.http.ResponseEntity;
import ru.spb.anohin.webrise_test_task.model.Subscription;
import ru.spb.anohin.webrise_test_task.model.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    User save(User user);

    boolean existUserByNickname(String nickname);

    Optional<User> findUserById(Long id);

    Optional<User> findUserByNickname(String nickname);

    boolean existUserById(Long id);

    void delete(Long id);}
