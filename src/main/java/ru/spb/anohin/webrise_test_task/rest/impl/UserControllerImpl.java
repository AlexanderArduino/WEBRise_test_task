package ru.spb.anohin.webrise_test_task.rest.impl;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spb.anohin.webrise_test_task.dto.request.SubscriptionDtoRequest;
import ru.spb.anohin.webrise_test_task.dto.request.UserDtoRequest;
import ru.spb.anohin.webrise_test_task.rest.UserController;
import ru.spb.anohin.webrise_test_task.service.dto.UserDtoService;

@RestController
@RequestMapping("/api/users")
public class UserControllerImpl implements UserController {

    private final UserDtoService userDtoService;

    public UserControllerImpl(UserDtoService userDtoService) {
        this.userDtoService = userDtoService;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") Long id) {
        return userDtoService.getUserById(id);
    }

    @Override
    @GetMapping
    public ResponseEntity<Object> getAllUsers(
            @RequestParam(name = "isArchive", required = false, defaultValue = "false") boolean isArchive
    ) {
        return userDtoService.getAllUsersWithArchive(isArchive);
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody UserDtoRequest request) {
        return userDtoService.saveUser(request);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") Long id,
                                             @RequestBody UserDtoRequest request) {
        return userDtoService.updateUser(id, request);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
        return userDtoService.deleteUser(id);
    }

    @Override
    @GetMapping("/{id}/subscriptions")
    public ResponseEntity<Object> getUserSubscriptionsByUserId(@PathVariable("id") Long id) {
        return userDtoService.getUserSubscriptionsByUserId(id);
    }

    @Override
    @PostMapping("/{id}/subscriptions")
    public ResponseEntity<Object> addSubscriptionAtUser(@PathVariable("id") Long userId,
                                                        @RequestBody SubscriptionDtoRequest request) {
        return userDtoService.addSubscriptionAtUser(userId, request);
    }

    @Override
    @DeleteMapping("/{id}/subscriptions/{subId}")
    public ResponseEntity<Object> deleteSubscriptionAtUser(@PathVariable("id") Long userId,
                                                           @PathVariable("subId") Long subId) {
        return userDtoService.deleteSubscriptionAtUser(userId, subId);
    }

    @Override
    @GetMapping("/subscriptions/top3")
    public ResponseEntity<Object> getTop3UsersSubscriptions() {
        return userDtoService.getTop3UsersSubscriptions();
    }
}
