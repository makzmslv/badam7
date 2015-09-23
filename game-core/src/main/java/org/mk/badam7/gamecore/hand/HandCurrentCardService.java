package org.mk.badam7.gamecore.hand;

import java.util.List;
import java.util.Map;

import org.mk.badam7.gamedto.hand.HandCurrentCardDTO;

public interface HandCurrentCardService
{
    public HandCurrentCardDTO createHandCurrentCard(HandCurrentCardDTO inputDTO);

    public Map<Integer, List<HandCurrentCardDTO>> getAllHandCurrentCards(Integer handId);
}
