package com.epam.provider.dao.factory.entity;


import static com.epam.provider.model.Field.*;

import com.epam.provider.model.Entity;
import com.epam.provider.model.Field;
import com.epam.provider.model.Tariff;

import java.util.EnumMap;

public class TariffFactory extends AbstractProviderEntityFactory {

    private static TariffFactory factoryInstance = new TariffFactory();

    private TariffFactory() {
        fieldSetters = new EnumMap<>(Field.class);
        fieldSetters.put(TARIFF_ID, (String s, Entity e) -> ((Tariff) e).setTariffId(getInt(s)));
        fieldSetters.put(TARIFF_DESCRIPTION, (String s, Entity e) -> ((Tariff) e).setDescription(s));
        fieldSetters.put(TARIFF_NAME, (String s, Entity e) -> ((Tariff) e).setName(s));
        fieldSetters.put(TARIFF_LANG, (String s, Entity e) -> ((Tariff) e).setLang(s));
        fieldSetters.put(TARIFF_PRICE, (String s, Entity e) -> ((Tariff) e).setPrice(getInt(s)));
        fieldSetters.put(TARIFF_RECEIVE_SPEED, (String s, Entity e) -> ((Tariff) e).setReceivingSpeed(getInt(s)));
        fieldSetters.put(TARIFF_TRANSFER_SPEED, (String s, Entity e) -> ((Tariff) e).setTransferSpeed(getInt(s)));
    }

    public static TariffFactory getInstance() {
        return factoryInstance;
    }

    @Override
    protected Entity generateEmptyObject() {
        return new Tariff();
    }
}
