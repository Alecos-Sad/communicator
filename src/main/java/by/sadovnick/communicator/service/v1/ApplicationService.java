package by.sadovnick.communicator.service.v1;

import by.sadovnick.communicator.dto.ApplicationIdRs;
import by.sadovnick.communicator.dto.ApplicationRq;

public interface ApplicationService {

    ApplicationIdRs create(final ApplicationRq applicationRq, final String clientKey);
}
