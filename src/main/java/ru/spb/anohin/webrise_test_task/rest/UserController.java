package ru.spb.anohin.webrise_test_task.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spb.anohin.webrise_test_task.dto.request.SubscriptionDtoRequest;
import ru.spb.anohin.webrise_test_task.dto.request.UserDtoRequest;

public interface UserController {

    ResponseEntity<Object> getUserById(@PathVariable("id") Long id);

    ResponseEntity<Object> getAllUsers(@RequestParam(name = "isArchive", required = false, defaultValue = "false") boolean isArchive);

    ResponseEntity<Object> saveUser(@RequestBody UserDtoRequest request);

    ResponseEntity<Object> updateUser(@PathVariable("id") Long id,
                                      @RequestBody UserDtoRequest request);

    ResponseEntity<Object> deleteUser(@PathVariable("id") Long id);

    ResponseEntity<Object> getUserSubscriptionsByUserId(@PathVariable("id") Long id);

    ResponseEntity<Object> addSubscriptionAtUser(@PathVariable("id") Long userId,
                                                 @RequestBody SubscriptionDtoRequest request);

    ResponseEntity<Object> deleteSubscriptionAtUser(@PathVariable("id") Long userId,
                                                    @PathVariable("subId") Long subId);

    ResponseEntity<Object> getTop3UsersSubscriptions();
}
