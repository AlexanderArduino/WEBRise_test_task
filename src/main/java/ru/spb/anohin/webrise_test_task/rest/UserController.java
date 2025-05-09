package ru.spb.anohin.webrise_test_task.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.spb.anohin.webrise_test_task.dto.request.SubscriptionDtoRequest;
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
        log.info("GET /api/users/{} called", id);
        return userDtoService.getUserById(id);
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers(
            @RequestParam(name = "isArchive", required = false, defaultValue = "false") boolean isArchive
    ) {
        log.info("GET /api/users called with parametr {}", isArchive);
        return userDtoService.getAllUsersWithArchive(isArchive);
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody UserDtoRequest request) {
        log.info("POST /api/users called");
        return userDtoService.saveUser(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") Long id,
                                             @RequestBody UserDtoRequest request) {
        log.info("PUT /api/users/{} called", id);
        return userDtoService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
        log.info("DELETE /api/users/{} called", id);
        return userDtoService.deleteUser(id);
    }

    @GetMapping("/{id}/subscriptions")
    public ResponseEntity<Object> getUserSubscriptionsByUserId(@PathVariable("id") Long id) {
        log.info("GET /api/users/{}/subscriptions called", id);
        return userDtoService.getUserSubscriptionsByUserId(id);
    }

    @PostMapping("/{id}/subscriptions")
    public ResponseEntity<Object> addSubscriptionAtUser(@PathVariable("id") Long userId,
                                                        @RequestBody SubscriptionDtoRequest request) {
        log.info("POST /api/users/{}/subscriptions called", userId);
        return userDtoService.addSubscriptionAtUser(userId, request);
    }

    //TODO доделать эндпойнт для удаления подписки
}
