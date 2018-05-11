package com.epam.provider.dao.factory.entity;


import static com.epam.provider.model.Field.*;
import com.epam.provider.model.Field;
import com.epam.provider.model.Tariff;
import java.util.EnumMap;

public class TariffFactory extends AbstractProviderEntityFactory {

  private static TariffFactory factoryInstance = new TariffFactory();

  private TariffFactory() {
    fieldSetters=new EnumMap<>(Field.class);
    fieldSetters.put(TARIFF_ID, s -> ((Tariff)entity).setTariffId(getInt(s)));
    fieldSetters.put(TARIFF_DESCRIPTION, s -> ((Tariff)entity).setDescription(s));
    fieldSetters.put(TARIFF_NAME, s -> ((Tariff)entity).setName(s));
    fieldSetters.put(TARIFF_LANG, s -> ((Tariff)entity).setLang(s));
    fieldSetters.put(TARIFF_PRICE, s -> ((Tariff)entity).setPrice(getInt(s)));
    fieldSetters.put(TARIFF_RECEIVE_SPEED, s -> ((Tariff)entity).setReceivingSpeed(getInt(s)));
    fieldSetters.put(TARIFF_TRANSFER_SPEED, s -> ((Tariff)entity).setTransferSpeed(getInt(s)));
  }

  public static TariffFactory getInstance() {
    return factoryInstance;
  }

  @Override
  protected void createObject() {
    entity=new Tariff();
  }
}
