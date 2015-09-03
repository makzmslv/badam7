package org.mk.badam7.gamecore.hand;

import java.util.List;

import org.mk.badam7.gamedto.hand.HandCurrentCardDTO;

public interface HandCurrentCardService
{
    public HandCurrentCardDTO createHandCurrentCard(HandCurrentCardDTO inputDTO);

    public List<HandCurrentCardDTO> getAllHandCurrentCards(Integer handId);
}
