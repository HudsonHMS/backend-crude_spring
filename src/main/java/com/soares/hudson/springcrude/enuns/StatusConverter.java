package com.soares.hudson.springcrude.enuns;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<StatusEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusEnum attribute) {
        if( null == attribute ) return null;
        return switch (attribute) {
            case ATIVO -> 1;
            case INATIVO -> 2;
            default -> null;
        };
    }

    @Override
    public StatusEnum convertToEntityAttribute(Integer dbData) {
        if( null == dbData ) return null;
        return switch( dbData ) {
            case 1 -> StatusEnum.ATIVO;
            case 2 -> StatusEnum.INATIVO;
            default -> null;
        };
    }

}
