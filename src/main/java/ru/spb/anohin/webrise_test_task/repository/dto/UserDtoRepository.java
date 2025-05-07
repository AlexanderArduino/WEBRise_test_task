package ru.spb.anohin.webrise_test_task.repository.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import ru.spb.anohin.webrise_test_task.dto.response.UserDtoResponse;
import ru.spb.anohin.webrise_test_task.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDtoRepository extends JpaRepository<User, Long> {

    @Query(value = """
            SELECT new ru.spb.anohin.webrise_test_task.dto.response.UserDtoResponse(
            u.id, u.nickname, u.name, u.lastname, u.age, u.is_archive
            )
            FROM User u
            WHERE u.id = :id
            """)
    Optional<UserDtoResponse> findUserById(@Param("id") Long id);

    @Query(value = """
            SELECT new ru.spb.anohin.webrise_test_task.dto.response.UserDtoResponse(
            u.id, u.nickname, u.name, u.lastname, u.age, u.is_archive
            )
            FROM User u
            WHERE u.nickname = :nickname
            """)
    Optional<UserDtoResponse> findUserByNickname(@Param("nickname") String nickname);

    @Query(value = """
            SELECT new ru.spb.anohin.webrise_test_task.dto.response.UserDtoResponse(
            u.id, u.nickname, u.name, u.lastname, u.age, u.is_archive
            )
            FROM User u
            """)
    List<UserDtoResponse> findAllUsers();

    @Query(value = """
            SELECT new ru.spb.anohin.webrise_test_task.dto.response.UserDtoResponse(
            u.id, u.nickname, u.name, u.lastname, u.age, u.is_archive
            )
            FROM User u
            WHERE u.is_archive = :archive
            """)
    List<UserDtoResponse> getAllUsersWithArchive(@Param("archive") boolean archive);
}
