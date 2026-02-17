package by.sadovnick.communicator.converter;

import by.sadovnick.communicator.enums.DeviceType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DeviceTypeConverterTest {

    private final DeviceTypeConverter converter = new DeviceTypeConverter();

    @Test
    void convertToDatabaseColumnReturnsNullForNullAttribute() {
        assertNull(converter.convertToDatabaseColumn(null));
    }

    @ParameterizedTest(name = "{index} => attribute={0}, dbValue={1}")
    @MethodSource("deviceTypeToDbValueProvider")
    void convertToDatabaseColumnReturnsExpectedValue(DeviceType attribute, String expectedValue) {
        assertEquals(expectedValue, converter.convertToDatabaseColumn(attribute));
    }

    @ParameterizedTest(name = "{index} => dbValue={0}, expected={1}")
    @MethodSource("dbValueToDeviceTypeProvider")
    void convertToEntityAttributeReturnsExpectedDeviceType(String dbValue, DeviceType expectedType) {
        assertEquals(expectedType, converter.convertToEntityAttribute(dbValue));
    }

    @Test
    void convertToEntityAttributeThrowsExceptionForNullValue() {
        assertThrows(NullPointerException.class, () -> converter.convertToEntityAttribute(null));
    }

    private static Stream<Arguments> deviceTypeToDbValueProvider() {
        return Stream.of(
                Arguments.of(DeviceType.CORP, "corp"),
                Arguments.of(DeviceType.BYOD, "byod"),
                Arguments.of(DeviceType.EMPTY, "empty")
        );
    }

    private static Stream<Arguments> dbValueToDeviceTypeProvider() {
        return Stream.of(
                Arguments.of("corp", DeviceType.CORP),
                Arguments.of("CORP", DeviceType.CORP),
                Arguments.of("byod", DeviceType.BYOD),
                Arguments.of("unknown", DeviceType.EMPTY)
        );
    }
}