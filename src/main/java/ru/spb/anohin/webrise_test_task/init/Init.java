package ru.spb.anohin.webrise_test_task.init;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.anohin.webrise_test_task.model.Subscription;
import ru.spb.anohin.webrise_test_task.model.User;
import ru.spb.anohin.webrise_test_task.repository.model.SubscriptionRepository;
import ru.spb.anohin.webrise_test_task.repository.model.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
//@Profile("dev")
public class Init {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public Init(SubscriptionRepository subscriptionRepository, UserRepository userRepository, UserRepository userRepository1) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository1;
    }

    @PostConstruct
    @Transactional
    void init() {
        Subscription s1 = new Subscription("Netflix");
        Subscription s2 = new Subscription("YouTube Premium");
        Subscription s3 = new Subscription("VK Музыка");
        Subscription s4 = new Subscription("Яндекс.Плюс");
        Subscription s5 = new Subscription("Twitch");
        subscriptionRepository.save(s1);
        subscriptionRepository.save(s2);
        subscriptionRepository.save(s3);
        subscriptionRepository.save(s4);
        subscriptionRepository.save(s5);

        User user1 = new User("user1", "user1", "user1", 18);
        Set<Subscription> userSet1 = new HashSet<>();
        userSet1.add(s1);
        userSet1.add(s2);
        userSet1.add(s3);
        user1.setSubscriptions(userSet1);
        userRepository.save(user1);

        User user2 = new User("user2", "user2", "user2", 18);
        Set<Subscription> userSet2 = new HashSet<>();
        userSet2.add(s1);
        userSet2.add(s3);
        userSet2.add(s4);
        userSet2.add(s5);
        user2.setSubscriptions(userSet2);
        userRepository.save(user2);

        User user3 = new User("user3", "user3", "user3", 18);
        Set<Subscription> userSet3 = new HashSet<>();
        userSet3.add(s1);
        userSet3.add(s4);
        userSet3.add(s5);
        user3.setSubscriptions(userSet3);
        userRepository.save(user3);

        User user4 = new User("user4", "user4", "user4", 18);
        Set<Subscription> userSet4 = new HashSet<>();
        userSet4.add(s1);
        userSet4.add(s4);
        userSet4.add(s5);
        user4.setSubscriptions(userSet4);
        userRepository.save(user4);

        User user5 = new User("user5", "user5", "user5", 18);
        Set<Subscription> userSet5 = new HashSet<>();
        userSet5.add(s1);
        userSet5.add(s3);
        userSet5.add(s5);
        user5.setSubscriptions(userSet5);
        userRepository.save(user5);

        User user6 = new User("user6", "user6", "user6", 18);
        Set<Subscription> userSet6 = new HashSet<>();
        userSet6.add(s1);
        userSet6.add(s2);
        userSet6.add(s3);
        userSet6.add(s5);
        user6.setSubscriptions(userSet6);
        userRepository.save(user6);

        //1: 6 раз
        //2: 2 раза
        //3: 4 раза
        //4: 3 раза
        //5: 5 раза
    }
}
