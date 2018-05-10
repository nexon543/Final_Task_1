package com.epam.provider.dao.factory.entity;


import static com.epam.provider.model.fields.ProfileField.BALANCE;
import static com.epam.provider.model.fields.ProfileField.FIRST_NAME;
import static com.epam.provider.model.fields.ProfileField.ID;
import static com.epam.provider.model.fields.ProfileField.ID_TARIFFS;
import static com.epam.provider.model.fields.ProfileField.LOGIN;
import static com.epam.provider.model.fields.ProfileField.PASS;
import static com.epam.provider.model.fields.ProfileField.PASSPORT;
import static com.epam.provider.model.fields.ProfileField.REGISTR_DATE;
import static com.epam.provider.model.fields.ProfileField.ROLE;
import static com.epam.provider.model.fields.ProfileField.SECOND_NAME;

import com.epam.provider.model.Entity;
import com.epam.provider.model.Profile;
import com.epam.provider.model.fields.ProfileField;
import com.epam.provider.model.fields.TableFieldName;
import java.sql.Date;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class ProfileFactory extends AbstractProviderEntityFactory {

  private static ProfileFactory instance = new ProfileFactory();
  private Map<ProfileField, Consumer<String>> profileFieldSetters = new EnumMap<>(
      ProfileField.class);

  private Profile profileInstance;

  private ProfileFactory() {
    profileFieldSetters.put(BALANCE, s -> profileInstance.setBalance(getDouble(s)));
    profileFieldSetters.put(FIRST_NAME, s -> profileInstance.setFirstName(s));
    profileFieldSetters.put(SECOND_NAME, s -> profileInstance.setSecondName(s));
    profileFieldSetters.put(ID, s -> profileInstance.setProfileId(getInt(s)));
    profileFieldSetters.put(ID_TARIFFS, s -> profileInstance.setIdTariffs(getInt(s)));
    profileFieldSetters.put(LOGIN, s -> profileInstance.setLogin(s));
    profileFieldSetters.put(PASS, s -> profileInstance.setPassword((s)));
    profileFieldSetters.put(PASSPORT, s -> profileInstance.setPassport(s));
    profileFieldSetters.put(ROLE, s -> profileInstance.setRole(s));
    profileFieldSetters.put(REGISTR_DATE,
        s -> profileInstance.setRegisterDate(new Date(new java.util.Date().getTime())));
  }

  public static ProfileFactory getInstance() {
    return instance;
  }

  @Override
  public Entity getEntity(Map<TableFieldName, String> properties) {
    profileInstance = new Profile();
    properties.forEach(instance::setEntityField);
    return profileInstance;
  }

  @Override
  protected void setEntityField(TableFieldName propertyName, String propertyValue) {
    if (Optional.ofNullable(propertyValue).filter(s -> !"".equals(s)).isPresent()) {
      Optional.ofNullable(profileFieldSetters.get(propertyName))
          .ifPresent(c -> c.accept(propertyValue));
    }
  }
}
