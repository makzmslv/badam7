package org.mk.badam7.game.player;

import org.mk.badam7.gamedto.player.PlayerDTO;
import org.mk.badam7.gamedto.player.PlayerInDTO;

public interface PlayerCRUDService
{
    public PlayerDTO createPlayer(PlayerInDTO playerInDTO);
}
