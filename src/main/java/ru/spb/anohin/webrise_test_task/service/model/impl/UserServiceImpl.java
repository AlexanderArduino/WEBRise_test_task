package ru.spb.anohin.webrise_test_task.service.model.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.anohin.webrise_test_task.model.User;
import ru.spb.anohin.webrise_test_task.repository.model.UserRepository;
import ru.spb.anohin.webrise_test_task.service.model.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existUserByNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }
}
