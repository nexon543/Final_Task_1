package com.epam.provider.model;

import java.util.Date;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * Created by HP on 27.03.2018.
 */
public class Transaction extends Entity {
    private OptionalInt idTransactions;
    private OptionalInt amount;
    private Optional<Date> date;
    private OptionalInt idProfiles;

}
