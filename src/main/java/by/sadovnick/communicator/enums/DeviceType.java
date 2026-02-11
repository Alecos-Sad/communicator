package by.sadovnick.communicator.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Locale;

/**
 * Тип мобильного устройства
 */
@Getter
public enum DeviceType {
    CORP("corp"),
    BYOD("byod"),
    EMPTY("empty");

    private final String value;

    DeviceType(String value) {
        this.value = value;
    }

    public static DeviceType getDeviceType(String value) {
        return Arrays
                .stream(DeviceType.values())
                .filter(dt -> dt.getValue().equals(value.toLowerCase(Locale.ROOT)))
                .findFirst()
                .orElse(EMPTY);
    }
}
