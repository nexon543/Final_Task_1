package com.epam.provider.dao.factory.entity;

import static com.epam.provider.model.Field.*;


import com.epam.provider.model.Entity;
import com.epam.provider.model.Field;
import com.epam.provider.model.Profile;
import java.sql.Date;
import java.util.EnumMap;

public class ProfileFactory extends AbstractProviderEntityFactory <Profile> {

  private static ProfileFactory instance = new ProfileFactory();

  private ProfileFactory() {
    fieldSetters=new EnumMap<>(Field.class);
    fieldSetters.put(PROFILE_BALANCE, (String s, Profile e) -> e.setBalance(getDouble(s)));
    fieldSetters.put(PROFILE_FIRST_NAME, (String s, Profile e) -> e.setFirstName(s));
    fieldSetters.put(PROFILE_SECOND_NAME,  (String s, Profile e) -> e.setSecondName(s));
    fieldSetters.put(PROFILE_ID, (String s, Profile e) -> e.setProfileId(getInt(s)));
    fieldSetters.put(PROFILE_ID_TARIFFS, (String s, Profile e) -> e.setIdTariffs(getInt(s)));
    fieldSetters.put(PROFILE_LOGIN, (String s, Profile e) -> e.setLogin(s));
    fieldSetters.put(PROFILE_PASS, (String s, Profile e) -> e.setPassword((s)));
    fieldSetters.put(PROFILE_PASSPORT,  (String s, Profile e) -> e.setPassport(s));
    fieldSetters.put(PROFILE_ROLE,  (String s, Profile e) -> e.setRole(s));
    fieldSetters.put(PROFILE_REGISTR_DATE,
        (String s, Profile e) -> e.setRegisterDate(new Date(new java.util.Date().getTime())));
  }

  @Override
  protected Profile generateEmptyObject() {
    return new Profile();
  }

  public static ProfileFactory getInstance() {
    return instance;
  }

}
