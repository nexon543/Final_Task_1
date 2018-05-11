package com.epam.provider.dao.factory.entity;


import static com.epam.provider.model.Field.*;


import com.epam.provider.model.Field;
import com.epam.provider.model.Profile;
import java.sql.Date;
import java.util.EnumMap;

public class ProfileFactory extends AbstractProviderEntityFactory {

  private static ProfileFactory instance = new ProfileFactory();

  private ProfileFactory() {
    fieldSetters=new EnumMap<>(Field.class);
    entity=new Profile();
    fieldSetters.put(PROFILE_BALANCE, s -> ((Profile)entity).setBalance(getDouble(s)));
    fieldSetters.put(PROFILE_FIRST_NAME, s -> ((Profile)entity).setFirstName(s));
    fieldSetters.put(PROFILE_SECOND_NAME,  s -> ((Profile)entity).setSecondName(s));
    fieldSetters.put(PROFILE_ID, s -> ((Profile)entity).setProfileId(getInt(s)));
    fieldSetters.put(PROFILE_ID_TARIFFS, s -> ((Profile)entity).setIdTariffs(getInt(s)));
    fieldSetters.put(PROFILE_LOGIN, s->((Profile)entity).setLogin(s));
    fieldSetters.put(PROFILE_PASS, s -> ((Profile)entity).setPassword((s)));
    fieldSetters.put(PROFILE_PASSPORT,  s -> ((Profile)entity).setPassport(s));
    fieldSetters.put(PROFILE_ROLE,  s -> ((Profile)entity).setRole(s));
    fieldSetters.put(PROFILE_REGISTR_DATE,
        s -> ((Profile)entity).setRegisterDate(new Date(new java.util.Date().getTime())));
  }

  @Override
  protected void createObject() {
    entity=new Profile();
  }

  public static ProfileFactory getInstance() {
    return instance;
  }

}
