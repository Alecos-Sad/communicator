package by.sadovnick.communicator.enums;

/**
 * Тип подключения.
 * proxy - подключение к URL только через шлюз SberMag,
 * bypass - взаимодействие мимо шлюза SberMAg
 * block - доступ к URL заблокирован
 * noaction - не выбрано
 */
public enum DestinationAction {
    PROXY,
    BLOCK,
    BYPASS,
    NOACTION
}
