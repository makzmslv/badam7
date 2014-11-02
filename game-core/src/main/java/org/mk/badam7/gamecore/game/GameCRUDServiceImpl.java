package org.mk.badam7.gamecore.game;

import java.util.List;

import org.dozer.Mapper;
import org.mk.badam7.database.dao.GameDAO;
import org.mk.badam7.database.entity.Game;
import org.mk.badam7.database.enums.GameStatus;
import org.mk.badam7.database.enums.GameType;
import org.mk.badam7.gamecore.library.Badam7Constants;
import org.mk.badam7.gamedto.game.GameDTO;
import org.mk.badam7.gamedto.game.GameInDTO;
import org.mk.badam7.gamedto.game.GameUpdateDTO;
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

    @Override
    public GameDTO updateGame(Integer gameId, GameUpdateDTO gameUpdateDTO)
    {
        validateUpdateOperation(gameId, gameUpdateDTO);
        Game gameEntity = gameDAO.findOne(gameId);
        gameEntity.setStatus(gameUpdateDTO.getStatus());
        gameEntity = gameDAO.save(gameEntity);
        GameDTO gameDTO = dozerMapper.map(gameEntity, GameDTO.class);
        return gameDTO;
    }

    @Override
    public GameDTO getById(Integer gameId)
    {
        Game gameEntity = gameDAO.findOne(gameId);
        if (gameEntity == null)
        {
            throw new IllegalArgumentException("Game with given id does not exist");
        }
        GameDTO gameDTO = dozerMapper.map(gameEntity, GameDTO.class);
        return gameDTO;
    }

    private void validateUpdateOperation(Integer gameId, GameUpdateDTO gameUpdateDTO)
    {
        if (gameId == null)
        {
            throw new IllegalArgumentException("GameId cannot be null");
        }

        Game gameEntity = gameDAO.findOne(gameId);
        if (gameEntity == null)
        {
            throw new IllegalArgumentException("Game with given id does not exist");
        }
        Integer gameStatus = gameEntity.getStatus();
        if (!canGameBeUpdated(gameStatus))
        {
            throw new IllegalArgumentException("Game cannot be updated");
        }

        List<Integer> listOfGameStatuses = GameStatus.getAllStatuses();
        if (!listOfGameStatuses.contains(gameStatus))
        {
            throw new IllegalArgumentException("Invalid Update Status");
        }

        if (gameStatus > gameUpdateDTO.getStatus())
        {
            throw new IllegalArgumentException("Invalid Status");
        }
    }

    private boolean canGameBeUpdated(Integer gameStatus)
    {
        if (gameStatus == GameStatus.COMPLETED.getCode())
        {
            return false;
        }

        if (gameStatus == GameStatus.ABANDONED.getCode())
        {
            return false;
        }
        return true;
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
        game.setStatus(GameStatus.CREATED.getCode());

        return game;
    }

}
