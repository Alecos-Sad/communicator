package by.sadovnick.communicator.enums;

import lombok.Getter;

@Getter
public enum ErrorStatus {

    VALIDATION_ERROR("SM-COMM-2", "dto validation error", Messages.MESSAGE_1),

    DATABASE_SQL_ERROR("SM-COMM-26", "Error while insert or update data in database", Messages.MESSAGE_1),

    CLIENT_UUID_IS_ABSENT_101("SM-COMM-101", "Client UUID is absent for cn=%s", Messages.MESSAGE_1),
    CLIENT_DN_NOT_VALID_104("SM-COMM-104", "ClientDN not valid. Check X-Cert-DN header", Messages.MESSAGE_1),
    CLIENT_DN_IS_ABSENT_105("SM-COMM-105", "ClientDN is absent. Check X-Cert-DN header", Messages.MESSAGE_1),


    APPLICATION_WAS_DELETED_201("SM-COM-201", "Application with bundleId=%s for platform,=%s was deleted", Messages.MESSAGE_1);

    private final String code;
    private final String description;
    private final String message;

    ErrorStatus(String code, String description, String message) {
        this.code = code;
        this.description = description;
        this.message = message;
    }

    private static class Messages {
        public static final String MESSAGE_1 = "Обратитесь в поддержку - сообщите код ошибки и время возникновения!";
        public static final String MESSAGE_2 = "Не используйте запрещенные домены для настройки!";
    }
}
