package org.mk.badam7.gamecore.hand;

import org.mk.badam7.gamedto.hand.HandDTO;

public interface HandService
{
    public HandDTO createHand(Integer gameId);

    public HandDTO endHand(Integer handId);

}
