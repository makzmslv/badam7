package org.mk.badam7.gamecore.library;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Badam7Constants
{
    public static final Integer MIN_NO_OF_PLAYERS = 4;

    public static final Integer MAX_HANDS = 7;

    public static final Integer MIN_HANDS = 1;

    public static final String EMAIL_SENDER = "Badam7Official";

    public static final String SUBJECT = "badam7.official@gmail.com";

    public static final String MESSAGE = "Please click on this link to verify your account :";

    public static final Map<Integer, Integer> nextPlayerMap;

    static
    {
        Map<Integer, Integer> aMap = new HashMap<Integer, Integer>();
        aMap.put(1, 2);
        aMap.put(2, 3);
        aMap.put(3, 4);
        aMap.put(4, 1);
        nextPlayerMap = Collections.unmodifiableMap(aMap);

    }

}
