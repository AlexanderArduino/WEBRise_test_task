package ru.spb.anohin.webrise_test_task.dto.response;

/**
 * @param id
 * @param nickname
 * @param name
 * @param lastname
 * @param age
 * @param is_archive
 */
public record UserDtoResponse(
        Long id,
        String nickname,
        String name,
        String lastname,
        int age,
        boolean is_archive
) {
}
