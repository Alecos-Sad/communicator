package by.sadovnick.communicator.service.impl;

import by.sadovnick.communicator.dto.ErrorResponse;
import by.sadovnick.communicator.exception.BusinessLogicException;
import by.sadovnick.communicator.service.CommonService;
import by.sadovnick.communicator.service.ProjectPropertiesService;
import by.sadovnick.communicator.util.ParserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import static by.sadovnick.communicator.enums.ErrorStatus.*;
import static by.sadovnick.communicator.enums.JsonLogField.*;
import static by.sadovnick.communicator.enums.SubSystem.ADMIN;
import static by.sadovnick.communicator.util.RequestUtil.getRequestPath;
import static by.sadovnick.communicator.util.RequestUtil.setClientUUID;
import static io.micrometer.common.util.StringUtils.isBlank;
import static java.lang.String.*;
import static net.logstash.logback.argument.StructuredArguments.kv;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommonServiceImpl implements CommonService {

    private final ProjectPropertiesService projectPropertiesService;

    @Override
    public String parseCnFromDnAdminAPI(String clientDn) {
        if (isBlank(clientDn)) {
            log.error(CLIENT_DN_IS_ABSENT_105.name(),
                    kv(CLIENT_DN.getDescription(), clientDn),
                    kv(SUB_SYSTEM.getDescription(), ADMIN.getDescription()),
                    kv(SERVLET_PATH.getDescription(), getRequestPath())
            );
            throw new BusinessLogicException(
                    new ErrorResponse(
                            CLIENT_DN_IS_ABSENT_105.getCode(),
                            CLIENT_DN_IS_ABSENT_105.getDescription(),
                            CLIENT_DN_IS_ABSENT_105.getMessage(),
                            MDC.get(TRACE_ID.getDescription())
                    )
            );
        }
        log.debug("Try to parse clientDN",
                kv(CLIENT_DN.getDescription(), clientDn),
                kv(SUB_SYSTEM.getDescription(), ADMIN.getDescription()),
                kv(SERVLET_PATH.getDescription(), getRequestPath())
        );
        String cn = ParserUtil.getCNFromDN(clientDn);
        if (isBlank(cn)) {
            log.error(CLIENT_DN_NOT_VALID_104.name(),
                    kv(CLIENT_DN.getDescription(), clientDn),
                    kv(SUB_SYSTEM.getDescription(), ADMIN.getDescription()),
                    kv(SERVLET_PATH.getDescription(), getRequestPath())
            );
            throw new BusinessLogicException(
                    new ErrorResponse(
                            CLIENT_DN_NOT_VALID_104.getCode(),
                            CLIENT_DN_NOT_VALID_104.getDescription(),
                            CLIENT_DN_NOT_VALID_104.getMessage(),
                            MDC.get(TRACE_ID.getDescription())
                    )
            );
        }
        return cn;
    }

    @Override
    public String getClientUUIDAdminAPI(String cn) {
        String clientUUID = projectPropertiesService.getClientUUID(cn);
        if (isBlank(clientUUID)) {
            log.error(CLIENT_UUID_IS_ABSENT_101.name(),
                    kv(ERROR.getDescription(), format(CLIENT_UUID_IS_ABSENT_101.getDescription(), cn)),
                    kv(SUB_SYSTEM.getDescription(), ADMIN.getDescription()),
                    kv(SERVLET_PATH.getDescription(), getRequestPath())
            );
            throw new BusinessLogicException(
                    new ErrorResponse(
                            CLIENT_UUID_IS_ABSENT_101.getCode(),
                            format(CLIENT_UUID_IS_ABSENT_101.getDescription(), cn),
                            CLIENT_UUID_IS_ABSENT_101.getMessage(),
                            MDC.get(TRACE_ID.getDescription())
                    )
            );
        }
        setClientUUID(clientUUID);
        return clientUUID;
    }
}
