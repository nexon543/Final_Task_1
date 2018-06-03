package com.epam.provider.dao.factory.entity;

import com.epam.provider.model.Entity;
import com.epam.provider.model.Field;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;


public abstract class AbstractProviderEntityFactory <T extends Entity> {

    protected Map<Field, BiConsumer<String, T>> fieldSetters;

    protected abstract T generateEmptyObject();

    public T getEntity(Map<Field, String> fieldKeyValue) {
        T newEntity = generateEmptyObject();
        initFields(newEntity, fieldKeyValue);
        return newEntity;
    }

    protected Float getFloat(String value) {
        return Float.parseFloat(value);
    }

    protected Double getDouble(String value) {
        return Double.parseDouble(value);
    }

    protected Integer getInt(String value) {
        return Integer.parseInt(value);
    }

    private void initFields(T entity, Map<Field, String> fieldKeyValue) {
        fieldKeyValue.forEach((k,v)->setEntityField(entity,k,v));
    }

    private void setEntityField(T entity, Field propertyName, String propertyValue) {
        if (Optional.ofNullable(propertyValue).filter(s -> !"".equals(s)).isPresent()) {
            Optional.ofNullable(fieldSetters.get(propertyName))
                    .ifPresent(c -> c.accept(propertyValue, entity));
        }
    }
}
