package by.sadovnick.communicator.mapper;

import by.sadovnick.communicator.dto.ApplicationRq;
import by.sadovnick.communicator.entity.ApplicationEntity;
import by.sadovnick.communicator.enums.DestinationAction;
import by.sadovnick.communicator.enums.DetectionNetworkMode;
import by.sadovnick.communicator.enums.Platform;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ApplicationMapperTest {

    private final ApplicationMapper APPLICATION_MAPPER = Mappers.getMapper(ApplicationMapper.class);

    private static final String CLIENT_UUID_TEST = "CERT-11";

    @Test
    void toEntity() {

        ApplicationRq applicationRq = buildApplicationRq(
                List.of(
                        "CI001", "CI002", "CI003"
                )
        );

        ApplicationEntity entity = APPLICATION_MAPPER.toEntity(
                applicationRq,
                CLIENT_UUID_TEST,
                DetectionNetworkMode.AUTO);

        assertNull(entity.getId());
        assertEquals(CLIENT_UUID_TEST, entity.getClientUUID());
        assertEquals(applicationRq.getBundleId(), entity.getBundleId());
        assertEquals(applicationRq.getPlatform(), entity.getPlatform());
        assertFalse(entity.isPublished());
        assertEquals(applicationRq.getDefaultDestinationAction(), entity.getDefaultDestinationAction());
        assertNotNull(entity.getCreatedAt());
        assertNull(entity.getUpdatedAt());
        assertNull(entity.getDeletedAt());
        assertEquals(DetectionNetworkMode.AUTO, entity.getDetectionNetworkMode());
        assertEquals("CI001,CI002,CI003", entity.getIdpClientIds());
        assertNull(entity.getApplicationDestinationEntityList());
    }

    @Test
    void toEntityShouldSetNullIdpClientIdsWhenSourceListIsNull() {
        ApplicationRq applicationRq = buildApplicationRq(null);

        ApplicationEntity entity = APPLICATION_MAPPER.toEntity(
                applicationRq,
                CLIENT_UUID_TEST,
                DetectionNetworkMode.AUTO);

        assertNull(entity.getIdpClientIds());
    }

    @Test
    void toEntityShouldSetNullIdpClientIdsWhenSourceListIsEmpty() {
        ApplicationRq applicationRq = buildApplicationRq(List.of());

        ApplicationEntity entity = APPLICATION_MAPPER.toEntity(
                applicationRq,
                CLIENT_UUID_TEST,
                DetectionNetworkMode.AUTO);

        assertNull(entity.getIdpClientIds());
    }

    @Test
    void toEntityShouldTrimAndSkipBlankIdpClientIds() {
        ApplicationRq applicationRq = buildApplicationRq(
                List.of("  CI001  ", "", "   ", "CI002")
        );

        ApplicationEntity entity = APPLICATION_MAPPER.toEntity(
                applicationRq,
                CLIENT_UUID_TEST,
                DetectionNetworkMode.AUTO);

        assertEquals("CI001,CI002", entity.getIdpClientIds());
    }

    private ApplicationRq buildApplicationRq(List<String> idpClientIds) {
        ApplicationRq applicationRq = new ApplicationRq();
        applicationRq.setBundleId("ru.test.com");
        applicationRq.setPlatform(Platform.IOS);
        applicationRq.setDefaultDestinationAction(DestinationAction.PROXY);
        applicationRq.setIdpClientIds(idpClientIds);
        return applicationRq;
    }
}
