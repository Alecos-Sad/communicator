package by.sadovnick.communicator.entity;

import by.sadovnick.communicator.converter.DeviceTypeConverter;
import by.sadovnick.communicator.enums.DeviceType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

/**
 * Экземпляр приложения
 */
@Entity
@Table(name = "application_instance")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplicationInstanceEntity extends BaseEntity{
    /**
     * Связь с сущностью приложения
     */
    @ManyToOne
    @JoinColumn(name = "application_id")
    private ApplicationEntity applicationEntity;
    /**
     * Открытый ключ ключевой пары экземпляра приложения, сгенерированный SdkCerber
     */
    @Column(name = "public_key")
    private String publicKey;
    /**
     * Отпечаток устройства
     */
    @Column(name = "fingerprint")
    private String fingerprint;
    /**
     * Тип мобильного устройства
     */
    @Convert(converter = DeviceTypeConverter.class)
    @Column(name = "device_type")
    private DeviceType deviceType;
    /**
     * Время создания экземпляра приложения
     */
    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;
    /**
     * Время последнего использования приложения
     */
    @CreatedDate
    @Column(name = "last_active_at")
    private Instant lastActiveAt;
    /**
     * Признак блокировки приложения
     */
    @Column(name = "blocked")
    private boolean blocked;
}
