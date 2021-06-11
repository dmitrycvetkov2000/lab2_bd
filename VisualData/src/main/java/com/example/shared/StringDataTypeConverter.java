package com.example.shared;

import java.time.OffsetDateTime;

public class StringDataTypeConverter {
    public Object convertToObjectByType(String value, Class<?> type){
        if(type.isAssignableFrom(Double.class))
            return Double.parseDouble(value);
        if(type.isAssignableFrom(Float.class))
            return Float.parseFloat(value);
        if(type.isAssignableFrom(Integer.class))
            return Integer.parseInt(value);
        if(type.isAssignableFrom(Long.class))
            return Long.parseLong(value);
        if(type.isAssignableFrom(OffsetDateTime.class))
            return OffsetDateTime.parse(value);
        return value;
    }
}
