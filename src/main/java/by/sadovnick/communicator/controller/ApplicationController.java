package by.sadovnick.communicator.controller;

import by.sadovnick.communicator.dto.ApplicationIdRs;
import by.sadovnick.communicator.dto.ApplicationRq;
import by.sadovnick.communicator.enums.JsonLogField;
import by.sadovnick.communicator.enums.SubSystem;
import by.sadovnick.communicator.service.v1.ApplicationService;
import by.sadovnick.communicator.util.RequestUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.argument.StructuredArguments;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static by.sadovnick.communicator.enums.JsonLogField.*;
import static by.sadovnick.communicator.enums.SubSystem.*;
import static by.sadovnick.communicator.util.RequestUtil.*;
import static net.logstash.logback.argument.StructuredArguments.*;

@RestController
@RequestMapping(value = "/v1")
@Slf4j
@RequiredArgsConstructor
@Validated
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping(path = "/application", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApplicationIdRs> create(
            @RequestHeader(value = "X-Cert-DN", required = false)
            @Size(max = 255)
            @Pattern(regexp = ".*") final String clientDN,
            @Valid
            @RequestBody final ApplicationRq applicationRq
    ) {
        ApplicationIdRs applicationIdRs = applicationService.create(applicationRq, clientDN);
        log.info("Application was saved",
                kv(SUB_SYSTEM.getDescription(), ADMIN.getDescription()),
                kv(HTTP_METHOD.getDescription(), HttpMethod.POST),
                kv(REQUEST.getDescription(), applicationRq),
                kv(RESPONSE.getDescription(), applicationIdRs),
                kv(CLIENT_UUID.getDescription(), getClientUUID()),
                kv(SERVLET_PATH.getDescription(), getRequestPath()));
        return ResponseEntity.ok().body(applicationIdRs);
    }
}
