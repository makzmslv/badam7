package org.mk.badam7.game.player;

import org.mk.badam7.database.entity.Player;
import org.mk.badam7.gamedto.player.PlayerDTO;

public interface PlayerCRUDService
{
    public Player createPlayer(PlayerDTO playerInDTO);
}
