package ru.spb.anohin.webrise_test_task.service.model.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.anohin.webrise_test_task.model.Subscription;
import ru.spb.anohin.webrise_test_task.model.User;
import ru.spb.anohin.webrise_test_task.repository.model.UserRepository;
import ru.spb.anohin.webrise_test_task.service.model.SubscriptionService;
import ru.spb.anohin.webrise_test_task.service.model.UserService;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SubscriptionService subscriptionService;

    public UserServiceImpl(UserRepository userRepository, SubscriptionService subscriptionService) {
        this.userRepository = userRepository;
        this.subscriptionService = subscriptionService;
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

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existUserById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).get(); //TODO
        user.setIs_archive(true);
        userRepository.save(user);
    }

    @Override
    public void deleteSubscriptionFromUser(Long userId, Long subId) {
        User user = userRepository.findById(userId).get();
        Subscription subscription = subscriptionService.findSubscriptionById(subId).get(); //TODO
        Set<Subscription> subSet = user.getSubscriptions();
        subSet.remove(subscription);
        user.setSubscriptions(subSet);
        userRepository.save(user);
    }
}
