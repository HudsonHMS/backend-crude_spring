package com.soares.hudson.springcrude.enuns;

import java.util.stream.Stream;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoriaConverter implements AttributeConverter<CategoriaEnum, String> {

    @Override
    public String convertToDatabaseColumn(CategoriaEnum attribute) {
        return attribute.getNome();
    }

    @Override
    public CategoriaEnum convertToEntityAttribute(String dbData) {
        return Stream.of(CategoriaEnum.values()).filter(el -> el.getNome().equals(dbData)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
