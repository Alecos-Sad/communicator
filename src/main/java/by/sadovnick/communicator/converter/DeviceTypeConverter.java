package by.sadovnick.communicator.converter;

import by.sadovnick.communicator.enums.DeviceType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Это JPA-конвертер (Jakarta Persistence), который автоматически переводит поле типа DeviceType в значение,
 * для БД и обратно.
 */
@Converter(autoApply = true)
public class DeviceTypeConverter implements AttributeConverter<DeviceType, String> {

    @Override
    public String convertToDatabaseColumn(DeviceType attribute) {
        return (attribute == null) ? null : attribute.getValue();
    }

    @Override
    public DeviceType convertToEntityAttribute(String dbData) {
        return DeviceType.getDeviceType(dbData);
    }
}
