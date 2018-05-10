package com.epam.provider.dao;

import java.util.*;

public class DBTableFieldName {

    //////////////TRANSACTION TABLE FIELDS//////////////
    public static final String PAYMENT_AMOUNT="amount";
    public static final String PAYMENT_DATE="date";
    public static final String PAYMENT_ID_TRANSACTIONS="id_transactions";
    public static final String PAYMENT_ID_PROFILES="id_profile";

    /////////PROFILE TABLE FIELDS////////////////////////
    public static final String PROFILE_ID="id_profiles";
    public static final String PROFILE_FIRST_NAME="first_name";
    public static final String PROFILE_BALANCE="balance";
    public static final String PROFILE_PASSPORT="passport";
    public static final String PROFILE_REGISTR_DATE="register_date";
    public static final String PROFILE_SECOND_NAME="second_name";
    public static final String PROFILE_ID_TARIFFS="id_tariffs";
    public static final String PROFILE_LOGIN="login";
    public static final String PROFILE_PASS="pass";
    public static final String PROFILE_ROLE="role";

    public static Set<String> PROFILE_FIELD_SET= Collections.unmodifiableSet(new HashSet<>(Arrays.asList(PROFILE_ID
    ,PROFILE_FIRST_NAME,PROFILE_BALANCE,PROFILE_PASSPORT,PROFILE_REGISTR_DATE, PROFILE_SECOND_NAME,PROFILE_ID_TARIFFS
    ,PROFILE_LOGIN,PROFILE_PASS,PROFILE_ROLE)));

    ///////////////////TARIFF TABLE FIELD//////////////////////////
    public static final String TARIFF_ID="id_tariffs";
    public static final String TARIFF_NAME="name";
    public static final String TARIFF_PRICE="price";
    public static final String TARIFF_RECIEVE_SPEED="recieving_speed";
    public static final String TARIFF_TRANSFER_SPEED="transfer_speed";
    public static final String TARIFF_DESCRIPTION="description";
    public static final String TARIFF_LANG="lang";



    private DBTableFieldName(){
        throw new IllegalStateException("Utility class");
    }
}
