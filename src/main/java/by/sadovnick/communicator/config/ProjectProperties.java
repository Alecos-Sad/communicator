package by.sadovnick.communicator.config;

import by.sadovnick.communicator.enums.DetectionNetworkMode;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Data
@Component
@Validated
@ConfigurationProperties("app-variables")
public class ProjectProperties {

    /**
     * Справочник сертификат -> clientUUID
     */
    @NotNull(message = "Необходимо заполнить справочник сертификатов")
    private Map<String, String> certMap;

    /**
     * Режим сети
     */
    @NotNull(message = "Необходимо указать режим сети")
    private DetectionNetworkMode detectionNetworkMode;
}
