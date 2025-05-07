package ru.spb.anohin.webrise_test_task.service.model;


import ru.spb.anohin.webrise_test_task.model.Subscription;

import java.util.Optional;

public interface SubscriptionService {

    Optional<Subscription> findSubscriptionById(Long id);
}
