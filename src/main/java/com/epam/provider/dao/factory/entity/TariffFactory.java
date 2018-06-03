package com.epam.provider.dao.factory.entity;


import static com.epam.provider.model.Field.*;

import com.epam.provider.model.Entity;
import com.epam.provider.model.Field;
import com.epam.provider.model.Tariff;

import java.util.EnumMap;

public class TariffFactory extends AbstractProviderEntityFactory <Tariff> {

    private static TariffFactory factoryInstance = new TariffFactory();

    private TariffFactory() {
        fieldSetters = new EnumMap<>(Field.class);
        fieldSetters.put(TARIFF_ID, (String s, Tariff e) -> e.setTariffId(getInt(s)));
        fieldSetters.put(TARIFF_DESCRIPTION, (String s, Tariff e) -> e.setDescription(s));
        fieldSetters.put(TARIFF_NAME, (String s, Tariff e) -> e.setName(s));
        fieldSetters.put(TARIFF_LANG, (String s, Tariff e) -> e.setLang(s));
        fieldSetters.put(TARIFF_PRICE, (String s, Tariff e) -> e.setPrice(getInt(s)));
        fieldSetters.put(TARIFF_RECEIVE_SPEED, (String s, Tariff e) -> e.setReceivingSpeed(getInt(s)));
        fieldSetters.put(TARIFF_TRANSFER_SPEED, (String s, Tariff e) -> e.setTransferSpeed(getInt(s)));
    }

    public static TariffFactory getInstance() {
        return factoryInstance;
    }

    @Override
    protected Tariff generateEmptyObject() {
        return new Tariff();
    }
}
