package by.sadovnick.communicator.dto;

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
    private UUID id;
}


