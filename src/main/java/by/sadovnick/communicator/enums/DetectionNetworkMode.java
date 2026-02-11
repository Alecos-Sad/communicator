package by.sadovnick.communicator.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Способ определения сети.
 * EXTERNAL - CerberSDK считает, что МП в Internet идет через проксирование SberMAG.
 * INTERNAL - CerberSDK считает, что МП в сети Сбера и идет напрямую в АС.
 * AUTO - CerberCDK сам определяет сеть.
 */
public enum DetectionNetworkMode {
    EXTERNAL,
    INTERNAL,
    AUTO;

    public static DetectionNetworkMode fromString(String value) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("Platform value cannot be null or empty");
        }
        try {
            return DetectionNetworkMode.valueOf(value.strip().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    String.format("Unknown platform: %s. Supported values: ANDROID, IOS", value)
            );
        }
    }
}
