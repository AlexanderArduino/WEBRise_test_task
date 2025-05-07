package ru.spb.anohin.webrise_test_task.service.dto.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.anohin.webrise_test_task.dto.request.UserDtoRequest;
import ru.spb.anohin.webrise_test_task.dto.response.UserDtoResponse;
import ru.spb.anohin.webrise_test_task.model.User;
import ru.spb.anohin.webrise_test_task.repository.dto.UserDtoRepository;
import ru.spb.anohin.webrise_test_task.service.dto.UserDtoService;
import ru.spb.anohin.webrise_test_task.service.model.UserService;

import java.util.Optional;

@Service
@Transactional
public class UserDtoServiceImpl implements UserDtoService {

    private static final Logger log = LoggerFactory.getLogger(UserDtoServiceImpl.class);
    private final UserDtoRepository userDtoRepository;
    private final UserService userService;

    public UserDtoServiceImpl(UserDtoRepository userDtoRepository,
                              UserService userService) {
        this.userDtoRepository = userDtoRepository;
        this.userService = userService;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> getUserById(Long id) {
        log.debug(getClass().getName() + " вызван");
        Optional<UserDtoResponse> opt = userDtoRepository.findUserById(id);
        if (opt.isPresent()) {
            return ResponseEntity.ok().body(opt.get());
        } else {
            return ResponseEntity.badRequest().build(); //TODO вставить текст сообщения
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> getAllUsers() {
        log.debug(getClass().getName() + " вызван");
        return ResponseEntity.ok(userDtoRepository.findAllUsers());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> getAllUsersWithArchive(boolean isArchive) {
            return ResponseEntity.ok(userDtoRepository.getAllUsersWithArchive(isArchive));
    }

    @Override
    public ResponseEntity<Object> saveUser(UserDtoRequest request) {
        log.debug(getClass().getName() + " вызван");
        if (!userService.existUserByNickname(request.nickname())) {
            User user = new User();
            user.setNickname(request.nickname());
            user.setName(request.name());
            user.setLastname(request.lastname());
            user.setAge(request.age());
            user.setIs_archive(false);
            userService.save(user);
            return ResponseEntity.ok(userDtoRepository.findUserByNickname(request.nickname()));
        } else {
            return ResponseEntity.badRequest().build(); //TODO need send message
        }
    }
}
