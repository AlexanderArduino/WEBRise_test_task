package ru.spb.anohin.webrise_test_task.service.model;


import org.springframework.http.ResponseEntity;
import ru.spb.anohin.webrise_test_task.model.User;

public interface UserService {

    User save(User user);

    boolean existUserByNickname(String nickname);
}
