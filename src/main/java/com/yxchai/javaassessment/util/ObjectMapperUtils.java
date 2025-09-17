package com.yxchai.javaassessment.util;

import org.modelmapper.ModelMapper;

public class ObjectMapperUtils {
    private static ModelMapper modelMapper = new ModelMapper();

    public static <D,T> D map(final T sourceObject, Class<D> resultObjectClass) {
        return modelMapper.map(sourceObject, resultObjectClass);
    }
}
