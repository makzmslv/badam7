package org.mk.badam7.gamecore.playercurrenthand;

import java.util.List;

import org.mk.badam7.gamedto.playercurrenthand.PlayerCurrentHandCardDTO;

public interface PlayerCurrentHandCardService
{
    public PlayerCurrentHandCardDTO createPlayerCurrentHandCard(PlayerCurrentHandCardDTO playerCurrentHandCardDTO);

    public PlayerCurrentHandCardDTO playCard(Integer playerId, Integer playerCurrentHandCardId, boolean skipChance);

    public List<PlayerCurrentHandCardDTO> getAllPlayerCurrentHandCards(Integer currentHandId, Integer playerCurrentGameInstanceId);
}
