package com.epam.provider.dao.factory.entity;

import com.epam.provider.model.Entity;
import com.epam.provider.model.fields.TableFieldName;
import java.util.Map;

public abstract class AbstractProviderEntityFactory {

  public void initFields(Map<TableFieldName, String> propertyAndValue) {
    propertyAndValue.forEach(this::setEntityField);
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

  public abstract Entity getEntity(Map<TableFieldName, String> properties);

  protected abstract void setEntityField(TableFieldName propertyName, String propertyValue);
}
