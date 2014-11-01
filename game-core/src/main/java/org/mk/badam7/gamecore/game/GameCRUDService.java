package org.mk.badam7.gamecore.game;

import org.mk.badam7.gamecore.dto.GameDTO;
import org.mk.badam7.gamecore.dto.GameInDTO;

public interface GameCRUDService
{
    public GameDTO createGame(GameInDTO gameInDTO);
}
