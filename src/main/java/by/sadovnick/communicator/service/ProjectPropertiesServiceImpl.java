package by.sadovnick.communicator.service;

import by.sadovnick.communicator.config.ProjectProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProjectPropertiesServiceImpl implements ProjectPropertiesService {

    private final ProjectProperties projectProperties;

    @Override
    public String getClientUUID(String cn) {
        Map<String, String> certMap = projectProperties.getCertMap();
        return certMap.get(cn);
    }
}
