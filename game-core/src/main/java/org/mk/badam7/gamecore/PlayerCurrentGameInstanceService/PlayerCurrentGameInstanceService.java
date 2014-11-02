package org.mk.badam7.gamecore.PlayerCurrentGameInstanceService;

import org.mk.badam7.gamecore.playercurrentgameinstance.PlayerCurrentGameInstanceDTO;

public interface PlayerCurrentGameInstanceService
{
    public PlayerCurrentGameInstanceDTO createPlayerCurrentGameInstance(Integer gameId, Integer playerId);
}
