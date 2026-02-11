package by.sadovnick.communicator.service.v1.impl;

import by.sadovnick.communicator.config.ProjectProperties;
import by.sadovnick.communicator.dto.ApplicationIdRs;
import by.sadovnick.communicator.dto.ApplicationRq;
import by.sadovnick.communicator.dto.ErrorResponse;
import by.sadovnick.communicator.entity.ApplicationEntity;
import by.sadovnick.communicator.exception.BusinessLogicException;
import by.sadovnick.communicator.mapper.ApplicationMapper;
import by.sadovnick.communicator.repository.ApplicationRepository;
import by.sadovnick.communicator.service.CommonService;
import by.sadovnick.communicator.service.v1.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import static by.sadovnick.communicator.enums.ErrorStatus.APPLICATION_WAS_DELETED_201;
import static by.sadovnick.communicator.enums.JsonLogField.*;
import static by.sadovnick.communicator.util.RequestUtil.*;
import static java.lang.String.format;
import static net.logstash.logback.argument.StructuredArguments.kv;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final CommonService commonService;
    private final ProjectProperties projectProperties;
    private final ApplicationMapper applicationMapper;

    @Override
    public ApplicationIdRs create(ApplicationRq applicationRq, String clientDN) {
        //TODO две эти операции по получению clientUUID сделать в другом сервисе
        String cn = commonService.parseCnFromDnAdminAPI(clientDN);
        log.debug("CREATE APP FOR CN",
                kv(CLIENT_DN.getDescription(), clientDN),
                kv(CN.getDescription(), cn),
                kv(SERVLET_PATH.getDescription(), getRequestPath()));
        String clientUUID = commonService.getClientUUIDAdminAPI(cn);
        throwExceptionIfApplicationIsExistsButDeleted(clientUUID, applicationRq);
        ApplicationEntity applicationEntity =
                applicationMapper.toApplicationEntity(
                        applicationRq,
                        clientUUID,
                        projectProperties.getDetectionNetworkMode()
                );
        ApplicationEntity savedApplication = applicationRepository.save(applicationEntity);
        return new ApplicationIdRs(savedApplication.getId());
    }

    private void throwExceptionIfApplicationIsExistsButDeleted(
            final String clientUUID,
            final ApplicationRq applicationRq
    ) {
        boolean isExistButDeleted = applicationRepository
                .findApplicationEntityByClientUUIDAndBundleIdAndPlatformAndDeletedAtIsNotNull(
                        clientUUID,
                        applicationRq.getBundleId(),
                        applicationRq.getPlatform()
                ).isPresent();
        if (isExistButDeleted) {
            log.error(
                    APPLICATION_WAS_DELETED_201.name(),
                    kv(
                            ERROR.getDescription(),
                            format(
                                    APPLICATION_WAS_DELETED_201.getDescription(),
                                    applicationRq.getBundleId(),
                                    applicationRq.getPlatform().name())
                    ),
                    kv(REQUEST.getDescription(), applicationRq),
                    kv(CLIENT_UUID.getDescription(), getClientUUID()),
                    kv(SERVLET_PATH.getDescription(), getRequestPath()));
            throw new BusinessLogicException(
                    new ErrorResponse(
                            APPLICATION_WAS_DELETED_201.getCode(),
                            format(
                                    APPLICATION_WAS_DELETED_201.getDescription(),
                                    applicationRq.getBundleId(),
                                    applicationRq.getPlatform().name()
                            ),
                            APPLICATION_WAS_DELETED_201.getMessage(),
                            MDC.get(TRACE_ID.getDescription())
                    )
            );
        }
    }
}
