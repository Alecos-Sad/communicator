package by.sadovnick.communicator.enums;

import lombok.Getter;

/**
 * Тип системы идущей с запросом в сервис
 */
@Getter
public enum SubSystem {
    ADMIN("admin"),
    CLIENT("client");

    private final String description;

    SubSystem(String description) {
        this.description = description;
    }
}
