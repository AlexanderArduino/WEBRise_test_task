package ru.spb.anohin.webrise_test_task.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.spb.anohin.webrise_test_task.dto.request.UserDtoRequest;
import ru.spb.anohin.webrise_test_task.service.dto.UserDtoService;

import java.util.Arrays;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserDtoService userDtoService;

    public UserController(UserDtoService userDtoService) {
        this.userDtoService = userDtoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") Long id) {
        log.info("GET запрос на " + getClass() + getClass().getName());
        return userDtoService.getUserById(id);
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers(
            @RequestParam(name = "isArchive", required = false, defaultValue = "false") boolean isArchive
    ) {
        log.info("GET запрос на " + getClass() + getClass().getName() + Arrays.toString(getClass().getTypeParameters()));
            return userDtoService.getAllUsersWithArchive(isArchive);
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody UserDtoRequest request) {
        log.info("POST запрос на " + getClass() + getClass().getName());
        return userDtoService.saveUser(request);
    }


}
