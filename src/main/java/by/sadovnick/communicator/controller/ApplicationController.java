package by.sadovnick.communicator.controller;

import by.sadovnick.communicator.dto.ApplicationIdRs;
import by.sadovnick.communicator.dto.ApplicationRq;
import by.sadovnick.communicator.service.v1.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static by.sadovnick.communicator.enums.JsonLogField.*;
import static by.sadovnick.communicator.enums.SubSystem.ADMIN;
import static by.sadovnick.communicator.util.RequestUtil.getClientUUID;
import static by.sadovnick.communicator.util.RequestUtil.getRequestPath;
import static net.logstash.logback.argument.StructuredArguments.kv;

/**
 * REST-контроллер для операций с приложением.
 * Обрабатывает входящие запросы API версии 'v1', валидирует данные
 * и делегирует бизнес-логику сервисному слою
 */
@RestController
@RequestMapping(value = "/v1")
@Slf4j
@RequiredArgsConstructor
@Validated
public class ApplicationController {

    private final ApplicationService applicationService;

    /**
     * Создает новое приложение. Если такое приложение уже есть и помечено на удаление, то возвращает ошибку.
     * @param clientDN значение заголовка X-Cert-DN c DN клиентского сертификата.
     * @param applicationRq тело запроса с данными приложения для создания.
     * @return идентификатор созданного приложения.
     */
    @Operation(
            summary = "Создает приложение",
            description = "Если такое приложение уже есть и помечено на удаление, то возвращает ошибку"
    )
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
