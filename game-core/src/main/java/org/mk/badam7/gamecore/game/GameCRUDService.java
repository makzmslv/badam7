package org.mk.badam7.gamecore.game;

import org.mk.badam7.gamedto.game.GameDTO;
import org.mk.badam7.gamedto.game.GameInDTO;
import org.mk.badam7.gamedto.game.GameUpdateDTO;

public interface GameCRUDService
{
    public GameDTO createGame(GameInDTO gameInDTO);

    public GameDTO updateGame(Integer gameId, GameUpdateDTO gameUpdateDTO);

    public GameDTO getById(Integer gameId);

    public GameDTO startGame(Integer gameId);
}
