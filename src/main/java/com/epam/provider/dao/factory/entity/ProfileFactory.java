package com.epam.provider.dao.factory.entity;

import static com.epam.provider.model.Field.*;


import com.epam.provider.model.Entity;
import com.epam.provider.model.Field;
import com.epam.provider.model.Profile;
import java.sql.Date;
import java.util.EnumMap;

public class ProfileFactory extends AbstractProviderEntityFactory {

  private static ProfileFactory instance = new ProfileFactory();

  private ProfileFactory() {
    fieldSetters=new EnumMap<>(Field.class);
    fieldSetters.put(PROFILE_BALANCE, (String s, Entity e) -> ((Profile)e).setBalance(getDouble(s)));
    fieldSetters.put(PROFILE_FIRST_NAME, (String s, Entity e) -> ((Profile)e).setFirstName(s));
    fieldSetters.put(PROFILE_SECOND_NAME,  (String s, Entity e) -> ((Profile)e).setSecondName(s));
    fieldSetters.put(PROFILE_ID, (String s, Entity e) -> ((Profile)e).setProfileId(getInt(s)));
    fieldSetters.put(PROFILE_ID_TARIFFS, (String s, Entity e) -> ((Profile)e).setIdTariffs(getInt(s)));
    fieldSetters.put(PROFILE_LOGIN, (String s, Entity e) -> ((Profile)e).setLogin(s));
    fieldSetters.put(PROFILE_PASS, (String s, Entity e) -> ((Profile)e).setPassword((s)));
    fieldSetters.put(PROFILE_PASSPORT,  (String s, Entity e) -> ((Profile)e).setPassport(s));
    fieldSetters.put(PROFILE_ROLE,  (String s, Entity e) -> ((Profile)e).setRole(s));
    fieldSetters.put(PROFILE_REGISTR_DATE,
        (String s, Entity e) -> ((Profile)e).setRegisterDate(new Date(new java.util.Date().getTime())));
  }

  @Override
  protected Entity generateEmptyObject() {
    return new Profile();
  }

  public static ProfileFactory getInstance() {
    return instance;
  }

}
