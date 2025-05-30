package ru.spb.anohin.webrise_test_task.service.dto.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.anohin.webrise_test_task.dto.request.SubscriptionDtoRequest;
import ru.spb.anohin.webrise_test_task.dto.request.UserDtoRequest;
import ru.spb.anohin.webrise_test_task.dto.response.ErrorDtoResponse;
import ru.spb.anohin.webrise_test_task.dto.response.UserDtoResponse;
import ru.spb.anohin.webrise_test_task.model.Subscription;
import ru.spb.anohin.webrise_test_task.model.User;
import ru.spb.anohin.webrise_test_task.repository.dto.UserDtoRepository;
import ru.spb.anohin.webrise_test_task.service.dto.UserDtoService;
import ru.spb.anohin.webrise_test_task.service.model.SubscriptionService;
import ru.spb.anohin.webrise_test_task.service.model.UserService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserDtoServiceImpl implements UserDtoService {

    private final UserDtoRepository userDtoRepository;
    private final UserService userService;
    private final SubscriptionService subscriptionService;

    public UserDtoServiceImpl(UserDtoRepository userDtoRepository,
                              UserService userService, SubscriptionService subscriptionService) {
        this.userDtoRepository = userDtoRepository;
        this.userService = userService;
        this.subscriptionService = subscriptionService;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> getUserById(Long id) {
        Optional<UserDtoResponse> opt = userDtoRepository.findUserById(id);
        if (opt.isPresent()) {
            return ResponseEntity.ok().body(opt.get());
        } else {
            return ResponseEntity.badRequest().body(new ErrorDtoResponse(
                    400,
                    "Пользователь с указанным ID не найден",
                    LocalDateTime.now()));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> getAllUsersWithArchive(boolean isArchive) {
        return ResponseEntity.ok(userDtoRepository.getAllUsersWithArchive(isArchive));
    }

    @Override
    public ResponseEntity<Object> saveUser(UserDtoRequest request) {
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
            return ResponseEntity.badRequest().body(new ErrorDtoResponse(
                    400,
                    "Данный nickname уже занят. Используйте другой",
                    LocalDateTime.now()));
        }
    }

    @Override
    public ResponseEntity<Object> updateUser(Long id, UserDtoRequest request) {
        Optional<User> opt = userService.findUserById(id);
        if (opt.isPresent()) {
            User user = opt.get();
            user.setName(request.name());
            user.setLastname(request.lastname());
            user.setAge(request.age());
            return ResponseEntity.ok(userService.save(user));
        } else {
            return ResponseEntity.badRequest().body(new ErrorDtoResponse(
                    400,
                    "Пользователь с указанным ID не найден",
                    LocalDateTime.now()));
        }
    }

    @Override
    public ResponseEntity<Object> deleteUser(Long id) {
        if (userService.existUserById(id)) {
            userService.delete(id);
            return ResponseEntity.ok().body(new ErrorDtoResponse(
                    200,
                    "Пользователь успешно удален",
                    LocalDateTime.now()));
        }
        return ResponseEntity.badRequest().body(new ErrorDtoResponse(
                400,
                "Пользователь с указанным ID не найден",
                LocalDateTime.now()));
    }

    @Override
    public ResponseEntity<Object> getUserSubscriptionsByUserId(Long id) {
        return ResponseEntity.ok(userDtoRepository.findUserSubscriptionsByUserId(id));
    }

    @Override
    public ResponseEntity<Object> addSubscriptionAtUser(Long userId, SubscriptionDtoRequest request) {
        Optional<User> opt = userService.findUserById(userId);
        if (opt.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorDtoResponse(
                    400,
                    "Не верно указан ID пользователя",
                    LocalDateTime.now()
            ));
        }
        Optional<Subscription> subOpt = subscriptionService.findSubscriptionById(request.id());
        if (subOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorDtoResponse(
                    400,
                    "Не верно указан ID подписки",
                    LocalDateTime.now()
            ));
        }
        User user = opt.get();
        Set<Subscription> subSet = user.getSubscriptions();
        subSet.add(subOpt.get());
        user.setSubscriptions(subSet);
        userService.save(user);
        return ResponseEntity.ok(userDtoRepository.findUserSubscriptionsByUserId(userId));
    }

    @Override
    public ResponseEntity<Object> deleteSubscriptionAtUser(Long userId, Long subId) {
        Optional<User> opt = userService.findUserById(userId);
        if (opt.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorDtoResponse(
                            400,
                            "Пользователь не найден",
                            LocalDateTime.now()
                    )
            );
        }
        userService.deleteSubscriptionFromUser(userId, subId);
        return ResponseEntity.ok(userDtoRepository.findUserById(userId));
    }

    @Override
    public ResponseEntity<Object> getTop3UsersSubscriptions() {
        return ResponseEntity.ok(userDtoRepository.getTop3UsersSubscriptions());
    }
}
