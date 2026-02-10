package by.sadovnick.communicator.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Операционная система на устройстве клиента
 */
public enum Platform {
    ANDROID,
    IOS;

    public static Platform fromString(String value) {
        if (StringUtils.isBlank(value.trim())) {
            throw new IllegalArgumentException("Platform value cannot be null or empty");
        }
        try {
            return Platform.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    String.format("Unknown platform: %s. Supported values: ANDROID, IOS", value)
            );
        }
    }
}
