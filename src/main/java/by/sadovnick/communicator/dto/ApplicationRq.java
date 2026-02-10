package by.sadovnick.communicator.dto;

import by.sadovnick.communicator.enums.DestinationAction;
import by.sadovnick.communicator.enums.Platform;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRq {
    /**
     * Уникальный в разрезе платформы идентификатор приложения
     */
    @Pattern(regexp = ".*")
    @Size(max = 255)
    @NotBlank(message = "bundleId must not be null or empty")
    private String bundleId;
    /**
     * Платформа приложения
     */
    @NotNull
    private Platform platform;
    /**
     * Тип подключения приложения к SberMag
     */
    @NotNull
    private DestinationAction defaultDestinationAction;
    /**
     * Разрешенные СУДИРом клиенты
     */
    @Size(max = 100)
    private List<String> idpClientIds;
}
