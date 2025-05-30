package ru.spb.anohin.webrise_test_task.service.dto;


import org.springframework.http.ResponseEntity;
import ru.spb.anohin.webrise_test_task.dto.request.SubscriptionDtoRequest;
import ru.spb.anohin.webrise_test_task.dto.request.UserDtoRequest;

public interface UserDtoService {

    ResponseEntity<Object> getUserById(Long id);

    ResponseEntity<Object> saveUser(UserDtoRequest userDtoRequest);

    ResponseEntity<Object> getAllUsersWithArchive(boolean isArchive);

    ResponseEntity<Object> updateUser(Long id, UserDtoRequest request);

    ResponseEntity<Object> deleteUser(Long id);

    ResponseEntity<Object> getUserSubscriptionsByUserId(Long id);

    ResponseEntity<Object> addSubscriptionAtUser(Long userId, SubscriptionDtoRequest request);

    ResponseEntity<Object> deleteSubscriptionAtUser(Long userId, Long subId);

    ResponseEntity<Object> getTop3UsersSubscriptions();

}
