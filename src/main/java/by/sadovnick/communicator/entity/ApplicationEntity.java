package by.sadovnick.communicator.entity;

import by.sadovnick.communicator.enums.DestinationAction;
import by.sadovnick.communicator.enums.DetectionNetworkMode;
import by.sadovnick.communicator.enums.Platform;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.List;

/**
 * Мобильное приложение
 */
@Entity
@Table(name = "application")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplicationEntity extends BaseEntity{
    /**
     * Идентификатор клиента, записывающего доступные url в БД.
     */
    @Column(name = "client_uuid", nullable = false)
    private String clientUUID;
    /**
     * Уникальный в разрезе платформы идентификатор приложения.
     */
    @Column(name = "bundle_id", nullable = false)
    private String bundleId;
    /**
     * Платформа приложения
     */
    @Column(name = "platform", nullable = false)
    @Enumerated(EnumType.STRING)
    private Platform platform;
    /**
     * Опубликованы ли доступные URL для приложения в SberMag
     */
    @Column(name = "published", nullable = false)
    private boolean published;
    /**
     * Тип подключения по умолчанию для незарегистрированных в таблице destination URL
     */
    @Column(name = "default_destination_action", nullable = false)
    @Enumerated(EnumType.STRING)
    private DestinationAction defaultDestinationAction;
    /**
     * Время создания приложения
     */
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
    /**
     * Время обновления записи приложения в бд
     */
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;
    /**
     * Время когда приложение было помечено на удаления
     */
    @Column(name = "deleted_at")
    private Instant deletedAt;
    /**
     * Способ определения сети
     */
    @Column(name = "detection_network_mode")
    @Enumerated(EnumType.STRING)
    private DetectionNetworkMode detectionNetworkMode;
    /**
     * ClientID системы, куда пытается аутентифицироваться пользователь из приложения
     */
    @Column(name = "idp_client_ids")
    private String idpClientIds;
    /**
     * Связь с экземплярами приложения
     */
    @OneToMany(
            mappedBy = "applicationEntity",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<ApplicationDestinationEntity> applicationDestinationEntityList;
}
