package by.sadovnick.communicator.mapper;

import by.sadovnick.communicator.dto.ApplicationRq;
import by.sadovnick.communicator.entity.ApplicationEntity;
import by.sadovnick.communicator.enums.DetectionNetworkMode;
import by.sadovnick.communicator.util.ParserUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;

@Mapper(componentModel = "spring",
        imports = {
                Instant.class,
                ParserUtil.class
        })
public interface ApplicationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "published", ignore = true)
    @Mapping(target = "applicationDestinationEntityList", ignore = true)
    @Mapping(target = "createdAt", expression = "java(Instant.now())")
    @Mapping(target = "idpClientIds", expression = "java(ParserUtil.parseListToStringWithOutBrackets(applicationRq.getIdpClientIds()))")
    ApplicationEntity toApplicationEntity(
            ApplicationRq applicationRq,
            String clientUUID,
            DetectionNetworkMode detectionNetworkMode
    );
}
