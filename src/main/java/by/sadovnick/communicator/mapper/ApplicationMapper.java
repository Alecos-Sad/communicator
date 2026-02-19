package by.sadovnick.communicator.mapper;

import by.sadovnick.communicator.dto.ApplicationRq;
import by.sadovnick.communicator.entity.ApplicationEntity;
import by.sadovnick.communicator.enums.DetectionNetworkMode;
import by.sadovnick.communicator.util.ParserUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.Instant;

@Mapper(componentModel = "spring",
        imports = {
                Instant.class,
                ParserUtil.class
        },
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ApplicationMapper {

    @Mapping(target = "createdAt", expression = "java(Instant.now())")
    @Mapping(target = "idpClientIds", expression = "java(ParserUtil.parseListToStringWithOutBrackets(applicationRq.getIdpClientIds()))")
    ApplicationEntity toEntity(
            ApplicationRq applicationRq,
            String clientUUID,
            DetectionNetworkMode detectionNetworkMode
    );
}
