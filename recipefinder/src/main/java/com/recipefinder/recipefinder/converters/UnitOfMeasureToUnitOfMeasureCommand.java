package com.recipefinder.recipefinder.converters;

import com.recipefinder.recipefinder.commands.UnitOfMeasureCommand;
import com.recipefinder.recipefinder.model.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        return null;
    }
}
