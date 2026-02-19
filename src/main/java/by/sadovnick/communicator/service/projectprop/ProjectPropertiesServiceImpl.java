package by.sadovnick.communicator.service.projectprop;

import by.sadovnick.communicator.config.ProjectProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

import static by.sadovnick.communicator.enums.JsonLogField.*;
import static by.sadovnick.communicator.util.RequestUtil.getRequestPath;
import static net.logstash.logback.argument.StructuredArguments.kv;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProjectPropertiesServiceImpl implements ProjectPropertiesService {

    private final ProjectProperties projectProperties;

    @Override
    public String getClientUUID(String cn) {
        Map<String, String> certMap = projectProperties.getCertMap();
        if (certMap == null || certMap.isEmpty()) {
            log.error("Error occurred during cert_map initialization from Secman",
                    kv(ERROR.getDescription(), "cert-map section in yaml is null, empty or not found"),
                    kv(CN.getDescription(), cn),
                    kv(SERVLET_PATH.getDescription(), getRequestPath()));
            return null;
        }
        if (isBlank(cn) && !projectProperties.isSowaEnabled()) {
            log.warn("CN is blank and SOWA is disabled!",
                    kv(CN.getDescription(), cn),
                    kv("sowaEnabled", projectProperties.isSowaEnabled()),
                    kv(SERVLET_PATH.getDescription(), getRequestPath()));
            return certMap.get(projectProperties.getDn());
        }
        return certMap.get(cn);
    }
}
