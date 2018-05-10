package com.epam.provider.dao.factory.entity;

import com.epam.provider.model.Entity;

import java.util.Map;

public abstract class AbstractProviderEntityFactory {
    protected Entity entity;

    public void initFields(Map<String, String> propertyAndValue) {
        propertyAndValue.entrySet().forEach((entry)->setEntityField(entry.getKey(), entry.getValue()));
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

    public abstract Entity getEntity(Map<String, String> properties);

    protected abstract void setEntityField(String propertyName, String propertyValue);
}
