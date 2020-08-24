package com.first.springboot.demo.data.ingredients;

import com.first.springboot.demo.domains.Ingredient;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TypeConverter implements AttributeConverter<Ingredient.Type, String> {

    @Override
    public String convertToDatabaseColumn(Ingredient.Type type) {
        return type.toString();
    }

    @Override
    public Ingredient.Type convertToEntityAttribute(String s) {
        return Ingredient.Type.valueOf(s);
    }
}
