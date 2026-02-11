package by.sadovnick.communicator.dto;

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
    private String code;
    /**
     * Описание ошибки
     */
    @Pattern(regexp = ".*")
    @Size(max = 255)
    private String description;
    /**
     * Сообщение тела ошибки
     */
    @Pattern(regexp = ".*")
    @Size(max = 255)
    private String message;
    /**
     * Трассировка ошибки
     */
    @Pattern(regexp = ".*")
    @Size(max = 255)
    private String traceId;

    private Instant timeStamp = Instant.now();

    public ErrorResponse(String code, String description, String message, String traceId) {
        this.code = code;
        this.description = description;
        this.message = message;
        this.traceId = traceId;
    }
}
