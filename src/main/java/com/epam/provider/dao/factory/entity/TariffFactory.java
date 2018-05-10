package com.epam.provider.dao.factory.entity;


import static com.epam.provider.model.fields.TariffField.DESCRIPTION;
import static com.epam.provider.model.fields.TariffField.ID;
import static com.epam.provider.model.fields.TariffField.LANG;
import static com.epam.provider.model.fields.TariffField.NAME;
import static com.epam.provider.model.fields.TariffField.PRICE;
import static com.epam.provider.model.fields.TariffField.RECIEVE_SPEED;
import static com.epam.provider.model.fields.TariffField.TRANSFER_SPEED;

import com.epam.provider.model.Entity;
import com.epam.provider.model.Tariff;
import com.epam.provider.model.fields.TableFieldName;
import com.epam.provider.model.fields.TariffField;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class TariffFactory extends AbstractProviderEntityFactory {

  private static TariffFactory factoryInstance = new TariffFactory();
  private Map<TariffField, Consumer<String>> tariffFiledSetters = new EnumMap<>(
      TariffField.class);

  private Tariff tariff;

  private TariffFactory() {
    tariffFiledSetters.put(ID, s -> tariff.setTariffId(getInt(s)));
    tariffFiledSetters.put(DESCRIPTION, s -> tariff.setDescription(s));
    tariffFiledSetters.put(NAME, s -> tariff.setName(s));
    tariffFiledSetters.put(LANG, s -> tariff.setLang(s));
    tariffFiledSetters.put(PRICE, s -> tariff.setPrice(getInt(s)));
    tariffFiledSetters.put(RECIEVE_SPEED, s -> tariff.setReceivingSpeed(getInt(s)));
    tariffFiledSetters.put(TRANSFER_SPEED, s -> tariff.setTransferSpeed(getInt(s)));
  }

  @Override
  public Entity getEntity(Map<TableFieldName, String> properties) {
    tariff = new Tariff();
    properties.forEach(factoryInstance::setEntityField);
    return tariff;
  }

  @Override
  protected void setEntityField(TableFieldName propertyName, String propertyValue) {
    Optional.ofNullable(propertyValue).filter(s -> !"".equals(s)).ifPresent(s ->
        Optional.ofNullable(tariffFiledSetters.get(propertyName))
            .ifPresent(c -> c.accept(propertyValue))
    );
  }
}
