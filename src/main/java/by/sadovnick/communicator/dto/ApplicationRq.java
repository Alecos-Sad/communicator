package by.sadovnick.communicator.dto;

import by.sadovnick.communicator.enums.DestinationAction;
import by.sadovnick.communicator.enums.Platform;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "DTO запроса на создание создания нового приложения")
public class ApplicationRq {
    /**
     * Уникальный в разрезе платформы идентификатор приложения
     */
    @Pattern(regexp = ".*")
    @Size(max = 255)
    @NotBlank(message = "bundleId must not be null or empty")
    @Schema(
            description = "Unique application identifier within the platform",
            example = "ru.testver.sbermag"
    )
    private String bundleId;
    /**
     * Платформа приложения
     */
    @NotNull
    @Schema(
            description = "Application platform",
            allowableValues = {
                    "ANDROID",
                    "IOS"
            },
            example = "IOS"
    )
    private Platform platform;
    /**
     * Тип подключения приложения к SberMag
     */
    @NotNull
    @Schema(
            description = "Destination action",
            allowableValues = {
                    "PROXY",
                    "BLOCK",
                    "BYPASS",
                    "NOACTION"
            },
            example = "PROXY"
    )
    private DestinationAction defaultDestinationAction;
    /**
     * Разрешенные СУДИРом клиенты
     */
    @Size(max = 100)
    @Schema(
            description = "Clients allowed by IDP",
            example = "[\"CI001\",\"CI002\",\"CI003\"]"
    )
    private List<String> idpClientIds;
}