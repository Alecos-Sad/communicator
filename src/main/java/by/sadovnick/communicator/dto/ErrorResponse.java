package by.sadovnick.communicator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class ErrorResponse {
    /**
     * Код ошибки
     */
    @Pattern(regexp = ".*")
    @Size(max = 255)
    @Schema(
            description = "Error code",
            example = "SM-COMM-1"
    )
    private String code;
    /**
     * Описание ошибки
     */
    @Pattern(regexp = ".*")
    @Size(max = 255)
    @Schema(
            description = "Error description",
            example = "Data not found"
    )
    private String description;
    /**
     * Сообщение тела ошибки
     */
    @Pattern(regexp = ".*")
    @Size(max = 255)
    @Schema(
            description = "Error message",
            example = "Обратитесь в поддержку."
    )
    private String message;
    /**
     * Трассировка запроса
     */
    @Pattern(regexp = ".*")
    @Size(max = 255)
    @Schema(
            description = "Request trace id",
            example = "525a224b-c58e-4be0-9051-3074e72d7184"
    )
    private String traceId;
    /**
     * Время возникновения ошибки.
     */
    @Schema(
            description = "Time when an error was happened",
            example = "2025-12-27T04:56:09.574067068Z"
    )
    private Instant timeStamp = Instant.now();

    public ErrorResponse(String code, String description, String message, String traceId) {
        this.code = code;
        this.description = description;
        this.message = message;
        this.traceId = traceId;
    }
}
