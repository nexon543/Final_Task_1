package com.epam.provider.dao.factory.entity;

import com.epam.provider.model.Entity;
import com.epam.provider.model.Field;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;


public abstract class AbstractProviderEntityFactory {

    protected Map<Field, BiConsumer<String, Entity>> fieldSetters;

    protected abstract Entity generateEmptyObject();

    public Entity getEntity(Map<Field, String> fieldKeyValue) {
        Entity newEntity = generateEmptyObject();
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

    private void initFields(Entity entity, Map<Field, String> fieldKeyValue) {
        fieldKeyValue.forEach((k,v)->setEntityField(entity,k,v));
    }

    private void setEntityField(Entity entity, Field propertyName, String propertyValue) {
        if (Optional.ofNullable(propertyValue).filter(s -> !"".equals(s)).isPresent()) {
            Optional.ofNullable(fieldSetters.get(propertyName))
                    .ifPresent(c -> c.accept(propertyValue, entity));
        }
    }
}
