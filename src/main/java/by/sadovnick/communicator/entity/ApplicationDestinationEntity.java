package by.sadovnick.communicator.entity;

import by.sadovnick.communicator.enums.DestinationAction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

//TODO аннотацию ломбока с гетером сетером и всеми конструкторами
/**
 * Таблица связи приложения и его разрешенными URL, а также типа подключения к конкретному URL
 */
@Entity
@Table(name = "application_destination")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplicationDestinationEntity extends BaseEntity{
    /**
     * Тип подключения к URL в конкретном приложении.
     */
    @Column(name = "action")
    @Enumerated(EnumType.STRING)
    private DestinationAction action;
    /**
     * Разрешенный URL
     */
    @ManyToOne
    @JoinColumn(name = "destination_id")
    private DestinationEntity destinationEntity;
    /**
     * Само мобильное приложение
     */
    @ManyToOne
    @JoinColumn(name = "application_id")
    private ApplicationEntity applicationEntity;
    /**
     * Ранг связи (1 - наивысший, далее по возрастанию ценность ранга убывает)
     */
    @Column(name = "rank")
    private Integer rank = 1;
    /**
     * Время создания сущности
     */
    @CreatedDate
    @Column(name = "created_at")
    private Instant created_at;
    /**
     * Время изменения сущности
     */
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updated_at;
}
