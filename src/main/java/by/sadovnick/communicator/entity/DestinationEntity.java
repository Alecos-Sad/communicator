package by.sadovnick.communicator.entity;

import by.sadovnick.communicator.enums.DestinationAction;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.List;

/**
 * URL по которому разрешено переходить приложению и способ (action).
 */
@Entity
@Table(name = "destination")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DestinationEntity extends BaseEntity{
    /**
     * Идентификатор клиента, записывающего доступные URL в БД
     */
    @Column(name = "client_uuid", nullable = false)
    private String clientUUID;
    /**
     * Домен/маска домена (например, *sigma.sbrf.ru)
     */
    @Column(name = "address", nullable = false)
    private String address;
    /**
     * Тип подключения
     */
    @Column(name = "action", nullable = false)
    @Enumerated(EnumType.STRING)
    private DestinationAction action = DestinationAction.NOACTION;
    /**
     * Время создания сущности
     */
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant created_at;
    /**
     * Связь с разрешенными URL приложения
     */
    @OneToMany(
            mappedBy = "destinationEntity",
            fetch = FetchType.LAZY
    )
    private List<ApplicationDestinationEntity> applicationDestinationList;
}
