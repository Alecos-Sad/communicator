package by.sadovnick.communicator.enums;

/**
 * Способ определения сети.
 * EXTERNAL - CerberSDK считает, что МП в Internet идет через проксирование SberMAG.
 * INTERNAL - CerberSDK считает, что МП в сети Сбера и идет напрямую в АС.
 * AUTO - CerberCDK сам определяет сеть.
 */
public enum DetectionNetworkMode {
    EXTERNAL,
    INTERNAL,
    AUTO
}
