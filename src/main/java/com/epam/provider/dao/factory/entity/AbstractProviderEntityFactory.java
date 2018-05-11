package com.epam.provider.dao.factory.entity;

import com.epam.provider.model.Entity;
import com.epam.provider.model.Field;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;


public abstract class AbstractProviderEntityFactory {

  protected Entity entity;
  protected Map<Field, Consumer<String>> fieldSetters;
  public void initFields(Map<Field, String> fieldKeyValue) {
    fieldKeyValue.forEach(this::setEntityField);
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

  public Entity getEntity(Map<Field, String> fieldKeyValue)
  {
    createObject();
    initFields(fieldKeyValue);
    return entity;
  }

  protected abstract void createObject();
  protected void setEntityField(Field propertyName, String propertyValue){
    if (Optional.ofNullable(propertyValue).filter(s -> !"".equals(s)).isPresent()) {
      Optional.ofNullable(fieldSetters.get(propertyName))
              .ifPresent(c -> c.accept(propertyValue));
    }
  }
}
