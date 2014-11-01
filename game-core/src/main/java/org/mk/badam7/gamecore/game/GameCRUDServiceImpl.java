package org.mk.badam7.gamecore.game;

import org.dozer.Mapper;
import org.mk.badam7.database.dao.GameDAO;
import org.mk.badam7.database.entity.Game;
import org.mk.badam7.database.enums.GameStatus;
import org.mk.badam7.database.enums.GameType;
import org.mk.badam7.gamecore.library.Badam7Constants;
import org.mk.badam7.gamedto.game.GameDTO;
import org.mk.badam7.gamedto.game.GameInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameCRUDServiceImpl implements GameCRUDService
{

    @Autowired
    private GameDAO gameDAO;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public GameDTO createGame(GameInDTO gameInDTO)
    {
        validateInput(gameInDTO);

        Game game = createEntityFromDTO(gameInDTO);
        game = gameDAO.save(game);

        GameDTO gameDTO = dozerMapper.map(game, GameDTO.class);
        return gameDTO;
    }

    private void validateInput(GameInDTO gameInDTO)
    {
        if (gameInDTO == null)
        {
            throw new IllegalArgumentException("Input DTO null");
        }

        if (gameInDTO.getGameType() != GameType.BADAM7.getType())
        {
            throw new IllegalArgumentException("Incorrect Game Type");
        }

        if (gameInDTO.getNoOfPlayers() < Badam7Constants.MIN_NO_OF_PLAYERS)
        {
            throw new IllegalArgumentException("Incorrect no of players");
        }

        if (gameInDTO.getNoOfHands() > Badam7Constants.MAX_HANDS)
        {
            throw new IllegalArgumentException("Incorrect No of Hands");
        }

    }

    private Game createEntityFromDTO(GameInDTO gameInDTO)
    {
        Game game = new Game();
        game.setGameType(gameInDTO.getGameType());
        game.setNoOfHands(gameInDTO.getNoOfHands());
        game.setNoOfPlayers(gameInDTO.getNoOfHands());
        game.setStatus(GameStatus.CREATED.getStatusCode());

        return game;
    }

}
