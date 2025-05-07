package ru.spb.anohin.webrise_test_task.dto.request;

/**
 * @param nickname
 * @param name
 * @param lastname
 * @param age
 */
public record UserDtoRequest(
        String nickname,
        String name,
        String lastname,
        int age
) {
}
