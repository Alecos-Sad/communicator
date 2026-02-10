package by.sadovnick.communicator.repository;

import by.sadovnick.communicator.entity.ApplicationEntity;
import by.sadovnick.communicator.enums.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, UUID> {

    Optional<ApplicationEntity> findApplicationEntityByClientUUIDAndBundleIdAndPlatformAndDeletedAtIsNotNull(
            String clientUUID,
            String bundleId,
            Platform platform
    );
}
