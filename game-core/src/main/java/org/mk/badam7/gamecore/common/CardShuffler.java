package org.mk.badam7.gamecore.common;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.mk.badam7.database.entity.CardEntity;

public class CardShuffler
{
    public static List<CardEntity> shuffleCards(List<CardEntity> cards)
    {
        Collections.shuffle(cards, new Random());
        return cards;
    }
}
