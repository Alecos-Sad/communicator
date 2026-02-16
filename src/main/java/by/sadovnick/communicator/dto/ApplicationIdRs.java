package by.sadovnick.communicator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO для ответа на запрос создания приложения
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationIdRs {
    /**
     * ID созданного приложения
     */
    @Schema(
            description = "ID of the created application",
            example = "525a224b-c58e-4be0-9051-3074e72d7184"
    )
    private UUID id;
}


