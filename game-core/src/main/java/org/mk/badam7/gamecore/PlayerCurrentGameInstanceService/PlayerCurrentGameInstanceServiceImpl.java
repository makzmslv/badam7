package org.mk.badam7.gamecore.PlayerCurrentGameInstanceService;

import org.dozer.Mapper;
import org.mk.badam7.database.dao.GameDAO;
import org.mk.badam7.database.dao.PlayerCurrentGameInstanceDAO;
import org.mk.badam7.database.dao.PlayerDAO;
import org.mk.badam7.database.entity.Game;
import org.mk.badam7.database.entity.Player;
import org.mk.badam7.database.entity.PlayerCurrentGameInstance;
import org.mk.badam7.database.enums.GameStatus;
import org.mk.badam7.database.enums.PlayerCurrentGameInstanceStatus;
import org.mk.badam7.gamecore.playercurrentgameinstance.PlayerCurrentGameInstanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerCurrentGameInstanceServiceImpl implements PlayerCurrentGameInstanceService
{

    @Autowired
    private GameDAO gameDAO;

    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private PlayerCurrentGameInstanceDAO playerCurrentGameInstanceDAO;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public PlayerCurrentGameInstanceDTO createPlayerCurrentGameInstance(Integer gameId, Integer playerId)
    {
        Game game = getGame(gameId);
        Player player = getPlayer(playerId);
        PlayerCurrentGameInstance playerCurrentGameInstance = createPlayerGameInstanceEntity(game, player);
        playerCurrentGameInstance = playerCurrentGameInstanceDAO.save(playerCurrentGameInstance);
        updateGameStatus(game, playerCurrentGameInstance.getPlayerNo());
        PlayerCurrentGameInstanceDTO playerCurrentGameInstanceDTO = dozerMapper.map(playerCurrentGameInstance, PlayerCurrentGameInstanceDTO.class);

        return playerCurrentGameInstanceDTO;
    }

    private Player getPlayer(Integer playerId)
    {
        Player player = playerDAO.findOne(playerId);
        if (player == null)
        {
            throw new IllegalArgumentException("Player does not exist");
        }
        return player;
    }

    private Game getGame(Integer gameId)
    {
        Game game = gameDAO.findOne(gameId);
        if (game == null)
        {
            throw new IllegalArgumentException("Game with given id does not exist");
        }
        return game;
    }

    private PlayerCurrentGameInstance createPlayerGameInstanceEntity(Game game, Player player)
    {
        PlayerCurrentGameInstance playerCurrentGameInstance = new PlayerCurrentGameInstance();
        playerCurrentGameInstance.setGame(game);
        playerCurrentGameInstance.setPlayer(player);
        playerCurrentGameInstance.setStatus(PlayerCurrentGameInstanceStatus.JOINED_GAME.getStatusCode());
        Integer playerNo = playerCurrentGameInstanceDAO.getPlayerCountForGame(game);
        playerCurrentGameInstance.setPlayerNo(playerNo + 1);

        return playerCurrentGameInstance;
    }

    private void updateGameStatus(Game game, Integer playerNo)
    {
        Integer gameStatus = game.getStatus();
        if (gameStatus == GameStatus.CREATED.getCode())
        {
            game.setStatus(GameStatus.WAITING_FOR_PLAYERS.getCode());
            gameDAO.save(game);
        }

        if (game.getNoOfPlayers() == playerNo)
        {
            if (gameStatus == GameStatus.CREATED.getCode())
            {
                game.setStatus(GameStatus.WAITING_TO_START.getCode());
                gameDAO.save(game);
            }
        }

    }
}
