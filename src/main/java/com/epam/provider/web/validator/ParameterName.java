package com.epam.provider.web.validator;

/**
 * Created by HP on 04.04.2018.
 */
public enum ParameterName {
    USER_NAME("user_name", "regex");

    private String name;

    private String regexp;

    ParameterName(String name, String regexp) {
        this.name = name;
        this.regexp = regexp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegexp() {
        return regexp;
    }

    public void setRegexp(String regexp) {
        this.regexp = regexp;
    }
}