package org.mk.badam7.gamecore.game;

import java.util.List;

import org.mk.badam7.gamedto.game.GameDTO;
import org.mk.badam7.gamedto.game.GameDetailsDTO;
import org.mk.badam7.gamedto.game.GameInDTO;
import org.mk.badam7.gamedto.game.GameUpdateDTO;

public interface GameService
{
    public GameDTO createGame(GameInDTO gameInDTO);

    public GameDTO updateGame(Integer gameId, GameUpdateDTO gameUpdateDTO);

    public GameDetailsDTO getById(Integer gameId);

    public GameDetailsDTO startHand(Integer gameId);

    public GameDTO endGame(Integer gameId);

    public List<GameDTO> getGamesToJoin();
}
