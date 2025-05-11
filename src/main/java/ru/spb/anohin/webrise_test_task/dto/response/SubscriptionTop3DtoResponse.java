package ru.spb.anohin.webrise_test_task.dto.response;

/**
 * @param id
 * @param name
 * @param count
 */
public record SubscriptionTop3DtoResponse(
        Long id,
        String name,
        Long count
) {
}
