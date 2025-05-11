package ru.spb.anohin.webrise_test_task.dto.response;

import java.time.LocalDateTime;

/**
 *
 * @param code
 * @param message
 * @param timestamp
 */
public record ErrorDtoResponse(
        int code,
        String message,
        LocalDateTime timestamp
) {
}
