package ru.spb.anohin.webrise_test_task.service.dto;


import org.springframework.http.ResponseEntity;
import ru.spb.anohin.webrise_test_task.dto.request.UserDtoRequest;

public interface UserDtoService {

    ResponseEntity<Object> getUserById(Long id);

    ResponseEntity<Object> saveUser(UserDtoRequest userDtoRequest);

    ResponseEntity<Object> getAllUsers();

    ResponseEntity<Object> getAllUsersWithArchive(boolean isArchive);

    ResponseEntity<Object> updateUser(Long id, UserDtoRequest request);

    ResponseEntity<Object> deleteUser(Long id);
}
