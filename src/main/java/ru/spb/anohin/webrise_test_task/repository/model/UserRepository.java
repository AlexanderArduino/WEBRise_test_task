package ru.spb.anohin.webrise_test_task.repository.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.spb.anohin.webrise_test_task.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByNickname(String nickname);

    Optional<User> findByNickname(String nickname);

}
