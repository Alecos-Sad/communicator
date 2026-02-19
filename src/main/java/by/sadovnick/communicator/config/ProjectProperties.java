package by.sadovnick.communicator.config;

import by.sadovnick.communicator.enums.DetectionNetworkMode;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Data
@Component
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties("app-variables")
public class ProjectProperties {
    /**
     * Справочник сертификат -> clientUUID
     */
    @NotNull(message = "Необходимо заполнить справочник сертификатов")
    Map<String, String> certMap;
    /**
     * Режим сети
     */
    @NotNull(message = "Необходимо указать режим сети")
    DetectionNetworkMode detectionNetworkMode;
    /**
     * Временная маска для определения внутренней сети.
     * Присваивается, когда sowaEnabled=false
     */
    @NotNull(message = "Необходимо указать временную маску для определения внутренней сети.")
    String dn;
    /**
     * Временный флаг для состояния SOWA на стенде. (вкл/выкл)
     */
    @NotNull(message = "Необходимо указать состояние Sowa на стенде. (вкл-true/выкл-false)")
    boolean sowaEnabled;
}
