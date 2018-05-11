package com.epam.provider.model;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public enum Field {
    PAYMENT_AMOUNT("amount"),
    PAYMENT_DATE("date"),
    PAYMENT_ID_TRANSACTIONS("id_transactions"),
    PAYMENT_ID_PROFILES("id_profile"),

    PROFILE_ID("id_profiles"),
    PROFILE_FIRST_NAME("first_name"),
    PROFILE_BALANCE("balance"),
    PROFILE_PASSPORT("passport"),
    PROFILE_REGISTR_DATE("register_date"),
    PROFILE_SECOND_NAME("second_name"),
    PROFILE_ID_TARIFFS("id_tariffs"),
    PROFILE_LOGIN("login"),
    PROFILE_PASS("pass"),
    PROFILE_ROLE("role"),

    TARIFF_ID("id_tariffs"),
    TARIFF_NAME("name"),
    TARIFF_PRICE("price"),
    TARIFF_RECIEVE_SPEED("receiving_speed"),
    TARIFF_TRANSFER_SPEED("transfer_speed"),
    TARIFF_DESCRIPTION("description"),
    TARIFF_LANG("lang");

    public static Set<Field> tariffFields= Collections.unmodifiableSet(EnumSet.of(TARIFF_DESCRIPTION,TARIFF_ID,TARIFF_LANG,TARIFF_NAME,
            TARIFF_PRICE,TARIFF_RECIEVE_SPEED,TARIFF_TRANSFER_SPEED));
    public static Set<Field> profileFields= Collections.unmodifiableSet(EnumSet.of(PROFILE_FIRST_NAME,PROFILE_BALANCE,PROFILE_ID,
            PROFILE_ID_TARIFFS,PROFILE_LOGIN,PROFILE_PASS,PROFILE_PASSPORT,PROFILE_REGISTR_DATE,PROFILE_ROLE,PROFILE_SECOND_NAME));
    private final String name;

    Field(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
