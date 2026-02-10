package by.sadovnick.communicator.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class ErrorResponse {

    @Pattern(regexp = ".*")
    @Size(max = 255)
    private String code;

    @Pattern(regexp = ".*")
    @Size(max = 255)
    private String description;

    @Pattern(regexp = ".*")
    @Size(max = 255)
    private String message;

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
