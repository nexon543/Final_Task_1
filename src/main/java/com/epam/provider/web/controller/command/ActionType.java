package com.epam.provider.web.controller.command;

/**
 * @author Gleb Akseonov
 */
public enum ActionType {


    LOGIN, LOGOUT, GET_TARIFFS(CommandOwner.ADMIN),
    ADD_TARIFF(CommandOwner.ADMIN), SET_LOCALE, ADD_BALANCE(CommandOwner.CLIENT),
    CHANGE_TARIFF(CommandOwner.CLIENT), ADD_USER(CommandOwner.ADMIN), GET_USERS(CommandOwner.ADMIN),
    ADD_PROFILE(CommandOwner.ADMIN), GET_PROFILES(CommandOwner.ADMIN), UPDATE_TARIFF, GET_UPDATE_PAGE;

    enum CommandOwner {
        ADMIN, CLIENT, ANY
    }

    private CommandOwner owner;

    ActionType(CommandOwner owner) {
        this.owner = owner;
    }

    ActionType() {
        this.owner = CommandOwner.ANY;
    }

    public boolean isAdminCommand() {
        return owner == CommandOwner.ADMIN;
    }

    public boolean isClientCommand() {
        return owner == CommandOwner.CLIENT;
    }

}
