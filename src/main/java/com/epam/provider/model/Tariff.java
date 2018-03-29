package com.epam.provider.model;

import java.util.Optional;
import java.util.OptionalInt;

/**
 * Created by HP on 27.03.2018.
 */
public class Tariff extends Entity {
    private OptionalInt idTarifs;
    private OptionalInt price;
    private OptionalInt recievingSpeed;
    private OptionalInt transferingSpeed;
    private OptionalInt trafficVolume;
    private Optional<String> name;
}
