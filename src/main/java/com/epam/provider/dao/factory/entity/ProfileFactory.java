package com.epam.provider.dao.factory.entity;

import com.epam.provider.model.Entity;
import com.epam.provider.model.Profile;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import static com.epam.provider.dao.DBTableFieldName.*;

public class ProfileFactory extends AbstractProviderEntityFactory {

    private static ProfileFactory instance = new ProfileFactory();
    private Map<String, Consumer<String>> profileFieldSetters = new HashMap<>();

    private Profile profileInstance;

    private ProfileFactory() {
        profileFieldSetters.put(PROFILE_BALANCE, s -> profileInstance.setBalance(getDouble(s)));
        profileFieldSetters.put(PROFILE_FIRST_NAME, s -> profileInstance.setFirstName(s));
        profileFieldSetters.put(PROFILE_SECOND_NAME, s -> profileInstance.setSecondName(s));
        profileFieldSetters.put(PROFILE_ID, s -> profileInstance.setProfileId(getInt(s)));
        profileFieldSetters.put(PROFILE_ID_TARIFFS, s -> profileInstance.setIdTariffs(getInt(s)));
        profileFieldSetters.put(PROFILE_LOGIN, s -> profileInstance.setLogin(s));
        profileFieldSetters.put(PROFILE_PASS, s -> profileInstance.setPassword((s)));
        profileFieldSetters.put(PROFILE_PASSPORT, s -> profileInstance.setPassport(s));
        profileFieldSetters.put(PROFILE_ROLE, s -> profileInstance.setRole(s));
        profileFieldSetters.put(PROFILE_REGISTR_DATE, s->profileInstance.setRegisterDate(new Date(new java.util.Date().getTime())));
    }

    public static ProfileFactory getInstance() {
        return instance;
    }

    @Override
    public Entity getEntity(Map<String, String> properties) {
        profileInstance=new Profile();
        properties.forEach(instance::setEntityField);
        return profileInstance;
    }

    @Override
    protected void setEntityField(String propertyName, String propertyValue) {
        if (Optional.ofNullable(propertyValue).filter(s -> !s.equals("")).isPresent()) {
            Optional.ofNullable(profileFieldSetters.get(propertyName)).ifPresent(c -> c.accept(propertyValue));
        }
    }
}
