package org.mk.badam7.gamecore.PlayerCurrentGameInstanceService;

import org.mk.badam7.gamedto.playercurrentgameinstance.PlayerCurrentGameInstanceDTO;

public interface PlayerCurrentGameInstanceService
{
    public PlayerCurrentGameInstanceDTO createPlayerCurrentGameInstance(Integer gameId, Integer playerId, String username);
}
