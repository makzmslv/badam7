package org.mk.badam7.gamecore.player;

import org.mk.badam7.gamedto.player.PlayerDTO;
import org.mk.badam7.gamedto.player.PlayerInDTO;

public interface PlayerService
{
    public PlayerDTO createPlayer(PlayerInDTO playerInDTO);

    public PlayerDTO verifyPlayer(Integer playerId);

    public PlayerDTO getPlayerById(Integer playerId);
}
