package org.mk.badam7.database.enums;

import java.util.ArrayList;
import java.util.List;

public enum Suite
{
    HEARTS(1, "H"),
    SPADES(2, "S"),
    DIAMONDS(3, "D"),
    CLUBS(4, "C");

    private Integer value;
    private String suiteValue;

    Suite(Integer id, String suiteValue)
    {
        this.value = id;
        this.suiteValue = suiteValue;
    }

    public Integer getType()
    {
        return value;
    }

    public String getSuiteValue()
    {
        return suiteValue;
    }

    public static List<String> getAllSuites()
    {
        List<String> suites = new ArrayList<String>();
        suites.add(Suite.CLUBS.getSuiteValue());
        suites.add(Suite.DIAMONDS.getSuiteValue());
        suites.add(Suite.HEARTS.getSuiteValue());
        suites.add(Suite.SPADES.getSuiteValue());
        return suites;
    }
}
