package com.recipefinder.services;

import com.recipefinder.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface IUnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
