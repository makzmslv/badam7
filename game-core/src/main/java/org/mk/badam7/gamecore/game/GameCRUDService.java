package org.mk.badam7.gamecore.game;

import org.mk.badam7.gamedto.game.GameDTO;
import org.mk.badam7.gamedto.game.GameInDTO;

public interface GameCRUDService
{
    public GameDTO createGame(GameInDTO gameInDTO);
}
